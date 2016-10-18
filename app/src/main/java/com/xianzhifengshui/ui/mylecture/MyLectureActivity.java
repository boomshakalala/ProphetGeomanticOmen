package com.xianzhifengshui.ui.mylecture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
 * 描述: 我的讲座页面
 */
public class MyLectureActivity extends BaseActivity{
    /*======= 控件声明区 =======*/
    AutoTabLayout tabLayout;
    ViewPager viewPager;
    /*=========================*/
    List<BaseFragment> fragments;
    String[] titles;
    TabPagerAdapter pagerAdapter;


    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,MyLectureActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        tabLayout = (AutoTabLayout) findViewById(R.id.tab_my_lecture);
        viewPager = (ViewPager) findViewById(R.id.pager_my_lecture);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        MyLectureListFragment myWantedLectureFragment = new MyLectureListFragment();
        Bundle wantedBundle = new Bundle();
        wantedBundle.putInt("type",MyLectureListFragment.TYPE_WANTED);
        myWantedLectureFragment.setArguments(wantedBundle);
        MyLectureListFragment myDatedLectureFragment = new MyLectureListFragment();
        Bundle datedBundle = new Bundle();
        datedBundle.putInt("type",MyLectureListFragment.TYPE_DATED);
        myDatedLectureFragment.setArguments(datedBundle);
        fragments.add(myWantedLectureFragment);
        fragments.add(myDatedLectureFragment);
        titles = new String[]{"我想约的讲座","我约过的讲座"};
        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(),titles,fragments);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_lecture;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_lecture);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
