package com.masterfan.cloudbook.activity.personal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.entity.Comment;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import java.util.ArrayList;

/**
 * 我的评论
 */
@MTFActivityFeature(layout = R.layout.activity_comment_main,toolbar = R.id.toolbar, status_bar_color = R.color.colorPrimary)
public class CommentActivity extends MTFBaseActivity {

    ListView listView;
    ArrayList<Comment> arrayList;
    MyAdapter adapter;
    Comment comment;

    @Override
    public void initialize(Bundle savedInstanceState) {
        listView = (ListView) findViewById(R.id.listView);
        addData();
        adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
        toolbar.setTitle("我的评论");
    }

    public void addData(){
        arrayList = new ArrayList<Comment>();
        comment = new Comment("《西游记》","在android开发中ListView是比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示。抽空把对ListView的使用做了整理，并写了个小例子，如下图。","2016-1-21 10:43:26");
        arrayList.add(comment);
        comment = new Comment("《红楼梦》","在android开发中ListView是比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示。抽空把对ListView的使用做了整理，并写了个小例子，如下图。","2016-1-21 10:43:30");
        arrayList.add(comment);
        comment = new Comment("三国演义","在android开发中ListView是比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示。抽空把对ListView的使用做了整理，并写了个小例子，如下图。","2016-1-21 10:43:34");
        arrayList.add(comment);
        comment = new Comment("水浒传","在android开发中ListView是比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示。抽空把对ListView的使用做了整理，并写了个小例子，如下图。","2016-1-21 10:43:37");
        arrayList.add(comment);
        comment = new Comment("Android开发一本就够","在android开发中ListView是比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示。抽空把对ListView的使用做了整理，并写了个小例子，如下图。","2016-1-21 10:43:40");
        arrayList.add(comment);
        comment = new Comment("我对时间有耐心","在android开发中ListView是比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示。抽空把对ListView的使用做了整理，并写了个小例子，如下图。","2016-1-21 10:43:43");
        arrayList.add(comment);
        comment = new Comment("渝中人文","在android开发中ListView是比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示。抽空把对ListView的使用做了整理，并写了个小例子，如下图。","2016-1-21 10:43:46");
        arrayList.add(comment);
        comment = new Comment("渝中人文1","在android开发中ListView是比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示。抽空把对ListView的使用做了整理，并写了个小例子，如下图。","2016-1-21 10:43:46");
        arrayList.add(comment);
        comment = new Comment("渝中人文2","在android开发中ListView是比较常用的组件，它以列表的形式展示具体内容，并且能够根据数据的长度自适应显示。抽空把对ListView的使用做了整理，并写了个小例子，如下图。","2016-1-21 10:43:46");
        arrayList.add(comment);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (arrayList == null) {
                return 0;
            }
            return arrayList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return arrayList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup arg2) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_comment,null);
                holder.bookName = (TextView) convertView.findViewById(R.id.ietm_comment_title_textview);
                holder.content = (TextView) convertView.findViewById(R.id.ietm_comment_content_textview);
                holder.time = (TextView) convertView.findViewById(R.id.ietm_comment_time_textview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final Comment comment = arrayList.get(position);

            holder.bookName.setText(comment.getBookName());
            holder.content.setText(comment.getContent());
            holder.time.setText(comment.getTime());

            holder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CommentActivity.this,"点击了第"+position+"行",Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }
    private class ViewHolder {
        TextView bookName;
        TextView content;
        TextView time;

    }


}
