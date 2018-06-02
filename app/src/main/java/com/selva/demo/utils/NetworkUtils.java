package com.selva.demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.selva.demo.DemoApplication;

/**
 * Class for only network utility
 *
 * @author selva.raman
 * @version 1.0
 * @since 5/31/2018
 */

public final class NetworkUtils {
    /**
     * Method is to check whether the internet connection is available or not
     *
     * @return true if device is connected to internet otherwise false
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) DemoApplication
                .getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (null != connectivityManager) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return null != activeNetworkInfo && activeNetworkInfo.isConnected();
    }
}
