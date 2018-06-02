package com.selva.demo;

import android.app.Application;
import android.content.Context;

/**
 * Class is to keep a reference of the application context
 *
 * @author selva.raman
 * @version 1.0
 * @since 6/1/2018
 */

public class DemoApplication extends Application {
    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
    }

    /**
     * Method is to return the application context
     *
     * @return Context the application context
     */
    public static Context getContext() {
        return mApplicationContext;
    }

}
