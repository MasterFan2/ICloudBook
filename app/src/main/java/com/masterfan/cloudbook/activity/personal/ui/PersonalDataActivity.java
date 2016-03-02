package com.masterfan.cloudbook.activity.personal.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.Util.DialogUtils;
import com.masterfan.cloudbook.activity.personal.util.DialogLoadingUtil;
import com.masterfan.cloudbook.activity.personal.util.GetPictureBack;
import com.masterfan.cloudbook.activity.personal.util.ImageUploadGetUrl;
import com.masterfan.library.dialog.normal.MTFDialog;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.T;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 个人资料
 * Created by Administrator on 2016/1/20 0020.
 */
@MTFActivityFeature(layout = R.layout.activity_personal_data, status_bar_color = R.color.colorPrimary)
public class PersonalDataActivity extends MTFBaseActivity implements GetPictureBack {

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

    @Bind(R.id.person_data_birthday_textview)
    TextView birthdayTxt;

    @Bind(R.id.personal_data_head_imageView)
    ImageView headImage;

    private DialogLoadingUtil dialogLoadingUtil;

    private ImageUploadGetUrl getPictureUtil;

    @Override
    public void initialize(Bundle savedInstanceState) {

    }

    @OnClick(R.id.personal_data_head_imageView)
    public void setHeadImage(View view){
        getPictureUtil = new ImageUploadGetUrl(PersonalDataActivity.this);
        getPictureUtil.showdiaViewwithAspect(PersonalDataActivity.this,PersonalDataActivity.this.findViewById(R.id.personal_data_head_imageView),1,1);
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
        showTimeDialog(1);
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

    /**
     * 显示日期选择对话框
     *
     * @param tag 1:选择开始时间；2：选择结束时间
     */
    private void showTimeDialog(final int tag) {
        View view = getLayoutInflater().inflate(R.layout.dialog_datatime_layout, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        /**关闭DatePicker、TimePicker、NumberPicker的可编辑模式*/
        datePicker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        Date time = new Date();
        /**设置几点和几分--  不设置三星手机不能显示24小时制*/
        resizePikcer(datePicker);//调整datepicker大小
        //设置年月日
        Calendar cal = Calendar.getInstance();
        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
        final Dialog dialog = DialogUtils.showCustomDialogNoTitle(this, view);
        TextView aTxt = (TextView) dialog.findViewById(R.id.datatime_layout_1);//取消
        TextView bTxt = (TextView) dialog.findViewById(R.id.datatime_layout_2);//确定
        dialog.show();
        aTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        bTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(datePicker, tag);
                dialog.dismiss();
            }
        });
    }
    /**
     * 调整FrameLayout大小
     * @param tp
     */
    private void resizePikcer(FrameLayout tp){
        List<NumberPicker> npList = findNumberPicker(tp);
        for(NumberPicker np:npList){
            resizeNumberPicker(np);
        }
    }
    /**
     * 调整numberpicker大小
     * @param np
     */
    private void resizeNumberPicker(NumberPicker np){
        /**120 代表宽度*/
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(280, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 0, 5, 0);
        np.setLayoutParams(params);
    }

    /**
     * 得到viewGroup里面的numberpicker组件
     * @param viewGroup
     * @return
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup){
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if(null != viewGroup){
            for(int i = 0; i < viewGroup.getChildCount(); i++){
                child = viewGroup.getChildAt(i);
                if(child instanceof NumberPicker){
                    npList.add((NumberPicker)child);
                }
                else if(child instanceof LinearLayout){
                    List<NumberPicker> result = findNumberPicker((ViewGroup)child);
                    if(result.size()>0){
                        return result;
                    }
                }
            }
        }
        return npList;
    }
    /**
     * 设置开始结束和截至报名时间
     *
     * @param datePicker
     * @param tag 1：开始时间；2：结束时间；3：截至报名时间
     */
    private void setTime(DatePicker datePicker, int tag) {
        String month = datePicker.getMonth() + 1 + "";
        String day = datePicker.getDayOfMonth() + "";


        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        if(tag == 1) {
            birthdayTxt.setText(datePicker.getYear() + "-" + month + "-" + day);
        }else if(tag == 2){

        }

    }

    @Override
    public void getBack(String url) {
        Log.i("AAAA","getBack 收到返回了，图片地址："+url);
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        // imageView.setImageURI(Uri.fromFile(new File(filePath)));
        headImage.setImageBitmap(bitmap);
        File file2 = new File(url);
        if(file2 == null){
            T.s(PersonalDataActivity.this, "图片保存失败");
            return;
        }
        // myProfileModel.uploadImg(pushImgListener, file2);//这里就是上传图片了
        Log.i("AAAA","图片地址："+file2.getPath());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(getPictureUtil != null){
            getPictureUtil.onActivityResult(requestCode, resultCode, data);
        }
    }
}
