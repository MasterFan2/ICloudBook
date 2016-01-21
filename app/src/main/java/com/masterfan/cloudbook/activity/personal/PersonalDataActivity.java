package com.masterfan.cloudbook.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 个人资料
 * Created by Administrator on 2016/1/20 0020.
 */
@MTFActivityFeature(layout = R.layout.activity_personal_data,toolbar = R.id.toolbar, status_bar_color = R.color.colorPrimary)
public class PersonalDataActivity extends MTFBaseActivity {

    @Bind(R.id.personal_data_nick_name_layout)
    LinearLayout nickNameLayout;

    @Bind(R.id.personal_data_zwjs_layout)
    LinearLayout zwjsLayout;

    @Bind(R.id.personal_data_birthday_layout)
    LinearLayout birthdayLayout;

    @Bind(R.id.personal_data_email_layout)
    LinearLayout emailLayout;

    @Bind(R.id.personal_data_phone_layout)
    LinearLayout phoneLayout;

    @Bind(R.id.personal_data_login_password_layout)
    LinearLayout loginPassWordLayout;

    @Override
    public void initialize(Bundle savedInstanceState) {
        toolbar.setTitle("个人资料");
    }

    @OnClick(R.id.personal_data_nick_name_layout)
    public void setOnclickNickName(View view){
        Intent intent = new Intent(this,NickNameActivity.class);
        if(intent != null){
            startActivityForResult(intent, 0);
        }else{
            Log.i("AAAA","intent==null");
        }
    }

    @OnClick(R.id.personal_data_birthday_layout)
    public void setOnclickBirthday(View view){
        Intent intent = new Intent(this,BirthdayActivity.class);
        if(intent != null){
            startActivityForResult(intent, 0);
        }else{
            Log.i("AAAA","intent==null");
        }
    }

    @OnClick(R.id.personal_data_zwjs_layout)
    public void setOnclickZwjs(View view){
        Intent intent = new Intent(this,SelfIntroductionActivity.class);
        if(intent != null){
            startActivityForResult(intent, 0);
        }else{
            Log.i("AAAA","intent==null");
        }
    }

    @OnClick(R.id.personal_data_phone_layout)
    public void setOnclickPhone(View view){
        Intent intent = new Intent(this,PhoneActivity.class);
        if(intent != null){
            startActivityForResult(intent, 0);
        }else{
            Log.i("AAAA","intent==null");
        }
    }

    @OnClick(R.id.personal_data_email_layout)
    public void setOnclickEmail(View view){
        Intent intent = new Intent(this,EmailActivity.class);
        if(intent != null){
            startActivityForResult(intent, 0);
        }else{
            Log.i("AAAA","intent==null");
        }
    }

    @OnClick(R.id.personal_data_login_password_layout)
    public void setOnclickLoginPassword(View view){
        Intent intent = new Intent(this,UpdatePassWordActivity.class);
        if(intent != null){
            startActivityForResult(intent, 0);
        }else{
            Log.i("AAAA","intent==null");
        }
    }
}
