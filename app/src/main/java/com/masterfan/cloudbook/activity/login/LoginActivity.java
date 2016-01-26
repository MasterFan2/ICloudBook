package com.masterfan.cloudbook.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.main.HomeActivity;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;
import butterknife.OnClick;

@MTFActivityFeature(layout = R.layout.activity_login, status_bar_color = R.color.colorPrimary)
public class LoginActivity extends MTFBaseActivity {

    @Bind(R.id.login_username_edit)
    EditText usernameEdit;

    @Bind(R.id.login_password_edit)
    EditText passwordEdit;

    @Bind(R.id.login_login_btn)
    Button loginBtn;

    @Bind(R.id.login_forget_password_textview)
    TextView forgetTxt;

    @Override
    public void initialize(Bundle savedInstanceState) {

    }

    @OnClick(R.id.login_login_btn)
      public void onclickLoginBtn(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        if(intent != null){
            startActivity(intent);
        }
    }
    @OnClick(R.id.login_forget_password_textview)
    public void onclickForgetTxt(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        if(intent != null){
            startActivity(intent);
        }
    }
}
