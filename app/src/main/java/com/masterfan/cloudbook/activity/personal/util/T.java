package com.masterfan.cloudbook.activity.personal.util;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Fan on 2014-12-16.
 */
public class T {


    private static String code;
    private static String imageUrl;
    public static void s(Context context , String str){
        if(!TextUtils.isEmpty(str)){
            if(context != null) {
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            }
        }

    }
    public static String getCode(){
        return code;
    }

    public static String getImageUrl(){
        return imageUrl;
    }

    public static void setCode(String code) {
        T.code = code;
    }

    public static void setImageUrl(String imageUrl) {
        T.imageUrl = imageUrl;
    }
}