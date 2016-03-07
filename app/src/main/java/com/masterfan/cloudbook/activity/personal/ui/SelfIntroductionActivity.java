package com.masterfan.cloudbook.activity.personal.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.widget.listview.MTFProgress;
import com.masterfan.library.widget.probutton.MTFProcessButton;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 自我介绍
 * Created by Administrator on 2016/1/20 0020.
 */
@MTFActivityFeature(layout = R.layout.activity_self_introduction, status_bar_color = R.color.colorPrimary)
public class SelfIntroductionActivity extends MTFBaseActivity {

    @Bind(R.id.progress_btn)
    MTFProcessButton btn;


    private int count = 0;

    @OnClick(R.id.progress_btn)
    public void click(View view) {

        new Thread(){
            @Override
            public void run() {
                while (count < 100) {
                    count += 8;
                    if(count >= 100)
                        count = 100;
                    Message msg = handler.obtainMessage(1, count, 0);
                    msg.sendToTarget();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage(0, count, 0);
                msg.sendToTarget();
            }
        }.start();
        btn.setProgress(50);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    btn.setProgress(msg.arg1);
                    btn.setText("下载中    "+ msg.arg1 +"%");
                    break;
                case 0:
                    btn.setText("下载完成");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void initialize(Bundle savedInstanceState) {

    }
}
