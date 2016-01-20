package com.masterfan.library.dialog.normal;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;

import com.masterfan.library.dialog.normal.internal.MDRootLayout;

/**
 * Created by MasterFan on 2016/1/20.
 */
class DialogBase extends Dialog implements DialogInterface.OnShowListener {

    protected MDRootLayout view;
    private OnShowListener mShowListener;

    protected DialogBase(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public View findViewById(int id) {
        return view.findViewById(id);
    }

    @Override
    public final void setOnShowListener(OnShowListener listener) {
        mShowListener = listener;
    }

    protected final void setOnShowListenerInternal() {
        super.setOnShowListener(this);
    }

    protected final void setViewInternal(View view) {
        super.setContentView(view);
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if (mShowListener != null)
            mShowListener.onShow(dialog);
    }

    @Override
    @Deprecated
    public void setContentView(int layoutResID) throws IllegalAccessError {
        throw new IllegalAccessError("setContentView() is not supported in MTFDialog. Specify a custom view in the Builder instead.");
    }

    @Override
    @Deprecated
    public void setContentView(View view) throws IllegalAccessError {
        throw new IllegalAccessError("setContentView() is not supported in MTFDialog. Specify a custom view in the Builder instead.");
    }

    @Override
    @Deprecated
    public void setContentView(View view, ViewGroup.LayoutParams params) throws IllegalAccessError {
        throw new IllegalAccessError("setContentView() is not supported in MTFDialog. Specify a custom view in the Builder instead.");
    }
}