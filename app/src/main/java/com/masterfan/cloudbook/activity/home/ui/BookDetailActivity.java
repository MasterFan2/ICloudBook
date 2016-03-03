package com.masterfan.cloudbook.activity.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.util.T;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

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
    TextView downloadTxt;
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
        T.s(BookDetailActivity.this,"开始下载...");
    }
}