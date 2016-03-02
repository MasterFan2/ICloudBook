package com.masterfan.cloudbook.activity.personal.util;

public class Log {

    private static final String TAG = "AAAA";

    public static void e(String msg) {
        android.util.Log.i(TAG, msg);
    }

    public static void e(String msg, Throwable e) {
        android.util.Log.e(TAG, msg, e);
    }

    public static void i(String msg) {
        android.util.Log.i(TAG, msg);
    }

    public static void i(String msg, Throwable e) {
        android.util.Log.i(TAG, msg, e);
    }

}
