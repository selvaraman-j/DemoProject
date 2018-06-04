package com.selva.demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.selva.demo.presenter.ConnectionReceiverListener;
import com.selva.demo.utils.NetworkUtils;

/**
 * BroadcastReceiver is to notify the device connectivity changes
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/4/2018
 */

public class ConnectivityReceiver extends BroadcastReceiver {
    public static ConnectionReceiverListener mConnectionReceiverListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != intent.getAction() &&
                intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (null != mConnectionReceiverListener) {
                mConnectionReceiverListener.onNetworkConnectionChanged(NetworkUtils.isNetworkConnected());
            }
        }
    }
}
