package com.xianzhifengshui.ui.mytopic;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.TabPagerAdapter;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.mytopic.myattentiontopic.MyAttentionTopicFragment;
import com.xianzhifengshui.ui.mytopic.myinitiatetopic.MyInitiateTopicFragment;
import com.xianzhifengshui.ui.mytopic.myjoinedtopic.MyJoinedTopicFragment;
import com.xianzhifengshui.widget.auto.AutoTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/9.
 * 描述: 我的话题页
 */
public class MyTopicActivity extends BaseActivity{
    /*======= 控件声明区 =======*/
    AutoTabLayout tabLayout;
    ViewPager viewPager;
    /*=========================*/
    List<BaseFragment> fragments;
    String[] titles;
    TabPagerAdapter pagerAdapter;
    MyAttentionTopicFragment attentionTopicFragment;
    MyInitiateTopicFragment initiateTopicFragment;
    MyJoinedTopicFragment joinedTopicFragment;


    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,MyTopicActivity.class);
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
        titles = getResources().getStringArray(R.array.tab_my_topic);
        initiateTopicFragment = new MyInitiateTopicFragment();
        joinedTopicFragment = new MyJoinedTopicFragment();
        attentionTopicFragment = new MyAttentionTopicFragment();
        fragments.add(initiateTopicFragment);
        fragments.add(joinedTopicFragment);
        fragments.add(attentionTopicFragment);
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
        toolbar.setTitle(R.string.text_my_topic);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
