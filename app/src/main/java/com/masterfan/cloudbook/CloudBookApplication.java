package com.masterfan.cloudbook;

import android.app.Application;

import com.masterfan.cloudbook.http.net.HttpClient;

import org.xutils.x;

/**
 * Created by MasterFan on 2016/1/15.
 */
public class CloudBookApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //initialize local cache
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        //initialize network
        HttpClient.getInstance().init(this);
    }
}
