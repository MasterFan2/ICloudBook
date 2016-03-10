package com.masterfan.cloudbook.http.net;

import android.content.Context;
import android.util.Log;

import com.masterfan.cloudbook.http.bean.UserResp;
import com.squareup.okhttp.OkHttpClient;

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
 * Created by Administrator on 2015/11/15.
 */
public class HttpClient {

//    private static final String BASE_URL = "http://116.255.235.119:1280/weatherForecastServer";


    private static final String BASE_URL = "http://116.255.235.119:1282/teachingAssistantInterface";

    private Context mContext;
    private RestAdapter restAdapter = null;
    private NetInterface netInterface = null;

    private static HttpClient instanse;

    public HttpClient() {
    }

    public static HttpClient getInstance() {
        if (instanse == null) {
            instanse = new HttpClient();
        }
        return instanse;
    }

    public void init(Context context){
        mContext = context;
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(new OkHttpClient().setCookieHandler(new CookieManager())))
                .setLogLevel(RestAdapter.LogLevel.FULL)//打印所有日志
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {

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
        @FormUrlEncoded
        void login(@Field("account")String account, @Field("password")String password, @Field("classid")int classId, Callback<UserResp> callback);

//        get  示例----后面要删除
//        @GET("/twitter/index")
//        void twitterList(@Query("pageIndex")int pageIndex, @Query("pageSize")int pageSize, Callback<TwitterResp> cb);
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

}
