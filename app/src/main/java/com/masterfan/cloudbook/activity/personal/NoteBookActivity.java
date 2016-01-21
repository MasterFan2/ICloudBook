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
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import java.util.ArrayList;

/**
 * 我的笔记
 */
@MTFActivityFeature(layout = R.layout.activity_notebook_main, toolbar = R.id.toolbar,status_bar_color = R.color.colorPrimary)
public class NoteBookActivity extends MTFBaseActivity {

    ListView listView;
    ArrayList<Comment> arrayList;
    MyAdapter adapter;
    Comment comment;

    @Override
    public void initialize(Bundle savedInstanceState) {
        toolbar.setTitle("我的笔记");
        listView = (ListView) findViewById(R.id.notebook_listView);
        addData();
        adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
    }

    public void addData(){
        arrayList = new ArrayList<Comment>();
        comment = new Comment("《西游记》","1我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:26");
        arrayList.add(comment);
        comment = new Comment("《红楼梦》","2我的笔记有些长，我的笔记有些长，我的笔记有些长。","2016-1-21 10:43:30");
        arrayList.add(comment);
        comment = new Comment("三国演义","3我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:34");
        arrayList.add(comment);
        comment = new Comment("《西游记》","4我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:26");
        arrayList.add(comment);
        comment = new Comment("《红楼梦》","5我的笔记有些长，我的笔记有些长，我的笔记有些长。","2016-1-21 10:43:30");
        arrayList.add(comment);
        comment = new Comment("三国演义","6我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:34");
        arrayList.add(comment);
        comment = new Comment("《西游记》","7我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:26");
        arrayList.add(comment);
        comment = new Comment("《红楼梦》","8我的笔记有些长，我的笔记有些长，我的笔记有些长。","2016-1-21 10:43:30");
        arrayList.add(comment);
        comment = new Comment("三国演义","9我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:34");
        arrayList.add(comment);
        comment = new Comment("《西游记》","10我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:26");
        arrayList.add(comment);
        comment = new Comment("《红楼梦》","11我的笔记有些长，我的笔记有些长，我的笔记有些长。","2016-1-21 10:43:30");
        arrayList.add(comment);
        comment = new Comment("三国演义","12我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:34");
        arrayList.add(comment);
        comment = new Comment("《西游记》","13我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:26");
        arrayList.add(comment);
        comment = new Comment("《红楼梦》","14我的笔记有些长，我的笔记有些长，我的笔记有些长。","2016-1-21 10:43:30");
        arrayList.add(comment);
        comment = new Comment("三国演义","15我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长我的笔记有些长","2016-1-21 10:43:34");
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
                    Toast.makeText(NoteBookActivity.this,"点击了第"+position+"行",Toast.LENGTH_SHORT).show();
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
