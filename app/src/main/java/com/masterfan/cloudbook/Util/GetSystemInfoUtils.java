package com.masterfan.cloudbook.Util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.masterfan.cloudbook.http.bean.Tokens;
import com.masterfan.cloudbook.http.bean.UserInfo;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;


/**
 * 获取系统信息
 * Created by sunzj on 2016/3/17.
 */
public class GetSystemInfoUtils {

    /**
     * 获取apk的版本号 currentVersionCode
     * @param ctx
     * @return
     */
    public static int getAPPVersionCodeFromAPP(Context ctx) {
        int currentVersionCode = 0;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            String appVersionName = info.versionName; // 版本名
            currentVersionCode = info.versionCode; // 版本号
            System.out.println(currentVersionCode + " " + appVersionName);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch blockd
            e.printStackTrace();
        }
        return currentVersionCode;
    }
}
