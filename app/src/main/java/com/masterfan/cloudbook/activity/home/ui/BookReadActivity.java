package com.masterfan.cloudbook.activity.home.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.Util.Dbutils;
import com.masterfan.cloudbook.activity.home.entity.Book;
import com.masterfan.cloudbook.activity.home.entity.BookResp;
import com.masterfan.cloudbook.activity.home.entity.BooksResp;
import com.masterfan.cloudbook.activity.home.entity.DetailResp;
import com.masterfan.cloudbook.http.net.HttpClient;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import org.xutils.DbManager;
import org.xutils.x;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * txt图书阅读页面
 * Created by sunzj on 2016/3/25.
 */
@MTFActivityFeature(layout = R.layout.activity_book_read )
public class BookReadActivity extends MTFBaseActivity {

    DbManager db;
    ProgressDialog pd;
    private int bookid;
    private int page = 1;
    private int rows = 1000;
    @Bind(R.id.book_read_content_textview)
    TextView contentTxt;
    @Override
    public void initialize(Bundle savedInstanceState) {
        bookid = getIntent().getIntExtra("bookid",0);
        db = x.getDb(Dbutils.getConfig());
        pd = ProgressDialog.show(this, "提示", "加载中……");
        HttpClient.setDb(db);
        HttpClient.getInstance().txtBookRead(page,rows,bookid, txtBookReadCallback);

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
                    contentTxt.setText("无权阅读或图书不存在");
                }else if(bookResp.getCode()== 402){
                    contentTxt.setText("图书不是txt格式");
                    Book book = bookResp.getBook();
                }else if(bookResp.getCode()== 500){
                    contentTxt.setText("服务器中尚未保存此txt图书内容，无法读取");
                    Book book = bookResp.getBook();
                }else if(bookResp.getCode()== 200){
                    contentTxt.setText(bookResp.getContent());
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
