package com.masterfan.cloudbook.activity.personal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

/**
 * 图书管理-最近查看，浏览
 * 数据待做--
 */
@MTFFragmentFeature(layout = R.layout.fragment_recently_viewed)
public class RecentlyViewedFragment extends MTFBaseFragment {

	@Bind(R.id.fragment_recently_viewed_listview)
	ListView listView;

	private PersonListAdapter adapter;


	@Override
	public void initialize() {
		initMettingFrament();
	}

	private void initMettingFrament() {
		// public Person(String name, String zhiye, int shoucang, int yuedu, int pinglun, String jiyu) {
		List<BookManagement> list = new ArrayList<BookManagement>();
		BookManagement bookManagement = new BookManagement("水浒传","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传1","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传2","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传3","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传4","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传5","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传6","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传7","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传8","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传9","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传10","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
		list.add(bookManagement);
		bookManagement = new BookManagement("水浒传11","作者是谁",25,12,14,99,45,"2016-1-22 16:01:48");
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
				convertView = inflater.inflate(R.layout.item_recently_viewed, null);
				holder = new ViewHolder();
				holder.titleImg = (ImageView) convertView.findViewById(R.id.item_works_management_title_img);
				holder.bookNmeTxt = (TextView) convertView.findViewById(R.id.item_works_management_book_name_textview);
				holder.writerTxt = (TextView) convertView.findViewById(R.id.item_works_management_writer_textview);
				holder.hasReadTxt = (TextView) convertView.findViewById(R.id.item_works_management_has_read_textview);
				holder.collectTxt = (TextView) convertView.findViewById(R.id.item_works_management_collect_textview);
				holder.downloadTxt = (TextView) convertView.findViewById(R.id.item_works_management_download_textview);
				holder.readTxt = (TextView) convertView.findViewById(R.id.item_works_management_read_textview);
				holder.commentTxt = (TextView) convertView.findViewById(R.id.item_works_management_comment_textview);
				holder.timeTxt = (TextView) convertView.findViewById(R.id.item_works_management_time_textview);
				holder.continueRedTxt = (TextView) convertView.findViewById(R.id.item_works_management_continue_read_textview);
				holder.deleteTxt = (TextView) convertView.findViewById(R.id.item_works_management_delete_textview);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			BookManagement bookManagement = list.get(position);
			if (bookManagement != null) {
				holder.bookNmeTxt.setText(bookManagement.getBookNme());
				holder.writerTxt.setText(bookManagement.getWriter());
				holder.hasReadTxt.setText(""+bookManagement.getHasRead()+"%");
				holder.collectTxt.setText(""+bookManagement.getCollect());
				holder.downloadTxt.setText("" + bookManagement.getDownload());
				holder.readTxt.setText(""+bookManagement.getRead());
				holder.commentTxt.setText(""+bookManagement.getComment());
				holder.timeTxt.setText(bookManagement.getTime());
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
			TextView collectTxt;//
			TextView downloadTxt;//
			TextView readTxt;
			TextView commentTxt;
			TextView timeTxt;

			TextView continueRedTxt;
			TextView deleteTxt;

		}

	}
}
