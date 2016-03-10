package com.masterfan.cloudbook.activity.manamgment.ui;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.widget.dropmenu.GirdDropDownAdapter;
import com.masterfan.library.widget.dropmenu.ListDropDownAdapter;
import com.masterfan.library.widget.dropmenu.MTFDropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * 阅读情况统计
 * Created by Administrator on 2016/2/5 0005.
 */
@MTFActivityFeature(layout = R.layout.activity_read_qingkuang_tongji,status_bar_color = R.color.colorPrimary)
public class ReadStatisticsActivity extends MTFBaseActivity {

//    @Bind(R.id.read_qingkuang_tongji_select_nianji_textview)
//    TextView selectNianTxt;
//
//    @Bind(R.id.read_qingkuang_tongji_select_banji_textview)
//    TextView selectBanTxt;



    View linearLayout;

    @Bind(R.id.dropDownMenu)
    MTFDropDownMenu mDropDownMenu;
    private String headers[] = {"请选择年级", "请选择班级"};
    private String citys[] = {"不限", "2005级", "2006级", "2007级", "2008级", "2009级", "2010级", "2011级", "2012级", "2013级", "2014级", "2015级", "2016级"};
    private String ages[] = {"不限", "1班", "2班", "3班", "4班", "5班", "6班", "7班", "8班", "9班"};

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private List<View> popupViews = new ArrayList<>();
    @Override
    public void initialize(Bundle savedInstanceState) {
        linearLayout = LayoutInflater. from(this).inflate(R.layout.activity_read_qingkuang_tongji_tubiao,null );
        final ListView cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

        //init age menu
        final ListView ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        popupViews.add(cityView);
        popupViews.add(ageView);

        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, linearLayout);
    }
}
