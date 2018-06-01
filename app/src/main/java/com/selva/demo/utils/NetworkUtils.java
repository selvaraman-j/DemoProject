package com.selva.demo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
     * @param context context of the application
     * @return true if device is connected to internet otherwise false
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (null != connectivityManager) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return null != activeNetworkInfo && activeNetworkInfo.isConnected();
    }
}
