package com.masterfan.cloudbook.fragment.home;

import android.view.View;
import android.widget.LinearLayout;
import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;
import com.masterfan.library.utils.T;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 管理
 * Created by 13510 on 2016/1/19.
 */
@MTFFragmentFeature(layout = R.layout.fragment_home_management)
public class HomeManagementFragment extends MTFBaseFragment {

    @Bind(R.id.home_meanagement_read_statistics_layout)
    LinearLayout readStatisticsLayout;//阅读统计

    @Bind(R.id.home_meanagement_book_read_management_layout)
    LinearLayout bookReadManagementLayout;//图书阅读管理

    @Bind(R.id.home_meanagement_user_read_management_layout)
    LinearLayout userReadManagementLayout;//用户阅读管理

    @Bind(R.id.home_meanagement_user_account_management_layout)
    LinearLayout userAccountManagementLayout;//用户账号管理

    @Bind(R.id.home_meanagement_teacher_booth_management_layout)
    LinearLayout teacherBoothManagementLayout;//老师展台管理

    @Bind(R.id.home_meanagement_upload_library_audit_management_layout)
    LinearLayout bookAuditManagementLayout;//上传图书审核管理

    @Bind(R.id.home_meanagement_book_data_management_layout)
    LinearLayout bookDataManagementLayout;//图书综合数据管理

    @Bind(R.id.home_meanagement_class_read_management_layout)
    LinearLayout classReadManagementLayout;//班级阅读管理

    @Override
    public void initialize() {

    }

    @OnClick(R.id.home_meanagement_read_statistics_layout)
    public void onclickaView(View view){
        T.s(getActivity(),"a");
    }

    @OnClick(R.id.home_meanagement_book_read_management_layout)
    public void onclickbView(View view){
        T.s(getActivity(),"b");
    }

    @OnClick(R.id.home_meanagement_user_read_management_layout)
    public void onclickcView(View view){
        T.s(getActivity(),"c");
    }

    @OnClick(R.id.home_meanagement_user_account_management_layout)
    public void onclickdView(View view){
        T.s(getActivity(),"d");
    }

    @OnClick(R.id.home_meanagement_teacher_booth_management_layout)
    public void onclickeView(View view){
        T.s(getActivity(),"e");
    }

    @OnClick(R.id.home_meanagement_upload_library_audit_management_layout)
    public void onclickfView(View view){
        T.s(getActivity(),"f");
    }

    @OnClick(R.id.home_meanagement_book_data_management_layout)
    public void onclickgView(View view){
        T.s(getActivity(),"g");
    }

    @OnClick(R.id.home_meanagement_class_read_management_layout)
    public void onclickhView(View view){
        T.s(getActivity(),"h");
    }
}
