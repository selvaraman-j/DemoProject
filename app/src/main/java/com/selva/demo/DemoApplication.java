package com.selva.demo;

import android.app.Application;

import com.selva.demo.presenter.ConnectionReceiverListener;
import com.selva.demo.receiver.ConnectivityReceiver;

/**
 * Class is to keep a reference of the application context
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/1/2018
 */

public class DemoApplication extends Application {
    private static DemoApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * Method is to get the instance of application context
     *
     * @return DemoApplication the application instance
     */
    public static synchronized DemoApplication getInstance() {
        return mInstance;
    }

    /**
     * Method is to set the connectivity listener
     *
     * @param listener the ConnectionReceiverListener
     */
    public void setConnectionReceiverListener(ConnectionReceiverListener listener) {
        ConnectivityReceiver.mConnectionReceiverListener = listener;
    }
}
