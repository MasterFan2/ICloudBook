package com.masterfan.cloudbook.activity.personal.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.entity.BookManagement;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;
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
 *我的图书
 */
@MTFFragmentFeature(layout = R.layout.fragment_my_book)
public class MyBookListFragment extends MTFBaseFragment implements SwipeRefreshLayout.OnRefreshListener{

	@Bind(R.id.my_book_my_collect_layout)
	LinearLayout myCollectLayout;//我的收藏

	@Bind(R.id.my_book_book_friend_recommend_layout)
	LinearLayout bookFriendRecommendLayout;//书友推荐

	@Bind(R.id.my_book_my_recommend_layout)
	LinearLayout myRecommendLayout;//我的推荐

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
	private ArrayList<BookManagement> dataList = new ArrayList<>();

	@Bind(R.id.comment_recyclerView)
	RecyclerView recyclerView;


	@Override
	public void initialize() {
		initMettingFrament();
	}

	private void initMettingFrament() {
		for (int i = 1; i < 3; i++) {
			dataList.add( new BookManagement("西游1记","吴承恩",12,"好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书"));
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
				MTFRecyclerViewStateUtils.setFooterViewState(getActivity(), recyclerView, PAGE_SIZE, MTFLoadingFooter.State.Loading, null);
				requestData();
			} else {
				//the end
				MTFRecyclerViewStateUtils.setFooterViewState(getActivity(), recyclerView, PAGE_SIZE, MTFLoadingFooter.State.TheEnd, null);
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
			dataList.add( new BookManagement("西游1记","吴承恩",12,"好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书"));
		}
		currentSize = dataList.size();
		handler.sendEmptyMessageDelayed(1, 2000);
	}

	public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MTFViewHolder> {

		@Override
		public MTFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new MTFViewHolder(mLayoutInflater.inflate(R.layout.item_my_books, parent, false));
		}

		@Override
		public void onBindViewHolder(MTFViewHolder holder, final int position) {
			BookManagement bookManagement = dataList.get(position);
			if (bookManagement != null) {
				holder.bookNmeTxt.setText(bookManagement.getBookNme());
				holder.writerTxt.setText(bookManagement.getWriter());
				holder.hasReadTxt.setText(""+bookManagement.getHasRead()+"%");
				holder.introductionTxt.setText(bookManagement.getIntroduction());
				holder.continueRedTxt.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(context, dataList.get(position).getBookNme() + "继续阅读", Toast.LENGTH_SHORT).show();
					}
				});
				holder.deleteTxt.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(context, dataList.get(position).getBookNme() + "删除", Toast.LENGTH_SHORT).show();
					}
				});
			}

		}

		@Override
		public int getItemCount() {
			return dataList.size();
		}

		public class MTFViewHolder extends RecyclerView.ViewHolder {

			@Bind(R.id.item_my_books_title_img)
			ImageView titleImg;

			@Bind(R.id.item_my_books_book_name_textview)
			TextView bookNmeTxt;//

			@Bind(R.id.item_my_books_writer_textview)
			TextView writerTxt;//

			@Bind(R.id.item_my_books_has_read_textview)
			TextView hasReadTxt;//

			@Bind(R.id.item_my_books_introduction_textview)
			TextView introductionTxt;

			@Bind(R.id.item_my_books_continue_read_textview)
			TextView continueRedTxt;

			@Bind(R.id.item_my_books_delete_textview)
			TextView deleteTxt;



			public MTFViewHolder(View itemView) {
				super(itemView);
				ButterKnife.bind(this, itemView);
			}
		}
	}

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case 0:
					for (int i = 11; i < 21; i++) {
						dataList.add( new BookManagement("西游1记","吴承恩",12,"好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书好书"));
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

	@OnClick(R.id.my_book_my_collect_layout)
	public void onChickMyCollect(View view){
		myCollectLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_all_one));
		bookFriendRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_one));
		myRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_one));
	}

	@OnClick(R.id.my_book_book_friend_recommend_layout)
	public void onChickBookFriendRecommend(View view){
		myCollectLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_one));
		bookFriendRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_all_one));
		myRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_one));
	}

	@OnClick(R.id.my_book_my_recommend_layout)
	public void onChickMyRecommend(View view){
		myCollectLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_one));
		bookFriendRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_one));
		myRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_all_one));
	}
}
