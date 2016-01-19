package com.masterfan.cloudbook.activity.personal;

import android.os.Bundle;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

/**
 * 个人资料
 * Created by Administrator on 2016/1/20 0020.
 */
@MTFActivityFeature(layout = R.layout.activity_personal_data,toolbar = R.id.toolbar)
public class PersonalDataActivity extends MTFBaseActivity {
    @Override
    public void initialize(Bundle savedInstanceState) {
        toolbar.setTitle("个人资料");
    }
}
