package com.xianzhifengshui.ui.mymaster;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.TabPagerAdapter;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.mymaster.mydatedmaster.MyDatedMasterFragment;
import com.xianzhifengshui.ui.mymaster.mywantedmaster.MyWantedMasterFragment;
import com.xianzhifengshui.widget.auto.AutoTabLayout;
import com.xianzhifengshui.widget.popup.ListSelectDropDownPopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我的大师页面
 */
public class MyMasterActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener, PopupWindow.OnDismissListener, ListSelectDropDownPopupWindow.OnItemSelectedListener {
    /*======= 控件声明区 =======*/
    AutoTabLayout tabLayout;
    ViewPager viewPager;
    View customTabView;
    ImageView customTabIv;
    TextView customTabTv;
    ListSelectDropDownPopupWindow popupWindow;
    /*=========================*/
    List<BaseFragment> fragments;
    String[] titles;
    TabPagerAdapter pagerAdapter;
    boolean isCurrentDated = true;


    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,MyMasterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        tabLayout = (AutoTabLayout) findViewById(R.id.tab_my_master);
        viewPager = (ViewPager) findViewById(R.id.pager_my_master);
        customTabView = View.inflate(this,R.layout.widget_custom_tab,null);
        customTabIv = (ImageView)customTabView.findViewById(R.id.image_custom_tab_arrow);
        customTabTv = (TextView) customTabView.findViewById(R.id.text_custom_tab);
        customTabTv.setText("我约过的大师");
        customTabView.setSelected(true);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setCustomView(customTabView);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag(i);
                    tabView.setOnClickListener(this);
                }
            }
        }
        tabLayout.setOnTabSelectedListener(this);
        popupWindow = new ListSelectDropDownPopupWindow(this);
        popupWindow.setOnDismissListener(this);
        popupWindow.setOnItemSelectedListener(this);
        popupWindow.loadItems(Arrays.asList(getResources().getStringArray(R.array.item_my_master)));
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
        isCurrentDated = tab.getPosition() == 0;
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
            TabLayout.Tab tab = tabLayout.getTabAt(pos);
        if (tab != null) {
            if (pos == 0){
                if (isCurrentDated){
                    if (popupWindow != null) {
                        popupWindow.showAsDropDown(v);
                    }
                }else {
                    tab.select();
                    viewPager.setCurrentItem(0);
                }

            }else {
                tab.select();
                viewPager.setCurrentItem(1);
            }
        }
    }

    @Override
    public void onDismiss() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onSelected(int position) {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
