package com.masterfan.cloudbook.activity.manamgment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 图书阅读情况管理
 * Created by Administrator on 2016/2/17 0017.
 */
@MTFActivityFeature(layout = R.layout.activity_book_read_management,status_bar_color = R.color.colorPrimary)
public class BookReadManagementActivity extends MTFBaseActivity{

    @Bind(R.id.book_read_management_book_type_layout)
    LinearLayout linearLayout;

    @Bind(R.id.book_read_management_start_time_textview)
    TextView startTimeTxt;

    @Bind(R.id.book_read_management_end_time_textview)
    TextView endTimeTxt;

    @Bind(R.id.book_read_management_find_btn)
    Button findBtn;

    @Override
    public void initialize(Bundle savedInstanceState) {

    }

    @OnClick(R.id.book_read_management_find_btn)
    public void onclickFindBtn(View view){
        Intent intent = new Intent(BookReadManagementActivity.this, BookReadManagementListActivity.class);
        if(intent != null){
            startActivity(intent);
        }
    }
}
