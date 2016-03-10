package com.masterfan.cloudbook.Util;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;

/**
 * @ClassName: DialogUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhucl
 * @date 2014-4-22 下午3:14:19
 *
 */
public class DialogUtils {

    private static DialogUtils instance = null;
    private Context mContext = null;

    private static ProgressDialog mProgressDialog = null;
    private static AlertDialog mAlertDialog = null;
    private static View mView = null;
    private DialogUtils(Context context) {
        this.mContext = context;
    }

    /***
     * context 必须传当前页面activity的上下文
     *
     * @param context
     * @return
     */
    public static DialogUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DialogUtils(context);
        }
        return instance;
    }

    public static Dialog showCustomDialogNoTitle(Context context, View view) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        return dialog;
    }

    public static Dialog showCustomDialog(Context context, View view) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(view);

        return dialog;
    }

    public static Dialog showCustomDialogWithTitle(Context context, View view, String title) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(view);
        dialog.setTitle(title);

        return dialog;
    }

    public static void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

}