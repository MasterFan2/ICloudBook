package com.masterfan.cloudbook.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.Util.Dbutils;
import com.masterfan.cloudbook.Util.GetSystemInfoUtils;
import com.masterfan.cloudbook.Util.SystemInfo;
import com.masterfan.cloudbook.activity.main.HomeActivity;
import com.masterfan.cloudbook.activity.manamgment.entity.Classes;
import com.masterfan.cloudbook.activity.manamgment.entity.ClassesResp;
import com.masterfan.cloudbook.activity.manamgment.entity.GradeResp;
import com.masterfan.cloudbook.activity.manamgment.entity.Grades;
import com.masterfan.cloudbook.activity.personal.entity.ExitState;
import com.masterfan.cloudbook.activity.personal.util.T;
import com.masterfan.cloudbook.http.bean.Tokens;
import com.masterfan.cloudbook.http.bean.UserInfo;
import com.masterfan.cloudbook.http.bean.UserResp;
import com.masterfan.cloudbook.http.net.HttpClient;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.S;
import com.masterfan.library.widget.dropmenu.GirdDropDownAdapter;
import com.masterfan.library.widget.dropmenu.ListDropDownAdapter;
import com.masterfan.library.widget.dropmenu.MTFDropDownMenu;
import com.masterfan.library.widget.pickerWheel.TimePopupWindow;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@MTFActivityFeature(layout = R.layout.activity_login, status_bar_color = R.color.colorPrimary)
public class LoginActivity extends MTFBaseActivity {

    EditText usernameEdit;

    EditText passwordEdit;

    Button loginBtn;

    TextView forgetTxt;

    public static final int LOGIN_UNAME_IS_NULL = 0xff01;//user name is null;
    public static final int LOGIN_UPWD_IS_NULL = 0xff02;//password is null;
    public static final int LOGIN_PWD_SHORT = 0xff03;//password too short;
    public static final int LOGIN_VERIFY_SUCCESS = 0xff04;//verify success;

    DbManager db;
    private ProgressDialog pd;
    private UserInfo userInfo;

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
    private int gradeId = -1;//-1表示没有选择，
    private int classId = -1;//-1表示没有选择，


    @Override
    public void initialize(Bundle savedInstanceState) {
        db = x.getDb(Dbutils.getConfig());
        HttpClient.setDb(db);
        try {
            userInfo = db.findFirst(UserInfo.class);
            if (userInfo != null) {
                ExitState.setIsLogin(1);
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                animFinish();
            } else {
                linearLayout = LayoutInflater.from(this).inflate(R.layout.activity_login_two, null);
                usernameEdit = (EditText) linearLayout.findViewById(R.id.login_username_edit);
                passwordEdit = (EditText) linearLayout.findViewById(R.id.login_password_edit);
                loginBtn = (Button) linearLayout.findViewById(R.id.login_login_btn);
                forgetTxt = (TextView) linearLayout.findViewById(R.id.login_forget_password_textview);

                loginBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String uname = usernameEdit.getText().toString().trim();
                        final String pwd = passwordEdit.getText().toString().trim();
                        int verifyResult = verify(uname, pwd);

                        if (verifyResult == LOGIN_UNAME_IS_NULL) T.s(context, "用户名不能为空");
                        else if (verifyResult == LOGIN_UPWD_IS_NULL) T.s(context, "密码不能为空");
                        else if (verifyResult == LOGIN_PWD_SHORT) T.s(context, "密码太短");
                        else if (verifyResult == LOGIN_VERIFY_SUCCESS) {
                            if (gradeId == -1) {
                                T.s(LoginActivity.this, "请选择年级~");
                                return;
                            }
                            if (classId == -1) {
                                T.s(LoginActivity.this, "请选择班级~");
                                return;
                            } else {
                                pd = ProgressDialog.show(LoginActivity.this, "提示", "登陆中……");
                                HttpClient.getInstance().login(uname, pwd, classId, callback);
                            }
                        }

                    }
                });
                forgetTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        if (intent != null) {
                            animStart(intent);
                            animFinish();
                        }
                    }
                });
                pd = ProgressDialog.show(LoginActivity.this, "提示", "请稍等……");
                HttpClient.getInstance().getGrade(GradesCallback);

            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }


    /**
     * 验证账号和密码合法性
     */
    public int verify(String userName, String password) {
        // ...
        if (TextUtils.isEmpty(userName)) return LOGIN_UNAME_IS_NULL;

        //...
        if (TextUtils.isEmpty(password)) return LOGIN_UPWD_IS_NULL;

        //...
        if (password.trim().length() < 6) return LOGIN_PWD_SHORT;

        //...
        return LOGIN_VERIFY_SUCCESS;
    }

    /**
     * 登陆成功返回
     */
    private Callback<UserResp> callback = new Callback<UserResp>() {
        @Override
        public void success(UserResp userResp, Response response) {
            pd.dismiss();// 关闭ProgressDialog
            if (userResp.getCode() == 200) {
                Log.i("AAAA", userResp.toString());
                saveData(userResp);
            } else if (userResp.getCode() == 400) {
                T.s(LoginActivity.this, "用户名或密码错误");
            } else if (userResp.getCode() == 401) {
                T.s(LoginActivity.this, "用户被禁用");
            } else {
                //error
                Log.i("AAAA", "userResp.getCode()==" + userResp.getCode());
            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.i("AAAA", "error" + error.toString());
            T.s(LoginActivity.this, "登陆失败，请重新登录");
        }
    };

    public void saveData(UserResp userResp){
        if (userResp.getUser() != null) {
            UserInfo userInfo = userResp.getUser();
            try {
                UserInfo oldUserInfo = db.findFirst(UserInfo.class);
                if (oldUserInfo != null) {
                    db.delete(oldUserInfo);
                }
                db.save(userInfo);
                try{
                    SystemInfo oldInfo = db.findFirst(SystemInfo.class);
                    if(oldInfo != null){
                        db.delete(oldInfo);
                    }
                    SystemInfo systemInfo = SystemInfo.getSystemInfo();
                    systemInfo.setToken(userResp.getToken());
                    systemInfo.setGradeId(gradeId + "");
                    systemInfo.setClassesId(classId + "");
                    systemInfo.setOs(Build.MODEL);
                    systemInfo.setOs_version(Build.VERSION.RELEASE);
                    systemInfo.setApp_version(GetSystemInfoUtils.getAPPVersionCodeFromAPP(this) + "");
                    systemInfo.setUserId(userInfo.getId() + "");
                    db.save(systemInfo);
                }catch(Exception e){ }
                ExitState.setIsLogin(1);
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                animFinish();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取年级返回
     */
    private Callback<GradeResp> GradesCallback = new Callback<GradeResp>() {
        @Override
        public void success(GradeResp data, Response response) {
            if (pd != null) {
                pd.dismiss();
            }
            if (data != null) {
                Log.i("AAAA", "ok:" + data.getGrades().get(0).getName());
                gradeResps = data.getGrades();
                handler.sendEmptyMessage(1);

            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.i("AAAA", "err:" + error.toString());
        }
    };

    private Callback<ClassesResp> classesCallback = new Callback<ClassesResp>() {
        @Override
        public void success(ClassesResp data, Response response) {
            pd.dismiss();
            if (data != null) {
                classResp = data.getClasses();
                handler.sendEmptyMessage(2);

            }
        }

        @Override
        public void failure(RetrofitError error) {
            Log.i("AAAA", "err:" + error.toString());
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.i("AAAA", "进入CASE 1");
                    if (gradeResps != null && gradeResps.size() > 0) {
                        grades = new String[gradeResps.size()];
                        for (int i = 0; i < gradeResps.size(); i++) {
                            grades[i] = gradeResps.get(i).getName();
                        }
                    }
                    cityView = new ListView(LoginActivity.this);
                    cityAdapter = new GirdDropDownAdapter(LoginActivity.this, Arrays.asList(grades));
                    cityView.setDividerHeight(0);
                    cityView.setAdapter(cityAdapter);
                    popupViews.add(cityView);
                    cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            cityAdapter.setCheckItem(position);
                            mDropDownMenu.setTabText(grades[position]);
                            mDropDownMenu.closeMenu();
                            classId = -1;
                            gradeId = gradeResps.get(position).getId();
                            pd = ProgressDialog.show(LoginActivity.this, "提示", "请稍等……");
                            HttpClient.getInstance().getClasses(gradeResps.get(position).getId(), classesCallback);
                        }
                    });
                    ageView = new ListView(LoginActivity.this);
                    ageView.setDividerHeight(0);
                    classs = new String[]{};
                    ageAdapter = new ListDropDownAdapter(LoginActivity.this, Arrays.asList(classs));
                    ageView.setAdapter(ageAdapter);

                    popupViews.add(ageView);

                    ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ageAdapter.setCheckItem(position);
                            mDropDownMenu.setTabText(classs[position]);
                            classId = classResp.get(position).getId();
                            Log.i("AAAA", "名称：" + classResp.get(position).getName() + "  classId=" + classId);
                            mDropDownMenu.closeMenu();
                        }
                    });
                    mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, linearLayout);
                    break;
                case 2:
                    Log.i("AAAA", "进入CASE 2");
                    if (classResp != null && classResp.size() > 0) {
                        classs = new String[classResp.size()];
                        for (int i = 0; i < classResp.size(); i++) {
                            classs[i] = classResp.get(i).getName();
                        }
                    }
                    ageAdapter.setDate(Arrays.asList(classs));
                    ageAdapter.notifyDataSetChanged();
                    //init age menu
                    break;
                default:
                    break;
            }
        }
    };
}
