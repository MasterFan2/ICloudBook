package com.masterfan.cloudbook.activity.manamgment.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.Util.DialogUtils;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.widget.dropmenu.GirdDropDownAdapter;
import com.masterfan.library.widget.dropmenu.ListDropDownAdapter;
import com.masterfan.library.widget.dropmenu.MTFDropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    TextView startTimeTxt;

    TextView endTimeTxt;

    Button findBtn;

    @Bind(R.id.dropDownMenu)
    MTFDropDownMenu mDropDownMenu;
    private String headers[] = {"请选择图书类别"};
    private String citys[] = {"不限", "古典", "言情", "诗歌", "戏剧", "散文", "英文"};

    private GirdDropDownAdapter cityAdapter;
    private List<View> popupViews = new ArrayList<>();

    private String starttime = "";
    private String endtime = "";

    View view;
    @Override
    public void initialize(Bundle savedInstanceState) {
        view = LayoutInflater. from(this).inflate(R.layout.activity_book_read_management_btn,null );
        startTimeTxt = (TextView) view.findViewById(R.id.book_read_management_start_time_textview);
        endTimeTxt = (TextView) view.findViewById(R.id.book_read_management_end_time_textview);
        findBtn = (Button) view.findViewById(R.id.book_read_management_find_btn);
        startTimeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(1);
            }
        });
        endTimeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(2);
            }
        });
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookReadManagementActivity.this, BookReadManagementListActivity.class);
                if(intent != null){
                    startActivity(intent);
                }
            }
        });
        final ListView cityView = new ListView(this);
        cityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);
        popupViews.add(cityView);
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });


        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, view);
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
            starttime = datePicker.getYear() + "-" + month + "-" + day ;
            startTimeTxt.setText(starttime);
        }else if(tag == 2){
            endtime = datePicker.getYear() + "-" + month + "-" + day ;
            endTimeTxt.setText(endtime);
        }

    }
}
