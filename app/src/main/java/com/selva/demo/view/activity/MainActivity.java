package com.selva.demo.view.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.selva.demo.DemoApplication;
import com.selva.demo.R;
import com.selva.demo.presenter.ConnectionReceiverListener;
import com.selva.demo.presenter.FeedsCallback;
import com.selva.demo.receiver.ConnectivityReceiver;
import com.selva.demo.view.fragment.FeedsFragment;

/**
 * Class is to attach the fragment to activity
 *
 * @author selva.raman
 * @version 1.0
 * @since 5/31/2018
 */
public class MainActivity extends AppCompatActivity implements FeedsCallback, ConnectionReceiverListener {
    private ConnectivityReceiver mConnectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (null == savedInstanceState) {
            loadFragment(FeedsFragment.getInstance(), FeedsFragment.class.getSimpleName());
        } else {
            findFeedFragment();
        }
    }

    public void loadFragment(Fragment fragment, String tag) {
        //attach fragment to the activity
        getSupportFragmentManager().beginTransaction().add(R.id.container
                , fragment, tag)
                .addToBackStack(fragment.toString())
                .commit();
    }

    /**
     * Method is to update the action bar title
     *
     * @param title the Action bar title
     */
    @Override
    public void updateTitle(@NonNull String title) {
        if (null != getSupportActionBar())
            getSupportActionBar().setTitle(title);
    }

    /**
     * Method is to show or hide the back arrow navigation in action bar
     *
     * @param isShow boolean true if wants to show the back arrow navigation otherwise false
     */
    public void showOrHideNavIcon(boolean isShow) {
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
        }
    }

    /**
     * Method is to find FeedsFragment from fragment manager
     *
     * @return Fragment the FeedsFragment
     */
    public Fragment findFeedFragment() {
        return getSupportFragmentManager().findFragmentByTag(FeedsFragment.class.getSimpleName());
    }

    /**
     * Register the network connectivity receiver
     * when activity becomes visible
     */
    @Override
    protected void onResume() {
        super.onResume();
        mConnectivityReceiver = new ConnectivityReceiver();
        DemoApplication.getInstance().setConnectionReceiverListener(this);
        registerReceiver(mConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    /**
     * Un register the network connectivity receiver
     * when the activity goes to paused state
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (null != mConnectivityReceiver) {
            unregisterReceiver(mConnectivityReceiver);
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            finish();
        } else {
            super.onBackPressed();
            //hide the back arrow navigation when feeds fragment is shown
            FeedsFragment feedsFragment = (FeedsFragment) findFeedFragment();
            if (null != feedsFragment && feedsFragment.isVisible()) {
                showOrHideNavIcon(false);
            }
        }
    }

    /**
     * Method is to refresh the feed list from web server
     * when the device is connected to internet
     *
     * @param isConnected boolean if device connected to internet otherwise false
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            FeedsFragment feedFragment = (FeedsFragment) findFeedFragment();
            if (null != feedFragment && feedFragment.isVisible()) {
                feedFragment.onRefresh();
            }
        }
    }
}
