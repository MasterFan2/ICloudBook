package com.masterfan.cloudbook.activity.home.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.Util.Dbutils;
import com.masterfan.cloudbook.activity.home.entity.Book;
import com.masterfan.cloudbook.activity.home.entity.BookResp;
import com.masterfan.cloudbook.http.net.HttpClient;
import com.masterfan.cloudbook.view.BookAdapter;
import com.masterfan.cloudbook.view.BookLayout;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * txt图书阅读页面
 * Created by sunzj on 2016/3/25.
 */

public class BookReadMainActivity extends Activity {
    DbManager db;
    ProgressDialog pd;
    private int bookid;
    private int page = 1;
    private int rows = 1000;
    List<Map<String, Object>> str;
    BookAdapter ba;
    BookLayout bk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bk = new BookLayout(this);
        bk.setFocusableInTouchMode(true);
        str = new ArrayList<Map<String, Object>>();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("image",R.drawable.logo);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("text", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("text", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("text", "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("text", "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
//        str.add(map);
        str.add(map1);
//        str.add(map);
        str.add(map2);
        str.add(map3);
        str.add(map4);
//        str.add(map);
        BookAdapter ba = new BookAdapter(this);
        ba.addItem(str);
        bk.setPageAdapter(ba);
        setContentView(bk);
        bookid = getIntent().getIntExtra("bookid",0);
        db = x.getDb(Dbutils.getConfig());
        pd = ProgressDialog.show(this, "提示", "加载中……");
        HttpClient.setDb(db);
        HttpClient.getInstance().txtBookRead(page, rows, bookid, txtBookReadCallback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        //groundId是菜单所处的组的名字
        //itemId是当前菜单的id
        //order当前排序
        //title是显示的内容
        menu.add(0, 1, 1, "所有商品");
        menu.add(0, 3, 3, "退出程序");
        return super.onCreateOptionsMenu(menu);
    }

    //当客户点击菜单中某一个选项时，调用该方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if(item.getItemId() == 1){
            this.finish();
        }else if(item.getItemId() == 3){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 获取txt图书阅读返回
     */
    private Callback<BookResp> txtBookReadCallback = new Callback<BookResp>() {
        @Override
        public void success(BookResp bookResp, Response response) {
            if(pd != null){
                pd.dismiss();
            }
            if(bookResp != null) {
                if(bookResp.getCode()== 404){
                    //contentTxt.setText("无权阅读或图书不存在");
                }else if(bookResp.getCode()== 402){
                    //contentTxt.setText("图书不是txt格式");
                    Book book = bookResp.getBook();
                }else if(bookResp.getCode()== 500){
                   // contentTxt.setText("服务器中尚未保存此txt图书内容，无法读取");
                    Book book = bookResp.getBook();
                }else if(bookResp.getCode()== 200){
                    //contentTxt.setText(bookResp.getContent());
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("text", bookResp.getContent()+"");
                    str.add(map);
                }
            }
        }

        @Override
        public void failure(RetrofitError error) {
            if(pd != null){
                pd.dismiss();
            }
            Log.i("AAAA", "error" + error.toString());

        }
    };

}