package com.selva.demo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selva.demo.R;
import com.selva.demo.model.Feeds;
import com.selva.demo.model.Response;
import com.selva.demo.presenter.FeedPresenter;
import com.selva.demo.presenter.FeedsCallback;
import com.selva.demo.presenter.FeedsView;
import com.selva.demo.utils.NetworkUtils;
import com.selva.demo.view.adapter.FeedsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class is to display feeds from web service
 *
 * @author selva.raman
 * @version 1.0
 * @since 5/31/2018
 */

public class FeedsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, FeedsView {
    private List<Feeds> mFeedsList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mFeedsView;
    private FeedPresenter mFeedPresenter;
    private FeedsCallback mFeedsCallback;

    /**
     * Method is to initialize activity callback
     *
     * @param context the activity context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (null != context) {
            mFeedsCallback = (FeedsCallback) context;
        }
    }

    /**
     * Method is to initialise fragment instance
     *
     * @return Fragment the FeedsFragment
     */
    public static FeedsFragment getInstance() {
        return new FeedsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeds, container, false);
        initializeViews(view);
        return view;
    }

    /**
     * Method is to initialize views
     *
     * @param view the View
     */
    private void initializeViews(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        mFeedsView = view.findViewById(R.id.feeds_view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary
                , android.R.color.holo_green_dark, android.R.color.holo_orange_dark
                , R.color.colorAccent, android.R.color.holo_blue_dark);

        /*
         * Showing Swipe Refresh animation on create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getFeeds();
            }
        });
        mFeedsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFeedPresenter = new FeedPresenter(FeedsFragment.this);
    }

    /**
     * Method is to get the feeds from webservice
     * if device connected to internet
     */
    private void getFeeds() {
        if (!NetworkUtils.isNetworkConnected()) {
            mSwipeRefreshLayout.setRefreshing(false);
            mFeedsCallback.showSnackBarMessage(getString(R.string.no_internet_connection));
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);
        // Fetching data from server
        mFeedPresenter.getFeeds();
    }

    /**
     * Pull down refresh callback
     */
    @Override
    public void onRefresh() {
        getFeeds();
    }

    /**
     * Method is to remove null feeds
     *
     * @param feedsList the list of feeds
     * @return List the non null feeds list
     */
    private List<Feeds> removeNullFeeds(List<Feeds> feedsList) {
        List<Feeds> temporaryFeedsList = new ArrayList<>();
        for (Feeds feeds : feedsList) {
            if (null != feeds && null != feeds.getTitle()
                    && null != feeds.getDescription() && null != feeds.getImageHref()) {
                temporaryFeedsList.add(feeds);
            }
        }
        return temporaryFeedsList;
    }

    /**
     * Method is to return size of feeds list
     *
     * @return int the feeds list size
     */
    public int getFeedsListSize() {
        return mFeedsList.size();
    }

    /**
     * Method is to update feeds response to views
     *
     * @param response the FeedsResponse
     */
    @Override
    public void updateFeedsView(Response response) {
        if (null == mFeedsCallback) return;
        if (null != response.getTitle()) {
            mFeedsCallback.updateTitle(response.getTitle());
        }
        mFeedsList = removeNullFeeds(response.getFeedsList());
        mFeedsView.setAdapter(new FeedsAdapter(mFeedsList));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onResponseFailure() {
        if (null == mFeedsCallback) return;
        mSwipeRefreshLayout.setRefreshing(false);
        mFeedsCallback.showSnackBarMessage(getString(R.string.something_went_wrong));
    }
}
