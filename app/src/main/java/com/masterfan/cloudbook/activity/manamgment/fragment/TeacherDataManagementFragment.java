package com.masterfan.cloudbook.activity.manamgment.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.manamgment.ui.PersonReadListActivity;
import com.masterfan.cloudbook.activity.manamgment.ui.TeacherAccountManageListActivity;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;

import butterknife.Bind;
import butterknife.OnClick;

/**
 *老师信息管理
 */
@MTFFragmentFeature(layout = R.layout.fragment_teacher_data_manage)
public class TeacherDataManagementFragment extends MTFBaseFragment{

	@Bind(R.id.fragment_teacher_data_manage_find_button)
	Button findBtn;

	@Override
	public void initialize() {

	}

	@OnClick(R.id.fragment_teacher_data_manage_find_button)
	public void onclickBtn(View view){
		Intent intent = new Intent(getActivity(), TeacherAccountManageListActivity.class);
		if(intent != null){
			startActivity(intent);
		}
	}


}
