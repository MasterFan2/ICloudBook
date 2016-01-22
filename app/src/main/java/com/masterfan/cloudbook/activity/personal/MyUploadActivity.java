package com.masterfan.cloudbook.activity.personal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.entity.Book;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 我的上传（图书）
 */
@MTFActivityFeature(layout = R.layout.activity_my_upload,toolbar = R.id.toolbar)
public class MyUploadActivity extends MTFBaseActivity {

    @Bind(R.id.my_load_listview)
    ListView listView;

    ArrayList<Book> arrayList;

    PersonListAdapter adapter;
    @Override
    public void initialize(Bundle savedInstanceState) {
        initData();
        adapter = new PersonListAdapter(MyUploadActivity.this);
        listView.setAdapter(adapter);//设置展示数据的适配器
    }
    private void initData() {
        arrayList = new ArrayList<Book>();
        Book book = new Book("红楼梦","曹雪芹","这是一本好书","古典文学",0);
        arrayList.add(book);
        book = new Book("红楼梦1","曹雪芹","这是一本好书","古典文学",1);
        arrayList.add(book);
        book = new Book("红楼梦2","曹雪芹","这是一本好书","古典文学",1);
        arrayList.add(book);
        book = new Book("静夜诗","李白","这是一首好诗","现代文学",0);
        arrayList.add(book);
        book = new Book("红楼梦4","曹雪芹","这是一本好书","古典文学",1);
        arrayList.add(book);
        book = new Book("红楼梦5","曹雪芹","这是一本好书","古典文学",2);
        arrayList.add(book);
        book = new Book("红楼梦6","曹雪芹","这是一本好书","古典文学",1);
        arrayList.add(book);
        book = new Book("红楼梦7","曹雪芹","这是一本好书","古典文学",0);
        arrayList.add(book);
    }

    public class PersonListAdapter extends BaseAdapter {
        /**
         * 自定义图层
         */
        private LayoutInflater inflater;

        private Activity mActivity;

        public PersonListAdapter(Activity mActivity) {
            this.mActivity = mActivity;

        }
        @Override
        public int getCount() {
            return arrayList.size();
        }
        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
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
                convertView = inflater.inflate(R.layout.item_my_upload, null);
                holder = new ViewHolder();
                holder.titleImg = (ImageView) convertView.findViewById(R.id.ietm_my_upload_title_img_imageView);
                holder.bookNameTxt = (TextView) convertView.findViewById(R.id.ietm_my_upload_book_name_textview);
                holder.writerTxt = (TextView) convertView.findViewById(R.id.ietm_my_upload_writer_textview);
                holder.introductionTxt = (TextView) convertView.findViewById(R.id.ietm_my_upload_introduction_textview);
                holder.typeTxt = (TextView) convertView.findViewById(R.id.item_my_upload_book_type_textview);
                holder.statusTxt = (TextView) convertView.findViewById(R.id.item_my_upload_check_status_textview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Book book = arrayList.get(position);
            if (book != null) {
                holder.bookNameTxt.setText(book.getBookName());
                holder.writerTxt.setText(book.getWriter());
                holder.introductionTxt.setText(book.getIntroduction());
                holder.typeTxt.setText(book.getBookType());
                if(book.getStatus() == 0) {
                    holder.statusTxt.setText("己通过");
                    holder.statusTxt.setTextColor(getResources().getColor(R.color.base_green));
                }else if(book.getStatus() == 1){
                    holder.statusTxt.setText("未通过");
                    holder.statusTxt.setTextColor(getResources().getColor(R.color.orange));
                }else if(book.getStatus() == 2){
                    holder.statusTxt.setText("审核中");
                    holder.statusTxt.setTextColor(getResources().getColor(R.color.gray));
                }else{
                    holder.statusTxt.setText("未知");
                    holder.statusTxt.setTextColor(getResources().getColor(R.color.black));
                }
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
            TextView bookNameTxt;//
            TextView writerTxt;//
            TextView introductionTxt;//
            TextView typeTxt;//
            TextView statusTxt;//

        }

    }
}
