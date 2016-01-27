package com.masterfan.cloudbook.fragment.home;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.test.TestRecyclerViewLinearLayoutActivity;
import com.masterfan.library.dialog.normal.DialogAction;
import com.masterfan.library.dialog.normal.GravityEnum;
import com.masterfan.library.dialog.normal.MTFDialog;
import com.masterfan.library.dialog.version.MTFVersionDialog;
import com.masterfan.library.dialog.wait.WaitDialog;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;
import com.masterfan.library.utils.S;
import com.masterfan.library.utils.T;
import com.masterfan.library.widget.pickerWheel.TimePopupWindow;

import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 测试数据
 * Created by 13510 on 2016/1/19.
 */
@MTFFragmentFeature(layout = R.layout.fragment_home_management_test)
public class HomeManagementFragmentTest extends MTFBaseFragment {

    private final String TAG = HomeManagementFragmentTest.class.getCanonicalName();

    private MTFVersionDialog versionDialog;//版本dialog
    private TimePopupWindow pwTime;

    @Bind(R.id.dialog_btn)
    Button dialogBtn;

    @OnClick(R.id.dialog_btn)
    public void dialogClick(View view) {
        new MTFDialog.Builder(getActivity())
                .title("提示您")
                .content("是否确认?")
                .positiveText("稍后提示我")
                .negativeText("确认")
                .btnStackedGravity(GravityEnum.END)
                .forceStacking(true)
                .onAny(new MTFDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MTFDialog dialog, @NonNull DialogAction which) {
                        T.s(getActivity(), "选择" + which.name());
                    }
                }).show();
    }

    @OnClick(R.id.dialog_btn2)
    public void dialogClick2(View view) {
        new MTFDialog.Builder(getActivity())
                .title("提示您")
                .content("是否确认?")
                .positiveText("稍后提示我")
                .negativeText("确认")
                .btnStackedGravity(GravityEnum.END)
                .onAny(new MTFDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MTFDialog dialog, @NonNull DialogAction which) {
                        T.s(getActivity(), "选择" + which.name());
                    }
                }).show();
    }

    @OnClick(R.id.dialog_btn3)
    public void dialogClick3(View view) {
        WaitDialog d =  new WaitDialog.Builder(context).create();
        d.setCancelable(true);
        d.show();
    }

    @OnClick(R.id.dialog_btn31)
    public void dialogClick31(View view) {
        final String [] genders = {"男", "女", "不男不女"};
        new MTFDialog.Builder(getActivity())
                .title("请选择性别")
                .items(genders)
                .itemsCallbackSingleChoice(0, new MTFDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MTFDialog dialog, View itemView, int which, CharSequence text) {
                        T.s(context, "你选择的是     [" + genders[which]+"]");
                        return true;
                    }
                })
                .positiveText("确认选择")
                .show();
    }

    @OnClick(R.id.dialog_btn4)
    public void dialogClick4(View view) {
        versionDialog.show(getFragmentManager(), TAG);
    }

    @OnClick(R.id.dialog_btn5)
    public void dialogClick5(View view) {
        pwTime.showAtLocation(dialogBtn, Gravity.BOTTOM, 0, 0, new Date());
    }

    @OnClick(R.id.dialog_btn6)
    public void dialogClick6(View view) {
        animStart(TestRecyclerViewLinearLayoutActivity.class);
    }
    @OnClick(R.id.dialog_btn61)
    public void dialogClick61(View view) {
        final String [] genders = {"Apple", "Android", "Windows", "Blackberry"};
        new MTFDialog.Builder(getActivity())
                .title("请选择系统")
                .items(genders)
                .itemsCallbackMultiChoice(new Integer[]{1, 3}, new MTFDialog.ListCallbackMultiChoice() {//设置选中项和回调
                    @Override
                    public boolean onSelection(MTFDialog dialog, Integer[] which, CharSequence[] text) {

                        StringBuilder str = new StringBuilder();
                        for (int i = 0; i < which.length; i++) {
                            if (i > 0) str.append('\n');
                            str.append(which[i]);
                            str.append(": ");
                            str.append(text[i]);
                        }
                        T.s(context, str.toString());
                        return true; // allow selection
                    }
                })
                .positiveText("确认选择")
                .show();
    }

    @Override
    public void initialize() {
        versionDialog = new MTFVersionDialog();
        versionDialog.setOnMTFDialogClickListener(new MTFVersionDialog.OnMTFDialogClickListener() {
            @Override
            public void onClose() {
                versionDialog.tilesClose();
            }

            @Override
            public void onPositive() {
                versionDialog.change2download(null);
            }

            @Override
            public void onTilesAnimFinish() {
                versionDialog.dismiss();
            }
        });

        //时间选择器
        pwTime = new TimePopupWindow(getActivity(), TimePopupWindow.Type.YEAR_MONTH_DAY);
        pwTime.setTime(new Date());
        pwTime.setDefaultRange();

        //时间选择后回调
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                S.o("" + date.toString());
            }
        });
    }
}
