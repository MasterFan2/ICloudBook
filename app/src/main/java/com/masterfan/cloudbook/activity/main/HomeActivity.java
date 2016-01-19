package com.masterfan.cloudbook.activity.main;

import android.os.Bundle;
import android.sax.RootElement;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.fragment.home.HomeIndexFragment;
import com.masterfan.cloudbook.fragment.home.HomeManagementFragment;
import com.masterfan.cloudbook.fragment.home.HomePersonalFragment;
import com.masterfan.cloudbook.fragment.home.HomeResourceFragment;
import com.masterfan.library.ui.MTFBaseActivity;
import com.masterfan.library.ui.MTFBaseFragmentActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;

@MTFActivityFeature(layout = R.layout.activity_home, status_bar_color = R.color.colorPrimary, toolbar = R.id.toolbar)
public class HomeActivity extends MTFBaseActivity {

    private int index = 0;       //touched index
    private int currentIndex = 0;//current selected

    //Fragment
    private HomeIndexFragment       indexFragment;
    private HomeResourceFragment    resourceFragment;
    private HomeManagementFragment  managementFragment;
    private HomePersonalFragment    personalFragment;
    private Fragment[] fragments;

    //Layout
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
    }

    /**
     * Tab click listener
     * @param view
     */
    public void onTabClicked(View view){
        switch (view.getId()){
            case R.id.home_index_rl_layout:
                toolbar.setTitle("首页");
                index = 0;
                break;
            case R.id.home_resource_rl_layout:
                toolbar.setTitle("资源");
                index = 1;
                break;
            case R.id.home_management_rl_layout:
                toolbar.setTitle("管理");
                index = 2;
                break;
            case R.id.home_personal_rl_layout:
                toolbar.setTitle("个人");
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
}