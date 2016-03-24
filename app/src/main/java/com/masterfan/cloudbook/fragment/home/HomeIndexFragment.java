package com.masterfan.cloudbook.fragment.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
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
import com.masterfan.cloudbook.Util.GetSystemInfoUtils;
import com.masterfan.cloudbook.Util.SystemInfo;
import com.masterfan.cloudbook.activity.home.entity.Books;
import com.masterfan.cloudbook.activity.home.entity.BooksResp;
import com.masterfan.cloudbook.activity.home.entity.FragmentIndexBookEntity;
import com.masterfan.cloudbook.activity.home.ui.BookDetailActivity;
import com.masterfan.cloudbook.activity.home.ui.MPDFViewActivity;
import com.masterfan.cloudbook.activity.main.HomeActivity;
import com.masterfan.cloudbook.activity.manamgment.entity.ClassRead;
import com.masterfan.cloudbook.activity.personal.entity.ExitState;
import com.masterfan.cloudbook.http.bean.Tokens;
import com.masterfan.cloudbook.http.bean.UserInfo;
import com.masterfan.cloudbook.http.bean.UserResp;
import com.masterfan.cloudbook.http.net.HttpClient;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;
import com.masterfan.library.utils.S;
import com.masterfan.library.utils.T;
import com.masterfan.library.widget.recyclerview.MTFEndlessRecyclerOnScrollListener;
import com.masterfan.library.widget.recyclerview.MTFLoadingFooter;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewAdapterWrapper;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewStateUtils;
import com.squareup.picasso.Picasso;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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

//    private ArrayList<FragmentIndexBookEntity> dataList = new ArrayList<>();
    private ArrayList<Books> data = new ArrayList<>();

    @Bind(R.id.fragment_home_recyclerView)
    RecyclerView recyclerView;

    private final int PAGE_COUNT = 3;//总大小
    private final int PAGE_SIZE  = 1;//每页大小
    private int PAGE_INDEX = 1; //第几页
    private int currentSize = 0;
    private GridLayoutManager gridLayoutManager;
    private ProgressDialog pd;

    private int type = 0;//0 第一次加载，1。刷新，2，加载更多
    DbManager db;
    @Override
    public void initialize() {
        db = x.getDb(Dbutils.getConfig());
        HttpClient.setDb(db);
        pd = ProgressDialog.show(getActivity(), "提示", "加载中……");
        type = 0;
        initMettingFrament();
        Log.i("AAAA", "type " + type + "   PAGE_INDEX " + PAGE_INDEX + " PAGE_SIZE " + PAGE_SIZE);
        HttpClient.getInstance().bookList(PAGE_INDEX, PAGE_SIZE, callback);
    }

    /**
     * 获取图书列表返回
     */
    private Callback<BooksResp> callback = new Callback<BooksResp>() {
        @Override
        public void success(BooksResp booksResp, Response response) {
            pd.dismiss();// 关闭ProgressDialog
            if(booksResp != null) {
                Log.i("AAAA", "success");
                if(booksResp.getBooks()!= null){
                    Log.i("AAAA", "success" +
                            ";"+booksResp.getBooks().size());
                    if(type==0) {
                        data = booksResp.getBooks();
                        adapter.notifyDataSetChanged();
                    }else if(type == 1){
                        data.clear();
                        data = booksResp.getBooks();
                        T.s(context, "刷新成功");
                        refreshLayout.setRefreshing(false);
                        MTFRecyclerViewStateUtils.setFooterViewState(recyclerView, MTFLoadingFooter.State.Normal);
                        adapter.notifyDataSetChanged();
                    }else if(type == 2){
                        data.addAll(booksResp.getBooks());
                        adapter.notifyDataSetChanged();
                        currentSize = data.size();
                        if(booksResp.getBooks().size()==0) {
                            MTFRecyclerViewStateUtils.setFooterViewState(recyclerView, MTFLoadingFooter.State.TheEnd);
                        }else{
                            MTFRecyclerViewStateUtils.setFooterViewState(recyclerView, MTFLoadingFooter.State.Normal);
                        }
                    }
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

    private void initMettingFrament() {

        currentSize = data .size();
        mLayoutInflater = LayoutInflater.from(context);
        adapter = new MyAdapter();
        adapterWrapper = new MTFRecyclerViewAdapterWrapper(adapter);
        recyclerView.setAdapter(adapterWrapper);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);//每一行显示3个
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
                case 1:

                    break;
            }

        }
    };

    /**
     * 加载更多
     */
    private void requestData() {
        type = 2;
        PAGE_INDEX = PAGE_INDEX + 1;
        Log.i("AAAA", "type "+type+"   PAGE_INDEX " + PAGE_INDEX + " PAGE_SIZE " + PAGE_SIZE);
        HttpClient.getInstance().bookList(PAGE_INDEX, PAGE_SIZE, callback);

    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        data .clear();
        type = 1;
        PAGE_INDEX = 1;
        Log.i("AAAA", "type "+type+"   PAGE_INDEX " + PAGE_INDEX + " PAGE_SIZE " + PAGE_SIZE);
        HttpClient.getInstance().bookList(PAGE_INDEX, PAGE_SIZE, callback);


    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MTFViewHolder> {

        @Override
        public MTFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MTFViewHolder(mLayoutInflater.inflate(R.layout.item_fragment_home_index, parent, false));
        }

        @Override
        public void onBindViewHolder(MTFViewHolder holder, int position) {
            final Books item = data.get(position);
            holder.nameTxt.setText(item.getTitle());
            holder.writerTxt.setText(item.getAuthor()+"");
            try {
                Picasso.with(context).load("http://g.hiphotos.baidu.com/image/pic/item/e1fe9925bc315c60c2cafcd18eb1cb13495477b5.jpg").into(holder.bookImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.bookImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),BookDetailActivity.class);
                    intent.putExtra("bookid",item.getId());
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
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
