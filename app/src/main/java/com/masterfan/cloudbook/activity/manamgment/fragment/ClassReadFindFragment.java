package com.masterfan.cloudbook.activity.manamgment.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.manamgment.ui.ClassReadListActivity;
import com.masterfan.cloudbook.activity.manamgment.ui.PersonReadListActivity;
import com.masterfan.cloudbook.activity.personal.entity.Person;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;
import com.masterfan.library.utils.S;
import com.masterfan.library.utils.T;
import com.masterfan.library.widget.recyclerview.MTFEndlessRecyclerOnScrollListener;
import com.masterfan.library.widget.recyclerview.MTFLoadingFooter;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewAdapterWrapper;
import com.masterfan.library.widget.recyclerview.MTFRecyclerViewStateUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *班级阅读查询
 */
@MTFFragmentFeature(layout = R.layout.fragment_class_read_find)
public class ClassReadFindFragment extends MTFBaseFragment{

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


}
