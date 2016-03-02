package com.masterfan.cloudbook.activity.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.fragment.home.HomeIndexFragment;
import com.masterfan.cloudbook.fragment.home.HomeManagementFragment;
import com.masterfan.cloudbook.fragment.home.HomeManagementFragmentTest;
import com.masterfan.cloudbook.fragment.home.HomePersonalFragment;
import com.masterfan.cloudbook.fragment.home.HomeResourceFragment;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;
import com.masterfan.library.utils.T;
import com.masterfan.library.widget.title.MTFTitleView;

import butterknife.Bind;
import butterknife.OnClick;

@MTFActivityFeature(layout = R.layout.activity_home, status_bar_color = R.color.colorPrimary)
public class HomeActivity extends MTFBaseActivity {

    private int index = 0;       //touched index
    private int currentIndex = 0;//current selected

    //Fragment
    private HomeIndexFragment       indexFragment;
    private HomeResourceFragment    resourceFragment;
    private HomeManagementFragment managementFragment;
    private HomePersonalFragment    personalFragment;
    private Fragment[] fragments;

    //bottom Layout
    @Bind(R.id.titleView)
    MTFTitleView titleView;
    @Bind(R.id.home_index_rl_layout)
    RelativeLayout indexLayout;
    @Bind(R.id.home_resource_rl_layout)
    RelativeLayout resourceLayout;
    @Bind(R.id.home_management_rl_layout)
    RelativeLayout managementLayout;
    @Bind(R.id.home_personal_rl_layout)
    RelativeLayout personalLayout;

    private RelativeLayout[] layouts;

    @Override
    public void initialize(Bundle savedInstanceState) {

        //fragment
        indexFragment       = new HomeIndexFragment();
        resourceFragment    = new HomeResourceFragment();
        managementFragment  = new HomeManagementFragment();
        personalFragment    = new HomePersonalFragment();
        fragments = new Fragment[]{indexFragment, resourceFragment, managementFragment, personalFragment};
        //
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, indexFragment)
                .add(R.id.fragment_container, resourceFragment)
                .add(R.id.fragment_container, managementFragment)
                .add(R.id.fragment_container, personalFragment)
                .hide(resourceFragment).hide(managementFragment)
                .hide(personalFragment).show(indexFragment).commit();
        //layout
        layouts = new RelativeLayout[]{indexLayout, resourceLayout, managementLayout, personalLayout};
        layouts[0].setBackgroundColor(getResources().getColor(R.color.base_green_checked));

        titleView.setTitleText(getResources().getString(R.string.home_index));
    }

    /**
     * Tab click listener
     * @param view
     */
    public void onTabClicked(View view){
        switch (view.getId()){
            case R.id.home_index_rl_layout:
                titleView.setTitleText(getResources().getString(R.string.home_index));
                index = 0;
                break;
            case R.id.home_resource_rl_layout:
                titleView.setTitleText(getResources().getString(R.string.home_resource));
                index = 1;
                break;
            case R.id.home_management_rl_layout:
                titleView.setTitleText(getResources().getString(R.string.home_management));
                index = 2;
                break;
            case R.id.home_personal_rl_layout:
                titleView.setTitleText(getResources().getString(R.string.home_personal));
                index = 3;
                break;
        }
        if(index != currentIndex){
            getSupportFragmentManager().beginTransaction()
                    .hide(fragments[currentIndex])
                    .show(fragments[index]).commit();
            layouts[currentIndex].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            layouts[index].setBackgroundColor(getResources().getColor(R.color.base_green_checked));
            currentIndex = index;
        }
    }

    @OnClick(R.id.m_title_left_btn)
    public void onBack(View view){
        T.s(context, "返回");
    }

    @OnClick(R.id.m_title_right_btn)
    public void onRight(View view){
        T.s(context, "更多");
    }
}
