package com.masterfan.cloudbook.activity.launcher;

import android.os.Bundle;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

@MTFActivityFeature(layout = R.layout.activity_launcher, status_bar_color = R.color.colorPrimary, toolbar = R.id.toolbar)
public class LauncherActivity extends MTFBaseActivity {

    @Override
    public void initialize(Bundle savedInstanceState) {
        toolbar.setTitle("启动页");
    }
}
