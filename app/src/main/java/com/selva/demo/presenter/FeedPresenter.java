package com.selva.demo.presenter;

import android.support.annotation.Nullable;

import com.selva.demo.model.Response;
import com.selva.demo.service.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Class is to fetch the feed from web server
 * and update the response to feed view
 *
 * @author selva.raman
 * @version 1.0
 * @since 5/31/2018
 */

public class FeedPresenter {
    private final FeedsView mFeedsView;
    private ApiClient mApiClient;

    /**
     * Constructor with parameter
     *
     * @param mFeedsView the FeedsView
     */
    public FeedPresenter(FeedsView mFeedsView) {
        this.mFeedsView = mFeedsView;
        if (null == mApiClient) {
            mApiClient = new ApiClient();
        }
    }

    /**
     * Method is get the feeds from web server
     */
    public void getFeeds() {
        mApiClient.getApiClient().getFeeds().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@Nullable Call<Response> call, @Nullable retrofit2.Response<Response> response) {
                if (null != response && response.isSuccessful()) {
                    Response res = response.body();
                    if (null != res && res.getFeedsList() != null) {
                        mFeedsView.updateFeedsView(res);
                    }
                } else {
                    mFeedsView.onResponseFailure();
                }
            }

            @Override
            public void onFailure(@Nullable Call<Response> call, @Nullable Throwable t) {
                mFeedsView.onResponseFailure();
            }
        });
    }
}
