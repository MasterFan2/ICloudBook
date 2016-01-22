package com.masterfan.cloudbook.activity.personal;

import android.os.Bundle;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

/**
 * 电子邮件
 * Created by Administrator on 2016/1/20 0020.
 */
@MTFActivityFeature(layout = R.layout.activity_email,toolbar = R.id.toolbar, status_bar_color = R.color.colorPrimary)
public class EmailActivity extends MTFBaseActivity {
    @Override
    public void initialize(Bundle savedInstanceState) {
        toolbar.setTitle("电子邮件");
    }
}
