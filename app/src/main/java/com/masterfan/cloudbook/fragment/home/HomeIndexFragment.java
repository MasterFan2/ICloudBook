package com.masterfan.cloudbook.fragment.home;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.home.entity.FragmentIndexBookEntity;
import com.masterfan.cloudbook.activity.manamgment.entity.ClassRead;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;
import com.masterfan.library.utils.S;
import com.masterfan.library.utils.T;
import com.masterfan.library.widget.recyclerview.MTFEndlessRecyclerOnScrollListener;
import com.masterfan.library.widget.recyclerview.MTFLoadingFooter;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewAdapterWrapper;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewStateUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页
 * Created by 13510 on 2016/1/19.
 */
@MTFFragmentFeature(layout = R.layout.fragment_home_index)
public class HomeIndexFragment extends MTFBaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private MTFRecyclerViewAdapterWrapper adapterWrapper;
    private MyAdapter adapter;
    private LayoutInflater mLayoutInflater;

    @Bind(R.id.fragment_home_recyclerView_refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private ArrayList<FragmentIndexBookEntity> dataList = new ArrayList<>();

    @Bind(R.id.fragment_home_recyclerView)
    RecyclerView recyclerView;

    private final int PAGE_COUNT = 33;//总大小
    private final int PAGE_SIZE  = 10;//每页大小
    private final int PAGE_INDEX = 1; //第几页
    private int currentSize = 0;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void initialize() {
        initMettingFrament();
    }

    private void initMettingFrament() {
        dataList.add(new FragmentIndexBookEntity("http://n1.itc.cn/img8/wb/recom/2015/11/24/144837717308445247.jpeg", "赵丽颖", "赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://f.hiphotos.baidu.com/image/pic/item/2cf5e0fe9925bc3161866ee25ddf8db1ca1370f4.jpg","赵丽颖","赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://e.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d20b9727e379b033b5bb5b91a.jpg","赵丽颖","赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://e.hiphotos.baidu.com/image/pic/item/b90e7bec54e736d10ba4e37298504fc2d5626910.jpg","赵丽颖","赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://b.hiphotos.baidu.com/image/h%3D200/sign=0a728a5b7ff0f736c7fe4b013a55b382/908fa0ec08fa513d8781d0fa3a6d55fbb2fbd9ef.jpg","赵丽颖","赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://www.cnr.cn/ent/list/20151116/W020151116277914069193.jpg","赵丽颖","赵丽颖A"));

        currentSize = dataList .size();
        mLayoutInflater = LayoutInflater.from(context);
        adapter = new MyAdapter();
        adapterWrapper = new MTFRecyclerViewAdapterWrapper(adapter);
        recyclerView.setAdapter(adapterWrapper);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);


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
                MTFRecyclerViewStateUtils.setFooterViewState(getActivity(), recyclerView, PAGE_SIZE, MTFLoadingFooter.State.Loading, null);
                requestData();
            } else {
                //the end
                MTFRecyclerViewStateUtils.setFooterViewState(getActivity(), recyclerView, PAGE_SIZE, MTFLoadingFooter.State.TheEnd, null);
            }
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    dataList.add(new FragmentIndexBookEntity("http://n1.itc.cn/img8/wb/recom/2015/11/24/144837717308445247.jpeg", "赵丽颖", "赵丽颖A"));
                    dataList.add(new FragmentIndexBookEntity("http://f.hiphotos.baidu.com/image/pic/item/2cf5e0fe9925bc3161866ee25ddf8db1ca1370f4.jpg","赵丽颖","赵丽颖A"));
                    dataList.add(new FragmentIndexBookEntity("http://e.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d20b9727e379b033b5bb5b91a.jpg","赵丽颖","赵丽颖A"));
                    dataList.add(new FragmentIndexBookEntity("http://e.hiphotos.baidu.com/image/pic/item/b90e7bec54e736d10ba4e37298504fc2d5626910.jpg","赵丽颖","赵丽颖A"));
                    dataList.add(new FragmentIndexBookEntity("http://b.hiphotos.baidu.com/image/h%3D200/sign=0a728a5b7ff0f736c7fe4b013a55b382/908fa0ec08fa513d8781d0fa3a6d55fbb2fbd9ef.jpg","赵丽颖","赵丽颖A"));
                    dataList.add(new FragmentIndexBookEntity("http://www.cnr.cn/ent/list/20151116/W020151116277914069193.jpg","赵丽颖","赵丽颖A"));
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
        dataList.add(new FragmentIndexBookEntity("http://n1.itc.cn/img8/wb/recom/2015/11/24/144837717308445247.jpeg", "赵丽颖", "赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://f.hiphotos.baidu.com/image/pic/item/2cf5e0fe9925bc3161866ee25ddf8db1ca1370f4.jpg", "赵丽颖", "赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://e.hiphotos.baidu.com/image/pic/item/d8f9d72a6059252d20b9727e379b033b5bb5b91a.jpg","赵丽颖","赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://e.hiphotos.baidu.com/image/pic/item/b90e7bec54e736d10ba4e37298504fc2d5626910.jpg","赵丽颖","赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://b.hiphotos.baidu.com/image/h%3D200/sign=0a728a5b7ff0f736c7fe4b013a55b382/908fa0ec08fa513d8781d0fa3a6d55fbb2fbd9ef.jpg","赵丽颖","赵丽颖A"));
        dataList.add(new FragmentIndexBookEntity("http://www.cnr.cn/ent/list/20151116/W020151116277914069193.jpg","赵丽颖","赵丽颖A"));
        currentSize = dataList.size();
        handler.sendEmptyMessageDelayed(1, 2000);
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MTFViewHolder> {

        @Override
        public MTFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MTFViewHolder(mLayoutInflater.inflate(R.layout.item_fragment_home_index, parent, false));
        }

        @Override
        public void onBindViewHolder(MTFViewHolder holder, int position) {
            FragmentIndexBookEntity item = dataList.get(position);
            holder.nameTxt.setText(item.getBookName());
            holder.writerTxt.setText(item.getBookWriter()+"");
            try {
                Picasso.with(context).load(item.getBookImg()).into(holder.bookImg);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MTFViewHolder extends RecyclerView.ViewHolder {

            @Bind(R.id.item_fragment_home_index_book_img)
            ImageView bookImg;

            @Bind(R.id.item_fragment_home_index_book_name_textview)
            TextView nameTxt;

            @Bind(R.id.item_fragment_home_index_writer_textview)
            TextView writerTxt;



            public MTFViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
