package com.masterfan.cloudbook.activity.login;

import android.os.Bundle;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

@MTFActivityFeature(layout = R.layout.activity_signup, status_bar_color = R.color.colorPrimary, toolbar = R.id.toolbar)
public class SignupActivity extends MTFBaseActivity {


    @Override
    public void initialize(Bundle savedInstanceState) {
        toolbar.setTitle("Signup page.");
    }
}
