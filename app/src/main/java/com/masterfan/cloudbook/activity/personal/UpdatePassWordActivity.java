package com.masterfan.cloudbook.activity.personal;

import android.os.Bundle;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

/**
 * 修改密码
 * Created by Administrator on 2016/1/20 0020.
 */
@MTFActivityFeature(layout = R.layout.activity_update_login_password,toolbar = R.id.toolbar)
public class UpdatePassWordActivity extends MTFBaseActivity {
    @Override
    public void initialize(Bundle savedInstanceState) {
        toolbar.setTitle("修改密码");
    }
}
