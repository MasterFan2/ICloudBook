package com.masterfan.cloudbook.activity.personal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.masterfan.cloudbook.R;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 关注我的书友
 */
@MTFFragmentFeature(layout = R.layout.fragment_gzwd)
public class GzwdFragment extends MTFBaseFragment {

	@Bind(R.id.fragment_gzwd_listview)
	ListView listView;

	PersonListAdapter adapter;


	@Override
	public void initialize() {
		initMettingFrament();
	}

//	private View inflateAndSetupView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, int layoutResourceId) {
//		View layout = inflater.inflate(layoutResourceId, container, false);
//		listView = (ListView) layout.findViewById(R.id.fragment_gzwd_listview);
//
//		return layout;
//	}

	private void initMettingFrament() {
		// public Person(String name, String zhiye, int shoucang, int yuedu, int pinglun, String jiyu) {
		List<Person> list = new ArrayList<Person>();
		Person person = new Person("张三","老师",11,11,11,"关注我的");
		list.add(person);
		person = new Person("张三1","老师1",22,22,22,"关注我的");
		list.add(person);
		person = new Person("张三11","老师11",33,33,33,"关注我的");
		list.add(person);
		person = new Person("张三1","老师1",22,22,22,"关注我的");
		list.add(person);
		person = new Person("张三11","老师11",33,33,33,"关注我的");
		list.add(person);
		person = new Person("张三1","老师1",22,22,22,"关注我的");
		list.add(person);
		person = new Person("张三11","老师11",33,33,33,"关注我的");
		list.add(person);
		person = new Person("张三1","老师1",22,22,22,"关注我的");
		list.add(person);
		person = new Person("张三11","老师11",33,33,33,"关注我的");
		list.add(person);
		person = new Person("张三1","老师1",22,22,22,"关注我的");
		list.add(person);
		person = new Person("张三11","老师11",33,33,33,"关注我的");
		list.add(person);
		adapter = new PersonListAdapter(getActivity(),list);
		listView.setAdapter(adapter);//设置展示数据的适配器
	}

	public class PersonListAdapter extends BaseAdapter {
		/**
		 * 自定义图层
		 */
		private LayoutInflater inflater;

		private Activity mActivity;

		private List<Person> list;

		public PersonListAdapter(Activity mActivity, List<Person> list) {

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
				convertView = inflater.inflate(R.layout.item_book_friend, null);
				holder = new ViewHolder();
				holder.titleImg = (ImageView) convertView.findViewById(R.id.ietm_book_friend_title_img_imageView);
				holder.nameTxt = (TextView) convertView.findViewById(R.id.ietm_book_friend_name_textview);
				holder.zhiyeTxt = (TextView) convertView.findViewById(R.id.ietm_book_friend_zhiyi_textview);
				holder.yueduTxt = (TextView) convertView.findViewById(R.id.ietm_book_friend_yuedu_textview);
				holder.pinglunTxt = (TextView) convertView.findViewById(R.id.ietm_book_friend_pinglun_textview);
				holder.shoucangTxt = (TextView) convertView.findViewById(R.id.ietm_book_friend_shoucang_textview);
				holder.jiyuTxt = (TextView) convertView.findViewById(R.id.ietm_book_friend_jiyu_textview);
				holder.guanzhuTxt = (TextView) convertView.findViewById(R.id.ietm_book_friend_guanzhu_textview);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			Person dto = list.get(position);
			if (dto != null) {
				holder.nameTxt.setText(dto.getName());
				holder.zhiyeTxt.setText(dto.getZhiye());
				holder.yueduTxt.setText("阅读："+dto.getYuedu());
				holder.pinglunTxt.setText("评论："+dto.getPinglun());
				holder.shoucangTxt.setText("收藏："+dto.getShoucang());
				holder.jiyuTxt.setText(dto.getJiyu());
				holder.guanzhuTxt.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(getActivity(), "" + list.get(position).getName(), Toast.LENGTH_SHORT).show();
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
			TextView nameTxt;//
			TextView zhiyeTxt;//
			TextView shoucangTxt;//
			TextView yueduTxt;//
			TextView pinglunTxt;//
			TextView jiyuTxt;
			TextView guanzhuTxt;
		}

	}
}
