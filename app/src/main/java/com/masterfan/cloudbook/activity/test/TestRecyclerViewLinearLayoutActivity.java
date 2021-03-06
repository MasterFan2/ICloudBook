package com.masterfan.cloudbook.activity.test;

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
import com.masterfan.cloudbook.activity.test.bean.Item;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.S;
import com.masterfan.library.utils.T;
import com.masterfan.library.widget.recyclerview.MTFEndlessRecyclerOnScrollListener;
import com.masterfan.library.widget.recyclerview.MTFLoadingFooter;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewAdapterWrapper;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewStateUtils;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

@MTFActivityFeature(layout = R.layout.activity_test_recycler_view_linear_layout, status_bar_color = R.color.colorPrimary)
public class TestRecyclerViewLinearLayoutActivity extends MTFBaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private final int PAGE_COUNT = 33;//总大小
    private final int PAGE_SIZE  = 10;//每页大小
    private final int PAGE_INDEX = 1; //第几页

    private int currentSize = 0;

    private LayoutInflater mLayoutInflater;

    //recyclerView data
    private ArrayList<Item> dataList = new ArrayList<>();

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private MTFRecyclerViewAdapterWrapper adapterWrapper;
    private MyAdapter adapter;

    @Override
    public void initialize(Bundle savedInstanceState) {

        for (int i = 0; i < 10; i++) {
            dataList.add(new Item(i, String.valueOf(i)));
        }

        currentSize = dataList .size();
        mLayoutInflater = LayoutInflater.from(context);
        adapter = new MyAdapter();
        adapterWrapper = new MTFRecyclerViewAdapterWrapper(adapter);
        recyclerView.setAdapter(adapterWrapper);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        View headerView = LayoutInflater.from(context).inflate(R.layout.test_recyclerview_header, null, false);
        MTFRecyclerViewUtils.setHeaderView(recyclerView, headerView);
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
                    for (int i = 0; i < 10; i++) {
                        dataList.add(new Item(i, String.valueOf(i)));
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
        for (int i = 0; i < 10; i++) {
            dataList.add(new Item(i, String.valueOf(i)));
        }
        currentSize = dataList.size();
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MTFViewHolder> {

        @Override
        public MTFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MTFViewHolder(mLayoutInflater.inflate(R.layout.test_sample_item_text, parent, false));
        }

        @Override
        public void onBindViewHolder(MTFViewHolder holder, int position) {
            Item item = dataList.get(position);
            holder.textView.setText(item.getName());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MTFViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.info_text)
            TextView textView;

            public MTFViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
