package com.xianzhifengshui.ui.mymaster;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.TabPagerAdapter;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.mymaster.mydatedmaster.MyDatedMasterFragment;
import com.xianzhifengshui.ui.mymaster.mywantedmaster.MyWantedMasterFragment;
import com.xianzhifengshui.widget.auto.AutoTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我的大师页面
 */
public class MyMasterActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    /*======= 控件声明区 =======*/
    AutoTabLayout tabLayout;
    ViewPager viewPager;
    /*=========================*/
    List<BaseFragment> fragments;
    String[] titles;
    TabPagerAdapter pagerAdapter;


    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,MyMasterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        tabLayout = (AutoTabLayout) findViewById(R.id.tab_my_master);
        viewPager = (ViewPager) findViewById(R.id.pager_my_master);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        MyWantedMasterFragment myWantedMasterFragment = new MyWantedMasterFragment();
        MyDatedMasterFragment myDatedMasterFragment = new MyDatedMasterFragment();
        fragments.add(myWantedMasterFragment);
        fragments.add(myDatedMasterFragment);
        titles = new String[]{"我想约的大师","我约过的大师"};
        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(),titles,fragments);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_master;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_my_master);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
