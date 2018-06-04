package com.selva.demo.presenter;


/**
 * Interface is to notify the network connectivity changes
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/4/2018
 */
public interface ConnectionReceiverListener {
    void onNetworkConnectionChanged(boolean isConnected);
}
