package com.masterfan.cloudbook.activity.login;

import android.os.Bundle;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

@MTFActivityFeature(layout = R.layout.activity_login, status_bar_color = R.color.colorPrimary)
public class LoginActivity extends MTFBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initialize(Bundle savedInstanceState) {

    }
}
