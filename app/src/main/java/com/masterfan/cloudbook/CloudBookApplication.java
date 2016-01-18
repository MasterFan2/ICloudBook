package com.masterfan.cloudbook;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 13510 on 2016/1/15.
 */
public class CloudBookApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
