package com.masterfan.cloudbook.activity.personal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.entity.BookManagement;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 图书管理-我的图书
 * 数据待做--
 */
@MTFFragmentFeature(layout = R.layout.fragment_my_book)
public class MyBookFragment extends MTFBaseFragment {

	@Bind(R.id.fragment_my_book_listview)
	ListView listView;

	@Bind(R.id.my_book_my_collect_layout)
	LinearLayout myCollectLayout;//我的收藏

	@Bind(R.id.my_book_book_friend_recommend_layout)
	LinearLayout bookFriendRecommendLayout;//书友推荐

	@Bind(R.id.my_book_my_recommend_layout)
	LinearLayout myRecommendLayout;//我的推荐

	private PersonListAdapter adapter;




	@Override
	public void initialize() {
		initMettingFrament();
	}

	private void initMettingFrament() {
		List<BookManagement> list = new ArrayList<BookManagement>();
		BookManagement bookManagement = new BookManagement("西游1记","吴承恩",12,"一本好书一本好书一本好书一本好书一本好书一本好书");
		list.add(bookManagement);
		bookManagement = new BookManagement("西游2记","吴承恩",22,"一本好书一本好书一本好书一本好书一本好书一本好书");
		list.add(bookManagement);
		bookManagement = new BookManagement("西游3记","吴承恩",16,"一本好书一本好书一本好书一本好书一本好书一本好书");
		list.add(bookManagement);
		bookManagement = new BookManagement("西游4记","吴承恩",12,"一本好书一本好书一本好书一本好书一本好书一本好书");
		list.add(bookManagement);
		bookManagement = new BookManagement("西游5记","吴承恩",06,"一本好书一本好书一本好书一本好书一本好书一本好书");
		list.add(bookManagement);
		bookManagement = new BookManagement("西游6记","吴承恩",35,"一本好书一本好书一本好书一本好书一本好书一本好书");
		list.add(bookManagement);
		bookManagement = new BookManagement("西游7记","吴承恩",66,"一本好书一本好书一本好书一本好书一本好书一本好书");
		list.add(bookManagement);
		bookManagement = new BookManagement("西游8记","吴承恩",77,"一本好书一本好书一本好书一本好书一本好书一本好书");
		list.add(bookManagement);

		adapter = new PersonListAdapter(getActivity(),list);
		listView.setAdapter(adapter);//设置展示数据的适配器
	}

	public class PersonListAdapter extends BaseAdapter {
		/**
		 * 自定义图层
		 */
		private LayoutInflater inflater;

		private Activity mActivity;

		private List<BookManagement> list;

		public PersonListAdapter(Activity mActivity, List<BookManagement> list) {

			this.mActivity = mActivity;

			this.list = list;

		}
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			return list.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.item_my_books, null);
				holder = new ViewHolder();
				holder.titleImg = (ImageView) convertView.findViewById(R.id.item_my_books_title_img);
				holder.bookNmeTxt = (TextView) convertView.findViewById(R.id.item_my_books_book_name_textview);
				holder.writerTxt = (TextView) convertView.findViewById(R.id.item_my_books_writer_textview);
				holder.introductionTxt = (TextView) convertView.findViewById(R.id.item_my_books_introduction_textview);
				holder.hasReadTxt = (TextView) convertView.findViewById(R.id.item_my_books_has_read_textview);

				holder.continueRedTxt = (TextView) convertView.findViewById(R.id.item_my_books_continue_read_textview);
				holder.deleteTxt = (TextView) convertView.findViewById(R.id.item_my_books_delete_textview);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			BookManagement bookManagement = list.get(position);
			if (bookManagement != null) {
				holder.bookNmeTxt.setText(bookManagement.getBookNme());
				holder.writerTxt.setText(bookManagement.getWriter());
				holder.hasReadTxt.setText(""+bookManagement.getHasRead()+"%");
				holder.introductionTxt.setText(bookManagement.getIntroduction());
				holder.continueRedTxt.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(context, list.get(position).getBookNme() + "继续阅读", Toast.LENGTH_SHORT).show();
					}
				});
				holder.deleteTxt.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(context, list.get(position).getBookNme()+"删除",Toast.LENGTH_SHORT).show();
					}
				});
			}
			return convertView;
		}
		/**
		 * 自定义内部类
		 *
		 * @author shaozucheng
		 *
		 */
		class ViewHolder {
			ImageView titleImg;
			TextView bookNmeTxt;//
			TextView writerTxt;//
			TextView hasReadTxt;//
			TextView introductionTxt;

			TextView continueRedTxt;
			TextView deleteTxt;

		}

	}

	@OnClick(R.id.my_book_my_collect_layout)
	public void onChickMyCollect(View view){
		myCollectLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_all_1));
		bookFriendRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_1));
		myRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_1));
	}

	@OnClick(R.id.my_book_book_friend_recommend_layout)
	public void onChickBookFriendRecommend(View view){
		myCollectLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_1));
		bookFriendRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_all_1));
		myRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_1));
	}

	@OnClick(R.id.my_book_my_recommend_layout)
	public void onChickMyRecommend(View view){
		myCollectLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_1));
		bookFriendRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_1));
		myRecommendLayout.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_textview_all_1));
	}
}
