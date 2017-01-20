package com.xianzhifengshui.ui.myorder;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.widget.auto.AutoTabLayout;

/**
 * 作者：chengx
 * 日期：2017/1/13
 * 描述：
 */

public class MyOrderActivity extends BaseActivity {
    private AutoTabLayout tabLayout;
    private ViewPager viewPager;
    private TextView volumeTv;
    private TextView startLevel;

    @Override
    protected void initViews() {
        tabLayout = (AutoTabLayout) findViewById(R.id.tab_shop);
        viewPager = (ViewPager) findViewById(R.id.pager_my_order);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }
}
