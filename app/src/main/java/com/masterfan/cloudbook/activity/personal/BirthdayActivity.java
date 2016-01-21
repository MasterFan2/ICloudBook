package com.masterfan.cloudbook.activity.personal;

import android.os.Bundle;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

/**
 * 昵称
 * Created by Administrator on 2016/1/20 0020.
 */
@MTFActivityFeature(layout = R.layout.activity_birthday,toolbar = R.id.toolbar, status_bar_color = R.color.colorPrimary)
public class BirthdayActivity extends MTFBaseActivity {
    @Override
    public void initialize(Bundle savedInstanceState) {
        toolbar.setTitle("生日");
    }
}
