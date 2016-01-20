package com.masterfan.cloudbook.fragment.home;

import android.support.annotation.NonNull;
import android.view.View;

import com.masterfan.cloudbook.R;
import com.masterfan.library.dialog.normal.DialogAction;
import com.masterfan.library.dialog.normal.GravityEnum;
import com.masterfan.library.dialog.normal.MTFDialog;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;
import com.masterfan.library.utils.T;

import butterknife.OnClick;

/**
 * Created by 13510 on 2016/1/19.
 */
@MTFFragmentFeature(layout = R.layout.fragment_home_management)
public class HomeManagementFragment extends MTFBaseFragment {

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

    @Override
    public void initialize() {

    }
}
