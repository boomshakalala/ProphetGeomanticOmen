package com.xianzhifengshui.ui.index.shop;

import android.support.v4.view.ViewPager;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.TabPagerAdapter;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.index.shop.collection.CollectionFragment;
import com.xianzhifengshui.ui.index.shop.goods.GoodsFragment;
import com.xianzhifengshui.ui.index.shop.order.OrderFragment;
import com.xianzhifengshui.ui.index.shop.shoppingchart.ShoppingChartFragment;
import com.xianzhifengshui.widget.auto.AutoTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: 商城页
 */
public class ShopFragment extends BaseFragment {

    /*======= 控件声明区 =======*/
    AutoTabLayout tabLayout;
    ViewPager viewPager;
    /*=========================*/
    List<BaseFragment> fragments;
    String[] titles;
    TabPagerAdapter pagerAdapter;

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_shop);
    }

    @Override
    protected void initViews() {
        tabLayout = (AutoTabLayout) rootView.findViewById(R.id.tab_shop);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager_shop);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        titles = new String[]{"宝贝","购物车","收藏","订单"};
        fragments.add(new GoodsFragment());
        fragments.add(new ShoppingChartFragment());
        fragments.add(new CollectionFragment());
        fragments.add(new OrderFragment());
        pagerAdapter = new TabPagerAdapter(getFragmentManager(),titles,fragments);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }
}
