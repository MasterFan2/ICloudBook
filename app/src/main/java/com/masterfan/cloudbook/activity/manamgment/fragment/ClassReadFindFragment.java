package com.masterfan.cloudbook.activity.manamgment.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.Util.DialogUtils;
import com.masterfan.cloudbook.activity.manamgment.ui.ClassReadListActivity;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 *班级阅读查询
 */
@MTFFragmentFeature(layout = R.layout.fragment_class_read_find)
public class ClassReadFindFragment extends MTFBaseFragment{

	private String starttime = "";
	private String endtime = "";

	@Bind(R.id.fragment_class_read_start_time_layout)
	LinearLayout startLayout;

	@Bind(R.id.fragment_class_read_end_time_layout)
	LinearLayout endLayout;

	@Bind(R.id.fragment_class_read_start_time_textview)
	TextView startTxt;

	@Bind(R.id.fragment_class_read_end_time_textview)
	TextView endTxt;

	@Bind(R.id.fragment_class_read_find_button)
	Button findBtn;

	@Override
	public void initialize() {

	}

	@OnClick(R.id.fragment_class_read_find_button)
	public void onclickBtn(View view){
		Intent intent = new Intent(getActivity(), ClassReadListActivity.class);
		if(intent != null){
			startActivity(intent);
		}
	}

	@OnClick(R.id.fragment_class_read_start_time_layout)
	public void onclickStartTimeBtn(View view){
		showTimeDialog(1);
	}

	@OnClick(R.id.fragment_class_read_end_time_layout)
	public void onclickEndTimeBtn(View view){
		showTimeDialog(2);
	}
	/**
	 * 显示日期选择对话框
	 *
	 * @param tag 1:选择开始时间；2：选择结束时间
	 */
	private void showTimeDialog(final int tag) {
		View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_datatime_layout, null);
		final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
		/**关闭DatePicker、TimePicker、NumberPicker的可编辑模式*/
		datePicker.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
		Date time = new Date();
		/**设置几点和几分--  不设置三星手机不能显示24小时制*/
		resizePikcer(datePicker);//调整datepicker大小
		//设置年月日
		Calendar cal = Calendar.getInstance();
		datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
		final Dialog dialog = DialogUtils.showCustomDialogNoTitle(getActivity(), view);
		TextView aTxt = (TextView) dialog.findViewById(R.id.datatime_layout_1);//取消
		TextView bTxt = (TextView) dialog.findViewById(R.id.datatime_layout_2);//确定
		dialog.show();
		aTxt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		bTxt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTime(datePicker, tag);
				dialog.dismiss();
			}
		});
	}
	/**
	 * 调整FrameLayout大小
	 * @param tp
	 */
	private void resizePikcer(FrameLayout tp){
		List<NumberPicker> npList = findNumberPicker(tp);
		for(NumberPicker np:npList){
			resizeNumberPicker(np);
		}
	}
	/**
	 * 调整numberpicker大小
	 * @param np
	 */
	private void resizeNumberPicker(NumberPicker np){
		/**120 代表宽度*/
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(280, LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(5, 0, 5, 0);
		np.setLayoutParams(params);
	}

	/**
	 * 得到viewGroup里面的numberpicker组件
	 * @param viewGroup
	 * @return
	 */
	private List<NumberPicker> findNumberPicker(ViewGroup viewGroup){
		List<NumberPicker> npList = new ArrayList<NumberPicker>();
		View child = null;
		if(null != viewGroup){
			for(int i = 0; i < viewGroup.getChildCount(); i++){
				child = viewGroup.getChildAt(i);
				if(child instanceof NumberPicker){
					npList.add((NumberPicker)child);
				}
				else if(child instanceof LinearLayout){
					List<NumberPicker> result = findNumberPicker((ViewGroup)child);
					if(result.size()>0){
						return result;
					}
				}
			}
		}
		return npList;
	}
	/**
	 * 设置开始结束和截至报名时间
	 *
	 * @param datePicker
	 * @param tag 1：开始时间；2：结束时间；3：截至报名时间
	 */
	private void setTime(DatePicker datePicker, int tag) {
		String month = datePicker.getMonth() + 1 + "";
		String day = datePicker.getDayOfMonth() + "";


		if (month.length() == 1) {
			month = "0" + month;
		}
		if (day.length() == 1) {
			day = "0" + day;
		}
		if(tag == 1) {
			starttime = datePicker.getYear() + "-" + month + "-" + day ;
			startTxt.setText(starttime);
		}else if(tag == 2){
			endtime = datePicker.getYear() + "-" + month + "-" + day ;
			endTxt.setText(endtime);
		}

	}

}
