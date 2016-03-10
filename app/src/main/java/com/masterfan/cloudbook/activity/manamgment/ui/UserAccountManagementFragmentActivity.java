package com.masterfan.cloudbook.activity.manamgment.ui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.masterfan.cloudbook.R;
import com.masterfan.cloudbook.activity.manamgment.fragment.ClassReadFindFragment;
import com.masterfan.cloudbook.activity.manamgment.fragment.PersonReadFindFragment;
import com.masterfan.cloudbook.activity.manamgment.fragment.StudentDataManagementFragment;
import com.masterfan.cloudbook.activity.manamgment.fragment.TeacherDataManagementFragment;
import com.masterfan.library.ui.MTFBaseFragmentActivity;
import com.masterfan.library.ui.annotation.MTFActivityFeature;

import butterknife.Bind;

/**
 * 用户账户管理
 */
@MTFActivityFeature(layout = R.layout.activity_user_account_management,status_bar_color = R.color.colorPrimary)
public class UserAccountManagementFragmentActivity extends MTFBaseFragmentActivity
        implements ActionBar.TabListener {

    private StudentDataManagementFragment studentDataManagementFragment = new StudentDataManagementFragment();
    private TeacherDataManagementFragment teacherDataManagementFragment = new TeacherDataManagementFragment();
//

    private static final int TAB_INDEX_COUNT = 2;

    private static final int TAB_INDEX_ONE = 0;
    private static final int TAB_INDEX_TWO = 1;

    @Bind(R.id.pager)
    ViewPager mViewPager;

    ViewPagerAdapter mViewPagerAdapter;

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;


    private final String[] filters = new String[]{"学生信息管理", "老师信息管理"};


    @Override
    public void initialize(Bundle savedInstanceState) {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.setScrollPosition(position, 0, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        break;
                    default:
                        break;
                }
            }
        });

        for (int i = 0; i < filters.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(filters[i]);
            tabLayout.addTab(tab);
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//		setUpActionBar();
//		setUpViewPager();
//		setUpTabs();
    }

    private void setUpActionBar() {
        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    private void setUpViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                final ActionBar actionBar = getActionBar();
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setUpTabs() {
        final ActionBar actionBar = getActionBar();
        for (int i = 0; i < mViewPagerAdapter.getCount(); ++i) {
            actionBar.addTab(actionBar.newTab()
                    .setText(mViewPagerAdapter.getPageTitle(i))
                    .setTabListener(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int position) {
            // TODO Auto-generated method stub
            switch (position) {
                case TAB_INDEX_ONE:
                    return studentDataManagementFragment;
                case TAB_INDEX_TWO:
                    return teacherDataManagementFragment;
            }
            throw new IllegalStateException("No fragment at position " + position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return TAB_INDEX_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String tabLabel = null;
            switch (position) {
                case TAB_INDEX_ONE:
                    tabLabel = getString(R.string.s_my_attention);
                    break;
                case TAB_INDEX_TWO:
                    tabLabel = getString(R.string.s_attention_my);
                    break;
            }
            return tabLabel;
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub

    }
}