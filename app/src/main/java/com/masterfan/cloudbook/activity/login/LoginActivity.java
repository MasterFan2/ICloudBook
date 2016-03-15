package com.masterfan.cloudbook.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.Util.Dbutils;
import com.masterfan.cloudbook.activity.main.HomeActivity;
import com.masterfan.cloudbook.activity.personal.util.T;
import com.masterfan.cloudbook.http.bean.UserInfo;
import com.masterfan.cloudbook.http.bean.UserResp;
import com.masterfan.cloudbook.http.net.HttpClient;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.S;
import com.masterfan.library.widget.pickerWheel.TimePopupWindow;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;

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

    public static final int LOGIN_UNAME_IS_NULL  = 0xff01;//user name is null;
    public static final int LOGIN_UPWD_IS_NULL   = 0xff02;//password is null;
    public static final int LOGIN_PWD_SHORT      = 0xff03;//password too short;
    public static final int LOGIN_VERIFY_SUCCESS = 0xff04;//verify success;

    DbManager db;
    private ProgressDialog pd;
    private UserInfo userInfo;
    @Override
    public void initialize(Bundle savedInstanceState) {
        db = x.getDb(Dbutils.getConfig());
        try {
            userInfo = db.findFirst(UserInfo.class);
            if(userInfo != null){
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                animFinish();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.login_login_btn)
      public void onclickLoginBtn(View view){
        final String uname = usernameEdit.getText().toString().trim();
        final String pwd = passwordEdit.getText().toString().trim();
        int verifyResult = verify(uname, pwd);

        if (verifyResult == LOGIN_UNAME_IS_NULL) T.s(context, "用户名不能为空");
        else if (verifyResult == LOGIN_UPWD_IS_NULL) T.s(context, "密码不能为空");
        else if (verifyResult == LOGIN_PWD_SHORT) T.s(context, "密码太短");
        else if (verifyResult == LOGIN_VERIFY_SUCCESS) {
//            T.s(context, "验证通过");
             /* 显示ProgressDialog */
            pd = ProgressDialog.show(LoginActivity.this, "提示", "登陆中……");
            HttpClient.getInstance().login(uname, pwd, 1, callback);
            Log.i("AAAA", "HttpClient.getInstance().login(\"ajian\", \"123456\", 1, callback);");
        }


    }

    /**
     * 验证账号和密码合法性
     */
    public int verify(String userName, String password){
        // ...
        if(TextUtils.isEmpty(userName)) return LOGIN_UNAME_IS_NULL;

        //...
        if(TextUtils.isEmpty(password)) return LOGIN_UPWD_IS_NULL;

        //...
        if(password.trim().length() < 6) return LOGIN_PWD_SHORT;

        //...
        return LOGIN_VERIFY_SUCCESS;
    }

    private Callback<UserResp> callback = new Callback<UserResp>() {
        @Override
        public void success(UserResp userResp, Response response) {
            pd.dismiss();// 关闭ProgressDialog
            if (userResp.getCode() == 200) {
                 Log.i("AAAA",userResp.toString());
                if(userResp.getUser() != null) {
                        UserInfo userInfo = userResp.getUser();
                        try {
                               UserInfo oldUserInfo = db.findFirst(UserInfo.class);
                            if(oldUserInfo == null){
                                db.save(userInfo);
                            }
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (db.findFirst(UserInfo.class) != null) {
                                if (userInfo != null) {
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    animFinish();
                                }
                                Log.i("AAAA", "解析正确：" + userInfo.toString());
                            }
                        } catch (DbException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.i("AAAA", "解析错误：" + e.toString());
                        }

                }

            }else if(userResp.getCode() == 400) {
                T.s(LoginActivity.this,"用户名或密码错误");
            }else if(userResp.getCode() == 401) {
                T.s(LoginActivity.this,"用户被禁用");
            }else{
                //error
                Log.i("AAAA","userResp.getCode()=="+userResp.getCode());
            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.i("AAAA", "error" + error.toString());
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
