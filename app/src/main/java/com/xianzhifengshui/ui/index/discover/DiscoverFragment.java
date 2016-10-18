package com.xianzhifengshui.ui.index.discover;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.TabPagerAdapter;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.index.discover.lecture.LectureListFragment;
import com.xianzhifengshui.ui.index.discover.master.MasterListFragment;
import com.xianzhifengshui.ui.index.discover.topic.TopicListFragment;
import com.xianzhifengshui.widget.auto.AutoTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: 发现页
 */

public class DiscoverFragment extends BaseFragment {

    /*======= 控件声明区 =======*/
    AutoTabLayout tabLayout;
    ViewPager viewPager;
    /*=========================*/
    List<BaseFragment> fragments;
    String[] titles;
    TabPagerAdapter pagerAdapter;

    @Override
    protected void initViews() {
        tabLayout = (AutoTabLayout) rootView.findViewById(R.id.tab_discover);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager_discover);
//        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        MasterListFragment masterListFragment = new MasterListFragment();
        LectureListFragment lectureListFragment = new LectureListFragment();
        TopicListFragment topicListFragment = new TopicListFragment();
        fragments.add(masterListFragment);
        fragments.add(lectureListFragment);
        fragments.add(topicListFragment);
        titles = new String[]{"大师","讲座","话题"};
        pagerAdapter = new TabPagerAdapter(getFragmentManager(),titles,fragments);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }


}
