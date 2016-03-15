package com.masterfan.cloudbook.Util;

import android.os.Environment;

import org.xutils.DbManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by master on 2015-12-14.
 */
public class Dbutils {
    public static String path;
    public static DbManager.DaoConfig getConfig(){
        path = Environment.getExternalStorageDirectory()+ "/icbook";
        File file = new File(path);
        if(!file.exists()){
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("db_icloudbook")//数据库名
                .setDbDir(new File(path))
                .setDbVersion(1)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });
        return daoConfig;
    }
}
