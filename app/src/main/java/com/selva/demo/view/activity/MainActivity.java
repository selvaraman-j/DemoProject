package com.selva.demo.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.selva.demo.R;
import com.selva.demo.presenter.FeedsCallback;
import com.selva.demo.view.fragment.FeedsFragment;

/**
 * Class is to attach the fragment to activity
 *
 * @author selva.raman
 * @version 1.0
 * @since 5/31/2018
 */
public class MainActivity extends AppCompatActivity implements FeedsCallback {
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCoordinatorLayout = findViewById(R.id.coordinator_layout);
        if (null == savedInstanceState) {
            //attach fragment to the activity
            getSupportFragmentManager().beginTransaction().replace(R.id.container
                    , FeedsFragment.getInstance(), FeedsFragment.class.getSimpleName())
                    .commit();
        } else {
            findFeedFragment();
        }
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
     * Method is to display the message in Snack bar
     *
     * @param message the Snack bar message
     */
    @Override
    public void showSnackBarMessage(String message) {
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message
                , Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Fragment fragment = findFeedFragment();
                        if (null != fragment && fragment instanceof FeedsFragment) {
                            ((FeedsFragment) fragment).onRefresh();
                        }
                    }
                });
        snackbar.show();
    }

    /**
     * Method is to find FeedsFragment from fragment manager
     *
     * @return Fragment the FeedsFragment
     */
    public Fragment findFeedFragment() {
        return getSupportFragmentManager().findFragmentByTag(FeedsFragment.class.getSimpleName());
    }
}
