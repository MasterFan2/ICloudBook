package com.masterfan.cloudbook.http.net;

import android.content.Context;
import android.util.Log;

import com.masterfan.cloudbook.Util.GetSystemInfoUtils;
import com.masterfan.cloudbook.Util.SystemInfo;
import com.masterfan.cloudbook.activity.home.entity.BookResp;
import com.masterfan.cloudbook.activity.home.entity.BooksResp;
import com.masterfan.cloudbook.activity.home.entity.CommentsResp;
import com.masterfan.cloudbook.activity.home.entity.Detail;
import com.masterfan.cloudbook.activity.home.entity.DetailResp;
import com.masterfan.cloudbook.activity.manamgment.entity.ClassesResp;
import com.masterfan.cloudbook.activity.manamgment.entity.GradeResp;
import com.masterfan.cloudbook.activity.personal.entity.ExitState;
import com.masterfan.cloudbook.http.bean.UserResp;
import com.squareup.okhttp.OkHttpClient;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.net.CookieManager;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Http请求
 * Created by Administrator on 2015/11/15.
 */
public class HttpClient {

//    private static final String BASE_URL = "http://116.255.235.119:1280/weatherForecastServer";


    private static final String BASE_URL = "http://116.255.235.119:1282/teachingAssistantInterface";

    private Context mContext;
    private RestAdapter restAdapter = null;
    private NetInterface netInterface = null;

    private static HttpClient instanse;
    SystemInfo systemInfo;
    static DbManager db;
    public HttpClient() {
    }

    public static HttpClient getInstance() {
        if (instanse == null) {
            instanse = new HttpClient();
        }
        return instanse;
    }


    public static void setDb(DbManager dbManager) {
        db = dbManager;
    }

    public void init(final Context context){
        mContext = context;

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(new OkHttpClient().setCookieHandler(new CookieManager())))
                .setLogLevel(RestAdapter.LogLevel.FULL)//打印所有日志
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        int i = ExitState.getIsLogin();
                        if(i==1){
                            Log.i("AAAA","当前状态:登录");
                        }else{
                            Log.i("AAAA","当前状态:退出");
                        }

                        if( i == 1){
                            try {
                                systemInfo = db.findFirst(SystemInfo.class);
                                if(systemInfo != null) {
                                    request.addHeader("SCHOOLID", "1");
                                    request.addHeader("OS", systemInfo.getOs());
                                    request.addHeader("OS-VERSION", systemInfo.getOs_version());
                                    request.addHeader("APP-VERSION", systemInfo.getApp_version());
                                    request.addHeader("TOKEN", systemInfo.getToken());
                                    request.addHeader("USERID", "1");
                                    request.addHeader("GRADEID", systemInfo.getGradeId());
                                    request.addHeader("CLASSESID", systemInfo.getClassesId());
                                    Log.i("AAAA", systemInfo.toString());
                                }
                            } catch (DbException e) {
                                e.printStackTrace();
                            }

//                            SCHOOLID 当前app对应的学校的id，此id为固定死的值，直接在代码中写死
//                            OS 当前app所在的手机系统名称
//                            OS-VERSION 当前app所在的手机系统版本
//                            APP-VERSION 当前安装的app的版本号
//                            TOKEN 当前登录用户的token值
//                            USERID 当前登录用户的id
//                            GRADEID 当前登录用户的年级id
//                            CLASSESID 当前登录用户的班级id
                        }

//                        String temp = PreferenceUtils.getValue(mContext, PreferenceUtils.PREFERENCE_B_TOKEN, PreferenceUtils.DataType.STRING);
//                        if (!TextUtils.isEmpty(temp)) {
//                            // 设置JSESSIONID
//                            request.addHeader("btoken", temp);
//                        }
                    }
                })
                .build();

        netInterface = restAdapter.create(NetInterface.class);
    }

    //接口
    //@FormUrlEncoded   post请求
    //@Multipart        上传文件
    //@Field            post使用
    //@Query            get
    interface NetInterface {

        @POST("/userInfo/login")
        @FormUrlEncoded     //post请求要加这句
        void login(@Field("account")String account, @Field("password")String password, @Field("classid")int classId, Callback<UserResp> callback);
//        get  示例----后面要删除
//        @GET("/twitter/index")
//        void twitterList(@Query("pageIndex")int pageIndex, @Query("pageSize")int pageSize, Callback<TwitterResp> cb);

        @GET("/userInfo/schoolGrade")
        void getGrade(Callback<GradeResp> callback);


        @POST("/userInfo/schoolGradeClasses")
        @FormUrlEncoded     //post请求要加这句
        void getClasses(@Field("id")int id,Callback<ClassesResp> callback);


        @GET("/userInfo/schoolGrade")
        void exitLogin(Callback<Object > callback);

        @GET("/book/index")
        void bookList(@Query("page")int page, @Query("rows")int rows, Callback<BooksResp> callback);

        @GET("/book/bookDetail")
        void bookDetail(@Query("bookid")int bookid , Callback<DetailResp> callback);

        @GET("/book/bookComments")
        void bookComments(@Query("page")int page ,@Query("rows")int rows ,@Query("bookid")int bookid , Callback<CommentsResp> callback);

        @GET("/book/txtBookRead")
        void txtBookRead(@Query("page")int page ,@Query("rows")int rows ,@Query("bookid")int bookid , Callback<BookResp> callback);
    }


    /**
     * 登录
     *
     * @param account   账户名
     * @param password  密码
     * @param classId   班级ID
     * @param callback  回调
     */
    public void login(String account, String password, int classId, Callback<UserResp> callback) {
        Log.i("AAAA","login");
        netInterface.login(account, password, classId, callback);
    }

    /**
     * 获取年级
     * @param callback  回调
     */
    public void getGrade(Callback<GradeResp> callback) {
        netInterface.getGrade(callback);
    }

    /**
     * 获取班级
     * @param id
     * @param callback
     */
    public void getClasses(int id,Callback<ClassesResp> callback) {
        netInterface.getClasses(id, callback);
    }

    /**
     * 退出登陆
     * @param callback  回调
     */
    public void exitLogin(Callback<Object > callback) {
        netInterface.exitLogin(callback);
    }

    /**
     * 获取图书列表
     * @param page
     * @param rows
     * @param callback
     */
    public void bookList(int page,int rows, Callback<BooksResp> callback) {
        Log.i("AAAA","login");
        netInterface.bookList(page, rows, callback);
    }

    /**
     * 图书详情
     * @param bookid
     * @param callback
     */
    public void bookDetail(int bookid,Callback<DetailResp>callback){
        netInterface.bookDetail(bookid, callback);
    }

    /**
     * 图书评论列表
     * @param page
     * @param rows
     * @param bookid
     * @param callback
     */
    public void bookComments(int page,int rows,int bookid,Callback<CommentsResp>callback){
        Log.i("AAAA","参数："+page+"  "+rows+" "+bookid);
        netInterface.bookComments(page, rows, bookid, callback);
    }

    /**
     *txt图书阅读
     * @param page
     * @param rows
     * @param bookid
     * @param callback
     */
    public void txtBookRead(int page,int rows,int bookid,Callback<BookResp>callback){
        netInterface.txtBookRead(page, rows, bookid, callback);
    }
}
