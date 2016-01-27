package com.masterfan.cloudbook.activity.personal.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.library.dialog.normal.MTFDialog;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.T;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 个人资料
 * Created by Administrator on 2016/1/20 0020.
 */
@MTFActivityFeature(layout = R.layout.activity_personal_data, status_bar_color = R.color.colorPrimary)
public class PersonalDataActivity extends MTFBaseActivity {

    @Bind(R.id.personal_data_nick_name_layout)
    LinearLayout nickNameLayout;

    @Bind(R.id.personal_data_self_introduction_layout)
    LinearLayout zwjsLayout;

    @Bind(R.id.personal_data_birthday_layout)
    LinearLayout birthdayLayout;

    @Bind(R.id.personal_data_email_layout)
    LinearLayout emailLayout;

    @Bind(R.id.personal_data_phone_layout)
    LinearLayout phoneLayout;

    @Bind(R.id.personal_data_login_password_layout)
    LinearLayout loginPassWordLayout;

    @Bind(R.id.personal_data_class_layout)
    LinearLayout classLayout;

    @Bind(R.id.personal_data_name_layout)
    LinearLayout nameLayout;

    @Bind(R.id.personal_data_for_message_layout)
    LinearLayout forMessageLayout;

    @Bind(R.id.personal_data_sex_textview)
    TextView sexTxt;

    @Override
    public void initialize(Bundle savedInstanceState) {

    }

    @OnClick(R.id.personal_data_nick_name_layout)
    public void setOnclickNickName(View view) {
        Intent intent = new Intent(this, NickNameActivity.class);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            Log.i("AAAA", "intent==null");
        }
    }

    @OnClick(R.id.personal_data_birthday_layout)
    public void setOnclickBirthday(View view) {
        Intent intent = new Intent(this, BirthdayActivity.class);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            Log.i("AAAA", "intent==null");
        }
    }

    @OnClick(R.id.personal_data_self_introduction_layout)
    public void setOnclickSelfIntroduction(View view) {
        Intent intent = new Intent(this, SelfIntroductionActivity.class);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            Log.i("AAAA", "intent==null");
        }
    }

    @OnClick(R.id.personal_data_phone_layout)
    public void setOnclickPhone(View view) {
        Intent intent = new Intent(this, PhoneActivity.class);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            Log.i("AAAA", "intent==null");
        }
    }

    @OnClick(R.id.personal_data_email_layout)
    public void setOnclickEmail(View view) {
        Intent intent = new Intent(this, EmailActivity.class);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            Log.i("AAAA", "intent==null");
        }
    }


    @OnClick(R.id.personal_data_login_password_layout)
    public void setOnclickLoginPassword(View view) {
        Intent intent = new Intent(this, UpdatePassWordActivity.class);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            Log.i("AAAA", "intent==null");
        }
    }

    @OnClick(R.id.personal_data_class_layout)
    public void setOnclickClass(View view) {
        Intent intent = new Intent(this, ClassActivity.class);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            Log.i("AAAA", "intent==null");
        }
    }

    @OnClick(R.id.personal_data_name_layout)
    public void setOnclickName(View view) {
        Intent intent = new Intent(this, NameActivity.class);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            Log.i("AAAA", "intent==null");
        }
    }

    @OnClick(R.id.personal_data_for_message_layout)
    public void setOnclickForMessage(View view) {
        Intent intent = new Intent(this, ForMessageActivity.class);
        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            Log.i("AAAA", "intent==null");
        }
    }

    @OnClick(R.id.personal_data_sex_textview)
    public void setOnclickSex(View view) {
        selectSex();
    }

    public void selectSex() {
        final String[] genders = {"男", "女"};
        new MTFDialog.Builder(this)
                .title("请选择性别")
                .items(genders)
                .itemsCallbackSingleChoice(0, new MTFDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MTFDialog dialog, View itemView, int which, CharSequence text) {
                        sexTxt.setText(genders[which]);
                        return true;
                    }
                })
                .positiveText("确认选择")
                .show();
    }
}