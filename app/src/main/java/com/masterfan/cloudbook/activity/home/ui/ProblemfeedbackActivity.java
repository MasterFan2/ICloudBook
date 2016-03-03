package com.masterfan.cloudbook.activity.home.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.library.tiles.interfaces.ShaderType;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 问题反馈
 * Created by Administrator on 2016/1/27 0027.
 */
@MTFActivityFeature(layout = R.layout.activity_problem_feedback, status_bar_color = R.color.colorPrimary)
public class ProblemfeedbackActivity extends MTFBaseActivity {

    @Bind(R.id.problem_feedback_btn)
    Button btn;

    private boolean DEBUG = true;
    private String TAG = "AAAA";

    @Bind(R.id.problem_feedback_edittext)
    EditText mEditTextMsg;

    @Bind(R.id.problem_feedback_textview)
    TextView textView;



    @Override
    public void initialize(Bundle savedInstanceState) {
        mEditTextMsg.addTextChangedListener(new EditChangedListener());
    }

    @OnClick(R.id.problem_feedback_btn)
    public void onclickBtn(View view){
        animFinish();
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
            textView.setText((s.length())+" / 200");

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (DEBUG)
                Log.i(TAG, "输入文字后的状态");
            /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
            editStart = mEditTextMsg.getSelectionStart();
            editEnd = mEditTextMsg.getSelectionEnd();
            if (temp.length() > charMaxNum) {
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                mEditTextMsg.setText(s);
                mEditTextMsg.setSelection(tempSelection);
            }

        }
    };

}
