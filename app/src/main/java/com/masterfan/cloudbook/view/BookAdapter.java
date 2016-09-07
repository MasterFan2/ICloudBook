package com.masterfan.cloudbook.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;


import com.masterfan.cloudbook.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BookAdapter implements IAdapter{
	private List<Map<String, Object>> strList = new ArrayList<Map<String,Object>>();
	
	private Context mContext;
	public BookAdapter(Context context) {
		super();
		this.mContext = context;
	}
	public void addItem(List<Map<String, Object>> list){
		strList.addAll(list);
	}
	public int getCount() {
		return strList.size();
	}

	public String getItem(int position) {
		if(strList.get(position).containsKey("text"))
		{
			return strList.get(position).get("text").toString();
		}else
		{
			return strList.get(position).get("image").toString();
		}
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position) {
		Map<String, Object> map = strList.get(position);
		if(map.containsKey("text")){
			TextView textView = new TextView(mContext);
			textView.setText(map.get("text").toString());
			textView.setTextColor(Color.BLACK);
			textView.setTextColor(Color.RED);
			textView.setBackgroundResource(R.drawable.tx_green_wall);
			textView.setPadding(10, 10, 10, 10);
			textView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			return textView;
		}else if(map.containsKey("image"))
		{
			ImageView image = new ImageView(mContext);
			image.setBackgroundResource(R.drawable.logo);
			image.setPadding(10, 10, 10, 10);
			image.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			return image;
		}else if(map.containsKey("imageandtext"))
		{
			LinearLayout linear = new LinearLayout(mContext);
			ImageView image = new ImageView(mContext);
			TextView textView = new TextView(mContext);
			linear.addView(image);
			image.setBackgroundResource(R.drawable.logo);
			textView.setText("asdfsdfasdfasd");
			linear.addView(textView);
			linear.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			return linear;
		}else
		{
			VideoView videoView = new VideoView(mContext);
	       videoView.setVideoURI(Uri.parse("/sdcard/beijinghuanyingni.mp4"));
	       MediaController mediaController = new MediaController(mContext);
	       videoView.setMediaController(mediaController);
	       videoView.start(); 
	       return videoView;
		}
	}

}
