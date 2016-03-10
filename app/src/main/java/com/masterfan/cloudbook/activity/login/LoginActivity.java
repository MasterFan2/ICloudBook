package com.masterfan.cloudbook.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.main.HomeActivity;
import com.masterfan.cloudbook.http.bean.UserResp;
import com.masterfan.cloudbook.http.net.HttpClient;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.S;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
//        Intent intent = new Intent(this, HomeActivity.class);
//        if(intent != null){
//            animStart(intent);
//            animFinish();
//        }
        HttpClient.getInstance().login("ajian", "123456", 1, callback);
        Log.i("AAAA", "HttpClient.getInstance().login(\"ajian\", \"123456\", 1, callback);" );
    }

    private Callback<UserResp> callback = new Callback<UserResp>() {
        @Override
        public void success(UserResp userResp, Response response) {
            if (userResp.getCode() == 200) {
                if(userResp.getUser() != null) {
                    S.o(userResp.getUser().toString());
                    Log.i("AAAA",""+userResp.getUser().toString());
                }
            }else {
                //error
                Log.i("AAAA","userResp.getCode()=="+userResp.getCode());
            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.i("AAAA","error"+error.toString());
        }
    };


    @OnClick(R.id.login_forget_password_textview)
    public void onclickForgetTxt(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        if(intent != null){
            animStart(intent);
            animFinish();
        }
    }
}
