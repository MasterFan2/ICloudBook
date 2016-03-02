package com.masterfan.cloudbook.activity.personal.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Fan on 2015-02-13.
 */
public class PreUtil {


    public static boolean isFirstByTag(String tag, Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        boolean flag = sp.getBoolean(tag, true);
        return flag;
    }

    public static void setFirstByTag(String tag, Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(tag, false);
        editor.commit();
    }

    /**
     * 检查是否第一次运行
     * @param context
     * @return
     */
    public static boolean isFirstRun(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        boolean flag = sp.getBoolean("firstRun", false);
        return flag;
    }

    /**
     * 设置
     * @param context
     */
    public static void setRun(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("firstRun", true);
        editor.commit();
    }

    /**
     * 是否登录
     * @return true: 已登录    false:未登录
     */
    public static boolean isLogin(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String account = sp.getString("account", null);
        return account != null;
    }

    /**
     * 清空登陆信息
     * @return true: 已登录    false:未登录
     */
    public static void clearLoginInfo(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("account");
        editor.remove("password");
        editor.commit();
    }

    /**
     * 保存用户信息
     *
     * @param context
     * @param account
     * @param pwd
     */
    public static void saveLoginInfo(Context context, String account, String pwd){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("account", account);
        editor.putString("password", pwd);
        editor.commit();
    }

    public static void addObject(Context context, String key, String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getObject(Context context, String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key, null);
    }

    public static void delObject(Context context, String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }
}
