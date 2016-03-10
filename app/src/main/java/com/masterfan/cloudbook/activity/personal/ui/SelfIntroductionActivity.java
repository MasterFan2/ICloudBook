package com.masterfan.cloudbook.activity.personal.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.test.TestDropDownActivity;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 自我介绍
 * Created by Administrator on 2016/1/20 0020.
 */
@MTFActivityFeature(layout = R.layout.activity_self_introduction, status_bar_color = R.color.colorPrimary)
public class SelfIntroductionActivity extends MTFBaseActivity {

    @Bind(R.id.self_introduction_save_button)
    Button btn;

    @Bind(R.id.self_introduction_content_edittext)
    EditText contentEdit;

    private int count = 0;

    @OnClick(R.id.dropdown_btn)
    public void dropclick(View view ) {
        animStart(TestDropDownActivity.class);
    }

    private String TAG = "AAAA";
    private boolean DEBUG = true;

    @Bind(R.id.self_introduction_num_textview)
    TextView textView;

    @Override
    public void initialize(Bundle savedInstanceState) {
        contentEdit.addTextChangedListener(new EditChangedListener());
    }

    class EditChangedListener implements TextWatcher {
        private CharSequence temp;//监听前的文本
        private int editStart;//光标开始位置
        private int editEnd;//光标结束位置
        private final int charMaxNum = 200;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (DEBUG)
                Log.i(TAG, "输入文本之前的状态");
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (DEBUG)
                Log.i(TAG, "输入文字中的状态，count是一次性输入字符数");
            textView.setText("" + (s.length()) + " / 200");

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (DEBUG)
                Log.i(TAG, "输入文字后的状态");
            /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
            editStart = contentEdit.getSelectionStart();
            editEnd = contentEdit.getSelectionEnd();
            if (temp.length() > charMaxNum) {
                Toast.makeText(getApplicationContext(), "你输入的字数已经超过了限制！", Toast.LENGTH_LONG).show();
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                contentEdit.setText(s);
                contentEdit.setSelection(tempSelection);
            }
        }
    };


}
