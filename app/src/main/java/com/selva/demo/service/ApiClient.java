package com.selva.demo.service;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class is to initialise retrofit instance for retrieving the feeds from web server
 *
 * @author selva.raman
 * @version 1.0
 * @since 5/31/2018
 */

public class ApiClient {
    private Retrofit mRetrofit;

    /**
     * Empty constructor
     */
    public ApiClient() {
    }

    /**
     * Method is to initialise the retrofit instance
     *
     * @param context the application context
     * @return ApiService
     */
    public ApiService getApiClient(Context context) {
        if (null == mRetrofit) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.connectTimeout(60, TimeUnit.SECONDS);

            int cacheSize = 10 * 1024 * 1024;//10Mb
            Cache cache = new Cache(context.getCacheDir(), cacheSize);
            builder.cache(cache);
            OkHttpClient okHttpClient = builder.build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit.create(ApiService.class);
    }
}
