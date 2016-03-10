package com.masterfan.cloudbook.activity.manamgment.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.manamgment.entity.PersonRead;
import com.masterfan.cloudbook.activity.manamgment.entity.ReadCountAEntity;
import com.masterfan.cloudbook.activity.manamgment.entity.ReadCountBEntity;
import com.masterfan.cloudbook.activity.manamgment.entity.ReadCountCEntity;
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
import butterknife.OnClick;

/**
 * 图书综合数据管理
 */
@MTFActivityFeature(layout = R.layout.activity_book_data_management,status_bar_color = R.color.colorPrimary)
public class BookDataManagementActivity extends MTFBaseActivity implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.book_data_management_read_count_layout)
    LinearLayout readCountALayout;//阅读次数A排行

    @Bind(R.id.book_data_management_read_comment_collect_count_layout)
    LinearLayout readCountBLayout;//阅读次数B排行

    @Bind(R.id.book_data_management_read_time_comment_collect_count_layout)
    LinearLayout readCountCLayout;//阅读次数C排行

    @Bind(R.id.book_data_management_book_name_textview)
    TextView bookNameTxt;//书名

    @Bind(R.id.book_data_management_writer_textview)
    TextView writerTxt;//作者

    @Bind(R.id.book_data_management_type_textview)
    TextView typeTxt;//类型

    @Bind(R.id.book_data_management_read_count_textview)
    TextView readCountTxt;//阅读次数

    @Bind(R.id.book_data_management_comment_count_textview)
    TextView commentCountTxt;//评论数

    @Bind(R.id.book_data_management_collect_count_textview)
    TextView collectCountTxt;//收藏数

    @Bind(R.id.book_data_management_all_time_textview)
    TextView allTimeTxt;//总时长

    @Bind(R.id.book_data_management_all_comment_textview)
    TextView allCommentTxt;//总评论

    @Bind(R.id.book_data_management_all_collect_textview)
    TextView allCollectTxt;//总收藏


    private MTFRecyclerViewAdapterWrapper adapterWrapper;
    private MyAdapter adapter;
    private MyBAdapter adapterB;
    private MyCAdapter adapterC;
    private LayoutInflater mLayoutInflater;

    @Bind(R.id.book_data_management_refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private final int PAGE_COUNT = 33;//总大小
    private final int PAGE_SIZE  = 10;//每页大小
    private final int PAGE_INDEX = 1; //第几页
    private int currentSize = 0;

    //recyclerView data
    private ArrayList<ReadCountAEntity> dataAList = new ArrayList<>();
    private ArrayList<ReadCountBEntity> dataBList = new ArrayList<>();
    private ArrayList<ReadCountCEntity> dataCList = new ArrayList<>();

    @Bind(R.id.book_data_management_recyclerView)
    RecyclerView recyclerView;

    /**记录是哪一个*/
    int type=1;

    @Override
    public void initialize(Bundle savedInstanceState) {
        mLayoutInflater = LayoutInflater.from(context);

        initMettingFrament();
    }

    private void initMettingFrament() {

        if(type==1){
            for (int i = 0; i < 10; i++) {
                dataAList.add(new ReadCountAEntity("红楼梦","曹雪芹","古典图书","4"));
            }
            currentSize = dataAList .size();
            adapter = new MyAdapter();
            adapterWrapper = new MTFRecyclerViewAdapterWrapper(adapter);
        }else if(type  == 2){
            for (int i = 0; i < 10; i++) {
                dataBList.add(new ReadCountBEntity("红楼梦","曹雪芹","古典图书","313","323","333"));
            }
            currentSize = dataBList .size();
            adapterB = new MyBAdapter();
            adapterWrapper = new MTFRecyclerViewAdapterWrapper(adapterB);
        }else if(type == 3){
            for (int i = 0; i < 10; i++) {
                dataCList.add(new ReadCountCEntity("红楼梦","曹雪芹","古典图书","144","222","443"));
            }
            currentSize = dataCList .size();
            adapterC = new MyCAdapter();
            adapterWrapper = new MTFRecyclerViewAdapterWrapper(adapterC);
        }
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
    private void requestData() {
        handler.sendEmptyMessageDelayed(0, 1500);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    if(type==1){
                        for (int i = 0; i < 10; i++) {
                            dataAList.add(new ReadCountAEntity("红楼梦","曹雪芹","古典图书","6"));
                        }
                        adapter.notifyDataSetChanged();
                    }else if(type  == 2){
                        for (int i = 0; i < 10; i++) {
                            dataBList.add(new ReadCountBEntity("红楼梦","曹雪芹","古典图书","313","323","333"));
                        }
                        adapterB.notifyDataSetChanged();
                    }else if(type == 3){
                        for (int i = 0; i < 10; i++) {
                            dataCList.add(new ReadCountCEntity("红楼梦","曹雪芹","古典图书","144","222","443"));
                        }
                        adapterC.notifyDataSetChanged();
                    }

                    adapter.notifyDataSetChanged();
                    MTFRecyclerViewStateUtils.setFooterViewState(recyclerView, MTFLoadingFooter.State.Normal);
                    break;
                case 1:
                    refreshLayout.setRefreshing(false);
                    T.s(context, "刷新成功");
                    if(type==1){
                        adapter.notifyDataSetChanged();
                    }else if(type  == 2){
                        adapterB.notifyDataSetChanged();
                    }else if(type == 3){
                        adapterC.notifyDataSetChanged();
                    }
                    break;
            }

        }
    };
    @OnClick(R.id.book_data_management_read_count_layout)
    public void onChickMyCollect(View view){

        readCountALayout.setBackground(getResources().getDrawable(R.drawable.bg_textview_green_all_one));
        readCountBLayout.setBackground(getResources().getDrawable(R.drawable.bg_textview_green_one));
        readCountCLayout.setBackground(getResources().getDrawable(R.drawable.bg_textview_green_one));


        readCountTxt.setVisibility(View.VISIBLE);
        commentCountTxt.setVisibility(View.GONE);
         collectCountTxt.setVisibility(View.GONE);
         allTimeTxt.setVisibility(View.GONE);
         allCommentTxt.setVisibility(View.GONE);
         allCollectTxt.setVisibility(View.GONE);
        type=1;
        initMettingFrament();
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.book_data_management_read_comment_collect_count_layout)
    public void onChickBookFriendRecommend(View view){

        readCountALayout.setBackground(getResources().getDrawable(R.drawable.bg_textview_green_one));
        readCountBLayout.setBackground(getResources().getDrawable(R.drawable.bg_textview_green_all_one));
        readCountCLayout.setBackground(getResources().getDrawable(R.drawable.bg_textview_green_one));

        readCountTxt.setVisibility(View.VISIBLE);
        commentCountTxt.setVisibility(View.VISIBLE);
        collectCountTxt.setVisibility(View.VISIBLE);
        allTimeTxt.setVisibility(View.GONE);
        allCommentTxt.setVisibility(View.GONE);
        allCollectTxt.setVisibility(View.GONE);
        type=2;
        initMettingFrament();
        adapterB.notifyDataSetChanged();
    }

    @OnClick(R.id.book_data_management_read_time_comment_collect_count_layout)
    public void onChickMyRecommend(View view){

        readCountALayout.setBackground(getResources().getDrawable(R.drawable.bg_textview_green_one));
        readCountBLayout.setBackground(getResources().getDrawable(R.drawable.bg_textview_green_one));
        readCountCLayout.setBackground(getResources().getDrawable(R.drawable.bg_textview_green_all_one));

        readCountTxt.setVisibility(View.GONE);
        commentCountTxt.setVisibility(View.GONE);
        collectCountTxt.setVisibility(View.GONE);
        allTimeTxt.setVisibility(View.VISIBLE);
        allCommentTxt.setVisibility(View.VISIBLE);
        allCollectTxt.setVisibility(View.VISIBLE);
        type=3;
        initMettingFrament();
        adapterC.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {

        if(type==1){
            dataAList .clear();
            for (int i = 0; i < 10; i++) {
                dataAList.add(new ReadCountAEntity("红楼梦","曹雪芹","古典图书","5"));
            }
            currentSize = dataAList .size();
        }else if(type  == 2){
            dataBList .clear();
            for (int i = 0; i < 10; i++) {
                dataBList.add(new ReadCountBEntity("红楼梦","曹雪芹","古典图书","313","323","333"));
            }
            currentSize = dataBList .size();
        }else if(type == 3){
            dataCList .clear();
            for (int i = 0; i < 10; i++) {
                dataCList.add(new ReadCountCEntity("红楼梦","曹雪芹","古典图书","144","222","443"));
            }
            currentSize = dataCList .size();
        }


        handler.sendEmptyMessageDelayed(1, 2000);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MTFViewAHolder> {

        @Override
        public MTFViewAHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MTFViewAHolder(mLayoutInflater.inflate(R.layout.item_read_count_a, parent, false));
        }
        @Override
        public void onBindViewHolder(MTFViewAHolder holder, int position) {
                ReadCountAEntity item = dataAList.get(position);
                holder.bookNameTxt.setText(item.getBookName());
                holder.writerTxt.setText("" + item.getWriter());
                holder.typeTxt.setText("" + item.getType());
                holder.readCountTxt.setText("" + item.getReadCount());


        }

        @Override
        public int getItemCount() {
            if (type == 1) {
                return dataAList.size();
            } else if (type == 2) {
                return dataBList.size();
            } else {
                return dataCList.size();
            }
        }

        public class MTFViewAHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_read_comment_booknane_textview)
            TextView bookNameTxt;//书名

            @Bind(R.id.item_read_comment_writer_textview)
            TextView writerTxt;//作者

            @Bind(R.id.item_read_comment_type_textview)
            TextView typeTxt;//类型

            @Bind(R.id.item_read_comment_read_count_textview)
            TextView readCountTxt;//阅读次数


            public MTFViewAHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        public class MTFViewBAHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_read_comment_booknane_textview)
            TextView bookNameTxt;//书名

            @Bind(R.id.item_read_comment_writer_textview)
            TextView writerTxt;//作者

            @Bind(R.id.item_read_comment_type_textview)
            TextView typeTxt;//类型

            @Bind(R.id.item_read_comment_read_count_textview)
            TextView readCountTxt;//阅读次数

            @Bind(R.id.item_read_comment_b_comment_count_textview)
            TextView commentCountTxt;//评论数

            @Bind(R.id.item_read_comment_b_collect_count_textview)
            TextView collectCountTxt;//收藏数

            public MTFViewBAHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        public class MTFViewCHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_read_comment_booknane_textview)
            TextView bookNameTxt;//书名

            @Bind(R.id.item_read_comment_writer_textview)
            TextView writerTxt;//作者

            @Bind(R.id.item_read_comment_type_textview)
            TextView typeTxt;//类型


            @Bind(R.id.item_read_comment_c_time_textview)
            TextView allTimeTxt;//总时长

            @Bind(R.id.item_read_comment_c_comment_textview)
            TextView allCommentTxt;//总评论

            @Bind(R.id.item_read_comment_c_collect_textview)
            TextView allCollectTxt;//总收藏

            public MTFViewCHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    public class MyBAdapter extends RecyclerView.Adapter<MyBAdapter.MTFViewBAHolder> {

        @Override
        public MTFViewBAHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MTFViewBAHolder(mLayoutInflater.inflate(R.layout.item_read_count_b, parent, false));
        }
        @Override
        public void onBindViewHolder(MTFViewBAHolder holder, int position) {
                ReadCountBEntity item = dataBList.get(position);
                holder.bookNameTxt.setText(item.getBookName());
                holder.writerTxt.setText("" + item.getWriter());
                holder.readCountTxt.setText("" + item.getReadCount());
                holder.commentCountTxt.setText("" + item.getCommentCount());
                holder.collectCountTxt.setText("" + item.getCollectCount());


        }

        @Override
        public int getItemCount() {
                return dataBList.size();
        }



        public class MTFViewBAHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_read_comment_booknane_textview)
            TextView bookNameTxt;//书名

            @Bind(R.id.item_read_comment_writer_textview)
            TextView writerTxt;//作者

            @Bind(R.id.item_read_comment_type_textview)
            TextView typeTxt;//类型

            @Bind(R.id.item_read_comment_read_count_textview)
            TextView readCountTxt;//阅读次数

            @Bind(R.id.item_read_comment_b_comment_count_textview)
            TextView commentCountTxt;//评论数

            @Bind(R.id.item_read_comment_b_collect_count_textview)
            TextView collectCountTxt;//收藏数

            public MTFViewBAHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }



    }

    public class MyCAdapter extends RecyclerView.Adapter<MyCAdapter.MTFViewCHolder> {

        @Override
        public MTFViewCHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MTFViewCHolder(mLayoutInflater.inflate(R.layout.item_read_count_c, parent, false));
        }
        @Override
        public void onBindViewHolder(MTFViewCHolder holder, int position) {
                ReadCountCEntity item = dataCList.get(position);
                holder.bookNameTxt.setText(item.getBookName());
                holder.writerTxt.setText("" + item.getWriter());
                holder.typeTxt.setText("" + item.getType());
                holder.allTimeTxt.setText(item.getAllTime());
                holder.allCommentTxt.setText("" + item.getAllComment());
                holder.allCollectTxt.setText("" + item.getAllCollect());


        }

        @Override
        public int getItemCount() {
                return dataCList.size();
        }


        public class MTFViewCHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_read_comment_booknane_textview)
            TextView bookNameTxt;//书名

            @Bind(R.id.item_read_comment_writer_textview)
            TextView writerTxt;//作者

            @Bind(R.id.item_read_comment_type_textview)
            TextView typeTxt;//类型


            @Bind(R.id.item_read_comment_c_time_textview)
            TextView allTimeTxt;//总时长

            @Bind(R.id.item_read_comment_c_comment_textview)
            TextView allCommentTxt;//总评论

            @Bind(R.id.item_read_comment_c_collect_textview)
            TextView allCollectTxt;//总收藏

            public MTFViewCHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
