package com.masterfan.cloudbook.activity.manamgment.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.manamgment.entity.ClassRead;
import com.masterfan.cloudbook.activity.manamgment.entity.Classes;
import com.masterfan.cloudbook.activity.manamgment.entity.ClassesResp;
import com.masterfan.cloudbook.activity.manamgment.entity.GradeResp;
import com.masterfan.cloudbook.activity.manamgment.entity.Grades;
import com.masterfan.cloudbook.http.net.HttpClient;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.S;
import com.masterfan.library.utils.T;
import com.masterfan.library.widget.dropmenu.GirdDropDownAdapter;
import com.masterfan.library.widget.dropmenu.ListDropDownAdapter;
import com.masterfan.library.widget.dropmenu.MTFDropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogRecord;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * 阅读情况统计
 * Created by Administrator on 2016/2/5 0005.
 */
@MTFActivityFeature(layout = R.layout.activity_read_qingkuang_tongji,status_bar_color = R.color.colorPrimary)
public class ReadStatisticsActivity extends MTFBaseActivity {

    View linearLayout;

    @Bind(R.id.dropDownMenu)
    MTFDropDownMenu mDropDownMenu;

    private String headers[] = {"请选择年级", "请选择班级"};
    private String grades[];
    private String classs[];

    ArrayList<Grades> gradeResps;
    ArrayList<Classes> classResp;

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private List<View> popupViews = new ArrayList<>();

    ListView cityView;
    ListView ageView;
    private ProgressDialog pd;
    @Override
    public void initialize(Bundle savedInstanceState) {
        linearLayout = LayoutInflater. from(this).inflate(R.layout.activity_read_qingkuang_tongji_tubiao,null );
        pd = ProgressDialog.show(ReadStatisticsActivity.this, "提示", "请稍等……");
        HttpClient.getInstance().getGrade(GradesCallback);


    }

    private Callback<GradeResp> GradesCallback = new Callback<GradeResp>(){
            @Override
        public void success(GradeResp data, Response response) {
                pd.dismiss();
            if(data != null) {
                Log.i("AAAA", "ok:" + data.getGrades().get(0).getName());
                gradeResps = data.getGrades();
                handler.sendEmptyMessage(1);

            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.i("AAAA","err:"+error.toString());
        }
    };

    private Callback<ClassesResp> classesCallback = new Callback<ClassesResp>(){
        @Override
        public void success(ClassesResp data, Response response) {
            pd.dismiss();
            if(data != null) {
              classResp = data.getClasses();
                handler.sendEmptyMessage(2);

            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.i("AAAA","err:"+error.toString());
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("AAAA","进入CASE 1");
                    if(gradeResps != null && gradeResps.size()>0){
                        grades = new String [gradeResps.size()];
                        for(int i=0;i<gradeResps.size();i++){
                            grades[i] = gradeResps.get(i).getName();
                        }
                    }
                    cityView = new ListView(ReadStatisticsActivity.this);
                    cityAdapter = new GirdDropDownAdapter(ReadStatisticsActivity.this, Arrays.asList(grades));
                    cityView.setDividerHeight(0);
                    cityView.setAdapter(cityAdapter);
                    popupViews.add(cityView);
                    cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            cityAdapter.setCheckItem(position);
                            mDropDownMenu.setTabText(grades[position]);
                            mDropDownMenu.closeMenu();
                            pd = ProgressDialog.show(ReadStatisticsActivity.this, "提示", "请稍等……");
                            HttpClient.getInstance().getClasses(gradeResps.get(position).getId(), classesCallback);
                        }
                    });
                    ageView = new ListView(ReadStatisticsActivity.this);
                    ageView.setDividerHeight(0);
                    classs = new String[]{};
                    ageAdapter = new ListDropDownAdapter(ReadStatisticsActivity.this, Arrays.asList(classs));
                    ageView.setAdapter(ageAdapter);

                    popupViews.add(ageView);

                    ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ageAdapter.setCheckItem(position);
                            mDropDownMenu.setTabText(classs[position]);
                            mDropDownMenu.closeMenu();
                        }
                    });
                    mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, linearLayout);
                    break;
                case 2:
                    Log.i("AAAA", "进入CASE 2");
                    if(classResp != null && classResp.size()>0){
                        classs = new String[classResp.size()];
                        for(int i=0;i<classResp.size();i++){
                            classs[i] = classResp.get(i).getName();
                        }
                    }
                    ageAdapter.setDate(Arrays.asList(classs));
                    ageAdapter.notifyDataSetChanged();
                    //init age menu


                    break;
                default:break;
            }
        }
    };
}
