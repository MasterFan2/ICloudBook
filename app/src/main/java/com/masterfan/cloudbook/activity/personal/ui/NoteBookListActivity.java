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
import android.widget.TextView;

import com.masterfan.cloudbook.R;
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
 * 摘录笔记列表
 */
@MTFActivityFeature(layout=R.layout.activity_notebook)
public class NoteBookListActivity extends MTFBaseActivity implements SwipeRefreshLayout.OnRefreshListener{


    private MTFRecyclerViewAdapterWrapper adapterWrapper;
    private MyAdapter adapter;
    private LayoutInflater mLayoutInflater;

    @Bind(R.id.notebook_recyclerView_refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private final int PAGE_COUNT = 33;//总大小
    private final int PAGE_SIZE  = 10;//每页大小
    private final int PAGE_INDEX = 1; //第几页
    private int currentSize = 0;
    //recyclerView data
    private ArrayList<Comment> dataList = new ArrayList<>();

    @Bind(R.id.notebook_recyclerView)
    RecyclerView recyclerView;


    @Override
    public void initialize(Bundle savedInstanceState) {
        initMettingFrament();
    }

    private void initMettingFrament() {
        for (int i = 1; i < 11; i++) {
            dataList.add(new Comment("西游记","孙悟空大闹天宫"+i,"2012-12-12:02:02:02"));
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
                        dataList.add(new Comment("西游记","孙悟空大闹天宫"+i,"2012-12-12:02:02:02"));
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
            dataList.add(new Comment("西游记","孙悟空大闹天宫"+i,"2012-12-12:02:02:02"));
        }
        currentSize = dataList.size();
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MTFViewHolder> {

        @Override
        public MTFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MTFViewHolder(mLayoutInflater.inflate(R.layout.item_note_book, parent, false));
        }

        @Override
        public void onBindViewHolder(MTFViewHolder holder, int position) {
            Comment item = dataList.get(position);
            holder.bookNameTxt.setText(item.getBookName());
            holder.contentTxt.setText(item.getContent());
            holder.timeTxt.setText(item.getTime());


        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MTFViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_notebook_book_name_textview)
            TextView bookNameTxt;

            @Bind(R.id.item_notebook_content_textview)
            TextView contentTxt;

            @Bind(R.id.item_notebook_time_textview)
            TextView timeTxt;

            public MTFViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
