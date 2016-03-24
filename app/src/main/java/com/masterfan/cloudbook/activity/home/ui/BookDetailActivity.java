package com.masterfan.cloudbook.activity.home.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.Util.Dbutils;
import com.masterfan.cloudbook.activity.home.entity.Books;
import com.masterfan.cloudbook.activity.home.entity.BooksResp;
import com.masterfan.cloudbook.activity.home.entity.Comments;
import com.masterfan.cloudbook.activity.home.entity.CommentsResp;
import com.masterfan.cloudbook.activity.home.entity.Detail;
import com.masterfan.cloudbook.activity.home.entity.DetailResp;
import com.masterfan.cloudbook.activity.personal.entity.Comment;
import com.masterfan.cloudbook.activity.personal.util.T;
import com.masterfan.cloudbook.http.net.HttpClient;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.widget.probutton.MTFProcessButton;
import com.masterfan.library.widget.recyclerview.MTFLoadingFooter;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewAdapterWrapper;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewStateUtils;
import com.squareup.picasso.Picasso;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * 图书详情
 * Created by sunzj on 2016/3/3.
 */
@MTFActivityFeature(layout = R.layout.activity_book_detail, status_bar_color = R.color.colorPrimary)
public class BookDetailActivity extends MTFBaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.book_detail_start_read_textview)
    TextView startReadTxt;//开始阅读

    @Bind(R.id.book_detail_start_down_load_textview)
    MTFProcessButton downloadTxt;//下载

    @Bind(R.id.book_detail_book_name_textview)
    TextView bookNameTxt;//图书名

    @Bind(R.id.book_detail_book_writer_textview)
    TextView writerTxt;//作者

    @Bind(R.id.book_detail_book_type_textview)
    TextView typeTxt ;//类型

    @Bind(R.id.book_detail_book_collect_textview)
    TextView collectTxt;//收藏

    @Bind(R.id.book_detail_book_recommend_textview)
    TextView recommendTxt;//推荐

    @Bind(R.id.book_detail_book_count_textview)
    TextView countTxt;//总字数

    @Bind(R.id.book_detail_book_read_total_time_textview)
    TextView readTimeTxt;//已阅读时间

    @Bind(R.id.book_detail_write_comment_textview)
    TextView writeCommentTxt;
    //开始
    private int count = 0;

    private int bookid;
    DbManager db;
    ProgressDialog pd;
    private Detail detail;
    private ArrayList<Detail> data;

    private int page = 1;
    private int rows = 2;


    private MTFRecyclerViewAdapterWrapper adapterWrapper;
    private MyAdapter adapter;
    private LayoutInflater mLayoutInflater;

    @Bind(R.id.fragment_home_recyclerView_refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Bind(R.id.fragment_home_recyclerView)
    RecyclerView recyclerView;

    private ArrayList<Comments> commentdata = new ArrayList<>();//图书评论列表

    @Override
    public void initialize(Bundle savedInstanceState) {

        bookid = getIntent().getIntExtra("bookid",0);
        Log.i("AAAA","bookid="+bookid);
        db = x.getDb(Dbutils.getConfig());
        pd = ProgressDialog.show(this, "提示", "加载中……");
        HttpClient.setDb(db);
        HttpClient.getInstance().bookDetail(bookid, bookDetailCallback);

        mLayoutInflater = LayoutInflater.from(context);
        adapter = new MyAdapter();
        adapterWrapper = new MTFRecyclerViewAdapterWrapper(adapter);
        recyclerView.setAdapter(adapterWrapper);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        refreshLayout.setOnRefreshListener(this);//添加refresh Listener
    }
    /**
     * 获取图书详情返回
     */
    private Callback<DetailResp> bookDetailCallback = new Callback<DetailResp>() {
        @Override
        public void success(DetailResp detailResp, Response response) {
            if(pd != null){
                pd.dismiss();
            }
            if(detailResp != null) {
                data = detailResp.getDetail();
                setData(data);
                handler.sendEmptyMessage(2);
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

    /**
     * 获取图书评论列表返回
     */
    private Callback<CommentsResp> bookCommentCallback = new Callback<CommentsResp>() {
        @Override
        public void success(CommentsResp commentsResp, Response response) {
            if(pd != null){
                pd.dismiss();
            }
            if(commentsResp != null) {
                commentdata = commentsResp.getComments();
                refreshLayout.setRefreshing(false);
                MTFRecyclerViewStateUtils.setFooterViewState(recyclerView, MTFLoadingFooter.State.Normal);
                adapter.notifyDataSetChanged();

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
    public void  setData(ArrayList<Detail> data){
        if(data != null) {
            Log.i("AAAA", data.size() + ""+data.get(0).toString());
            bookNameTxt.setText(data.get(0).getIntro()+"");
            writerTxt.setText(data.get(0).getIntro()+"");
            typeTxt.setText(data.get(0).getBook()+"");
            collectTxt.setText(data.get(0).getFavorite() + "次");
            recommendTxt.setText(data.get(0).getRecommend() + "次");
            countTxt.setText(data.get(0).getDownload() + "字");
            readTimeTxt.setText(data.get(0).getReadtotaltime() + "");
        }else{
            T.s(this,"data==null");
        }

    }


    @OnClick(R.id.book_detail_start_read_textview)
    public void onclickStartRead(View view){
        Intent intent = new Intent(BookDetailActivity.this,MPDFViewActivity.class);
        startActivity(intent);
    }



    @OnClick(R.id.book_detail_write_comment_textview)
    public void onclickWriteComment(View view){
        Intent intent = new Intent(BookDetailActivity.this,BookReadActivity.class);
        intent.putExtra("bookid",bookid);
        startActivity(intent);
    }

    @OnClick(R.id.book_detail_start_down_load_textview)
    public void onclickDownLoad(View view){
        new Thread(){
            @Override
            public void run() {
                while (count < 100) {
                    count += 8;
                    if(count >= 100)
                        count = 100;
                    Message msg = handler.obtainMessage(1, count, 0);
                    msg.sendToTarget();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage(0, count, 0);
                msg.sendToTarget();
            }
        }.start();
        downloadTxt.setProgress(50);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    downloadTxt.setProgress(msg.arg1);
                    downloadTxt.setText("下载中    "+ msg.arg1 +"%");
                    break;
                case 0:
                    downloadTxt.setText("下载完成");
                    break;
                case 2:
                    pd = ProgressDialog.show(BookDetailActivity.this, "提示", "加载中……");
                    HttpClient.getInstance().bookComments(page, rows, bookid, bookCommentCallback);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onRefresh() {
        HttpClient.getInstance().bookComments(page, rows, bookid, bookCommentCallback);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MTFViewHolder> {

        @Override
        public MTFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MTFViewHolder(mLayoutInflater.inflate(R.layout.item_book_detail_comment, parent, false));
        }

        @Override
        public void onBindViewHolder(MTFViewHolder holder, int position) {
            final Comments item = commentdata.get(position);
            holder.userNameTxt.setText(item.getUserInfo().getNickname());
            holder.timeTxt.setText(item.getCreatetime()+"");
            holder.commentContentTxt.setText(item.getComment());
            holder.commentTxt.setText(item.getCommend()+"");
            holder.zanTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.s(BookDetailActivity.this,"赞！");
                }
            });
            holder.commentTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.s(BookDetailActivity.this,"评论");
                }
            });

        }

        @Override
        public int getItemCount() {
            return commentdata.size();
        }

        public class MTFViewHolder extends RecyclerView.ViewHolder {

//            @Bind(R.id.item_fragment_home_index_book_img)
//            ImageView bookImg;

            @Bind(R.id.item_book_detail_comment_username_textview)
            TextView userNameTxt;

            @Bind(R.id.item_book_detail_comment_class_textview)
            TextView classTxt;

            @Bind(R.id.item_book_detail_comment_comment_content_textview)
            TextView commentContentTxt;

            @Bind(R.id.item_book_detail_comment_time_textview)
            TextView timeTxt;

            @Bind(R.id.item_book_detail_comment_zan_textview)
            TextView zanTxt;

            @Bind(R.id.item_book_detail_comment_comment_textview)
            TextView commentTxt;



            public MTFViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}