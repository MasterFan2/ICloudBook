package com.masterfan.cloudbook.fragment.home;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.personal.PersonalDataActivity;
import com.masterfan.library.ui.MTFBaseFragment;
import com.masterfan.library.ui.annotation.MTFFragmentFeature;

import org.xutils.view.annotation.ViewInject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 13510 on 2016/1/19.
 */
@MTFFragmentFeature(layout = R.layout.activity_personal)
public class HomePersonalFragment extends MTFBaseFragment {

    @Bind(R.id.personal_head_img)
    ImageView headImageView;

    @Override
    public void initialize() {

    }

    @OnClick(R.id.personal_head_img)
    public void onclickHeadImage(){
        Intent intent = new Intent(HomePersonalFragment.this.getContext(),PersonalDataActivity.class);
        if(intent != null){
            animStart(intent);
        }else{
            Log.i("AAAA","intent为空");
        }

    }
}
