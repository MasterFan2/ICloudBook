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
import android.widget.TextView;
import android.widget.Toast;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.entity.Person;
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

/**
 *我关注的书友
 */
@MTFFragmentFeature(layout = R.layout.fragment_my_attention)
public class MineAttentionFragment extends MTFBaseFragment implements SwipeRefreshLayout.OnRefreshListener{

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
	private ArrayList<Person> dataList = new ArrayList<>();

	@Bind(R.id.comment_recyclerView)
	RecyclerView recyclerView;


	@Override
	public void initialize() {
		initMettingFrament();
	}

	private void initMettingFrament() {
		for (int i = 1; i < 3; i++) {
			dataList.add( new Person("张三","老师",11,11,11,"我关注的"));
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
			dataList.add( new Person("张三","老师",11,11,11,"我关注的"));
		}
		currentSize = dataList.size();
		handler.sendEmptyMessageDelayed(1, 2000);
	}

	public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MTFViewHolder> {

		@Override
		public MTFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new MTFViewHolder(mLayoutInflater.inflate(R.layout.item_book_friend, parent, false));
		}

		@Override
		public void onBindViewHolder(MTFViewHolder holder, final int position) {
			Person person = dataList.get(position);
			if (person != null) {
				holder.nameTxt.setText(person.getName());
				holder.occupationTxt.setText(person.getOccupation());
				holder.readTxt.setText("阅读："+person.getRead());
				holder.commentTxt.setText("评论："+person.getComment());
				holder.collectTxt.setText("收藏："+person.getCollect());
				holder.messageTxt.setText("寄语：" + person.getForMessage());
				holder.attentionTxt.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(getActivity(), "取消关注" + dataList.get(position).getName(), Toast.LENGTH_SHORT).show();
					}
				});

				holder.privateCallTxt.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(getActivity(), "私聊" + dataList.get(position).getName(), Toast.LENGTH_SHORT).show();
					}
				});
			}

		}

		@Override
		public int getItemCount() {
			return dataList.size();
		}

		public class MTFViewHolder extends RecyclerView.ViewHolder {

			@Bind(R.id.item_book_friend_title_img_imageView)
			ImageView titleImg;

			@Bind(R.id.item_book_friend_name_textview)
			TextView nameTxt;//

			@Bind(R.id.item_book_friend_occupation_textview)
			TextView occupationTxt;//

			@Bind(R.id.item_book_friend_collect_textview)
			TextView collectTxt;//

			@Bind(R.id.item_book_friend_read_textview)
			TextView readTxt;//

			@Bind(R.id.item_book_friend_comment_textview)
			TextView commentTxt;//

			@Bind(R.id.item_book_friend_message_textview)
			TextView messageTxt;

			@Bind(R.id.item_book_friend_attention_textview)
			TextView attentionTxt;

			@Bind(R.id.item_book_friend_private_call_textview)
			TextView privateCallTxt;



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
						dataList.add( new Person("张三","老师",11,11,11,"我关注的"));
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

}
