package com.masterfan.cloudbook.view;

import android.view.View;

public interface IAdapter {
	public int getCount();
	public String getItem(int position);
	public long getItemId(int position);
	public View getView(int position);
}
