package com.xianzhifengshui.adapter;

import android.support.v4.app.FragmentManager;

import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.common.PagerAdapter;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: TabViewPagerAdapter
 */
public class TabPagerAdapter extends PagerAdapter {
    private String[] titles;

    public TabPagerAdapter(FragmentManager fm, String[] titles, List<BaseFragment> fragments) {
        super(fm,fragments);
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position % titles.length];
    }
}
