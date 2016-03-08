package com.masterfan.cloudbook.activity.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.util.T;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.widget.probutton.MTFProcessButton;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 图书详情
 * Created by sunzj on 2016/3/3.
 */
@MTFActivityFeature(layout = R.layout.activity_book_detail, status_bar_color = R.color.colorPrimary)
public class BookDetailActivity extends MTFBaseActivity {

    @Bind(R.id.book_detail_start_read_textview)
    TextView startReadTxt;


    @Bind(R.id.book_detail_start_down_load_textview)
    MTFProcessButton downloadTxt;

    private int count = 0;

    @Override
    public void initialize(Bundle savedInstanceState) {

    }

    @OnClick(R.id.book_detail_start_read_textview)
    public void onclickStartRead(View view){
        Intent intent = new Intent(BookDetailActivity.this,MPDFViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.book_detail_start_down_load_textview)
    public void onclickDownLoad(View view){
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
        downloadTxt.setProgress(50);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    downloadTxt.setProgress(msg.arg1);
                    downloadTxt.setText("下载中    "+ msg.arg1 +"%");
                    break;
                case 0:
                    downloadTxt.setText("下载完成");
                    break;
                default:
                    break;
            }
        }
    };
}