package com.masterfan.cloudbook.activity.personal.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.entity.Book;
import com.masterfan.cloudbook.activity.personal.entity.Comment;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.S;
import com.masterfan.library.utils.T;
import com.masterfan.library.widget.recyclerview.MTFEndlessRecyclerOnScrollListener;
import com.masterfan.library.widget.recyclerview.MTFLoadingFooter;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewAdapterWrapper;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewStateUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 上传图书
 */
@MTFActivityFeature(layout=R.layout.activity_my_upload)
public class MyUploadListActivity extends MTFBaseActivity implements SwipeRefreshLayout.OnRefreshListener{


    private MTFRecyclerViewAdapterWrapper adapterWrapper;
    private MyAdapter adapter;
    private LayoutInflater mLayoutInflater;

    @Bind(R.id.comment_recyclerView_refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private final int PAGE_COUNT = 33;//总大小
    private final int PAGE_SIZE  = 10;//每页大小
    private final int PAGE_INDEX = 1; //第几页
    private int currentSize = 0;
    //recyclerView data
    private ArrayList<Book> dataList = new ArrayList<>();

    @Bind(R.id.comment_recyclerView)
    RecyclerView recyclerView;


    @Override
    public void initialize(Bundle savedInstanceState) {
        initMettingFrament();
    }

    private void initMettingFrament() {
        for (int i = 1; i < 3; i++) {
            dataList.add(new Book("红楼梦"+i,"曹雪芹","这是一本好书","古典文学",0));
            dataList.add(new Book("红楼梦"+(3+i),"曹雪芹","这是一本好书","古典文学",1));
            dataList.add(new Book("红楼梦"+(6+i),"曹雪芹","这是一本好书","古典文学",2));
        }

        currentSize = dataList .size();
        mLayoutInflater = LayoutInflater.from(context);
        adapter = new MyAdapter();
        adapterWrapper = new MTFRecyclerViewAdapterWrapper(adapter);
        recyclerView.setAdapter(adapterWrapper);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        /**这2句是添加headView*/
//        View headerView = LayoutInflater.from(context).inflate(R.layout.test_recyclerview_header, null, false);
//        MTFRecyclerViewUtils.setHeaderView(recyclerView, headerView);

        recyclerView.addOnScrollListener(mOnScrollListener);

        refreshLayout.setOnRefreshListener(this);//添加refresh Listener
    }

    private MTFEndlessRecyclerOnScrollListener mOnScrollListener = new MTFEndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            S.o("next page");
            MTFLoadingFooter.State state = MTFRecyclerViewStateUtils.getFooterViewState(recyclerView);
            if(state == MTFLoadingFooter.State.Loading) {
                S.o("the state is Loading, just wait..");
                return;
            }

            if (recyclerView.getAdapter().getItemCount() < PAGE_COUNT) {
                // loading more
                MTFRecyclerViewStateUtils.setFooterViewState(context, recyclerView, PAGE_SIZE, MTFLoadingFooter.State.Loading, null);
                requestData();
            } else {
                //the end
                MTFRecyclerViewStateUtils.setFooterViewState(context, recyclerView, PAGE_SIZE, MTFLoadingFooter.State.TheEnd, null);
            }
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    for (int i = 11; i < 21; i++) {
                        dataList.add(new Book("红楼梦"+i,"曹雪芹","这是一本好书","古典文学",0));
                    }
                    adapter.notifyDataSetChanged();
                    MTFRecyclerViewStateUtils.setFooterViewState(recyclerView, MTFLoadingFooter.State.Normal);
                    break;
                case 1:
                    refreshLayout.setRefreshing(false);
                    T.s(context, "刷新成功");
                    adapter.notifyDataSetChanged();
                    break;
            }

        }
    };

    private void requestData() {
        handler.sendEmptyMessageDelayed(0, 1500);
    }

    @Override
    public void onRefresh() {
        dataList .clear();
        for (int i = 21; i < 31; i++) {
            dataList.add(new Book("红楼梦"+i,"曹雪芹","这是一本好书","古典文学",0));
        }
        currentSize = dataList.size();
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MTFViewHolder> {

        @Override
        public MTFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MTFViewHolder(mLayoutInflater.inflate(R.layout.item_my_upload, parent, false));
        }

        @Override
        public void onBindViewHolder(MTFViewHolder holder, int position) {
            Book book = dataList.get(position);
            holder.bookNameTxt.setText(book.getBookName());
            holder.writerTxt.setText(book.getWriter());
            holder.introductionTxt.setText(book.getIntroduction());
            holder.typeTxt.setText(book.getBookType());
            if(book.getStatus() == 0) {
                holder.statusTxt.setText("己通过");
                holder.statusTxt.setTextColor(getResources().getColor(R.color.base_green));
            }else if(book.getStatus() == 1){
                holder.statusTxt.setText("未通过");
                holder.statusTxt.setTextColor(getResources().getColor(R.color.orange));
            }else if(book.getStatus() == 2){
                holder.statusTxt.setText("审核中");
                holder.statusTxt.setTextColor(getResources().getColor(R.color.gray));
            }else{
                holder.statusTxt.setText("未知");
                holder.statusTxt.setTextColor(getResources().getColor(R.color.black));
            }


        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MTFViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_my_upload_title_img_imageView)
            ImageView titleImg;

            @Bind(R.id.item_my_upload_book_name_textview)
            TextView bookNameTxt;//

            @Bind(R.id.item_my_upload_writer_textview)
            TextView writerTxt;//

            @Bind(R.id.item_my_upload_introduction_textview)
            TextView introductionTxt;//

            @Bind(R.id.item_my_upload_book_type_textview)
            TextView typeTxt;//

            @Bind(R.id.item_my_upload_check_status_textview)
            TextView statusTxt;//

            public MTFViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
