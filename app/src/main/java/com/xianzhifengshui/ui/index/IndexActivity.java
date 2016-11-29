package com.xianzhifengshui.ui.index;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.ui.chat.ChatActivity;
import com.xianzhifengshui.ui.index.discover.DiscoverFragment;
import com.xianzhifengshui.ui.index.home.HomeFragment;
import com.xianzhifengshui.ui.index.mine.MineFragment;
import com.xianzhifengshui.ui.index.shop.ShopFragment;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.NotificationClickEvent;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: 先知主页
 */
public class IndexActivity extends BaseActivity implements View.OnClickListener {

    private final int INDEX_HOME = 0;
    private final int INDEX_DISCOVER = 1;
    private final int INDEX_SHOP = 2;
    private final int INDEX_MINE = 4;
    /*======= 控件声明区 =======*/
    TextView homeBtn;
    TextView discoverBtn;
    TextView shopBtn;
    TextView mineBtn;
    /*=========================*/
    private HomeFragment homeFragment;
    private DiscoverFragment discoverFragment;
    private ShopFragment shopFragment;
    private MineFragment mineFragment;


    @Override
    protected void initViews() {
        homeBtn = (TextView) findViewById(R.id.btn_index_home);
        discoverBtn = (TextView) findViewById(R.id.btn_index_discover);
        shopBtn = (TextView) findViewById(R.id.btn_index_shop);
        mineBtn = (TextView) findViewById(R.id.btn_index_mine);
        homeBtn.setOnClickListener(this);
        discoverBtn.setOnClickListener(this);
        shopBtn.setOnClickListener(this);
        mineBtn.setOnClickListener(this);
        setSelected(INDEX_HOME);
    }

    @Override
    protected void initData() {
        setCouldDoubleBackExit(true);
    }

    /**
     * 设置导航栏选中状态
     * @param index 选中索引
     */
    private void setSelected(int index){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideTransaction(ft);
        resetBtn();
        switch (index){
            case INDEX_HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.layout_container,homeFragment);
                }
                ft.show(homeFragment);
                homeBtn.setSelected(true);
                break;
            case INDEX_DISCOVER:
                if (discoverFragment == null) {
                    discoverFragment = new DiscoverFragment();
                    ft.add(R.id.layout_container,discoverFragment);
                }
                ft.show(discoverFragment);
                discoverBtn.setSelected(true);
                break;
            case INDEX_SHOP:
                if (shopFragment == null) {
                    shopFragment = new ShopFragment();
                    ft.add(R.id.layout_container,shopFragment);
                }
                ft.show(shopFragment);
                shopBtn.setSelected(true);
                break;
            case INDEX_MINE:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.layout_container,mineFragment);
                }
                ft.show(mineFragment);
                mineBtn.setSelected(true);
                break;
            default:
                break;
        }
        ft.commit();
    }

    /**
     * 隐藏fragment
     * @param ft 事物
     */
    private void hideTransaction(FragmentTransaction ft){
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (discoverFragment != null) {
            ft.hide(discoverFragment);
        }
        if (shopFragment != null) {
            ft.hide(shopFragment);
        }
        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
    }

    /**
     * 重置按钮状态，即设置按钮为暗色
     */
    private void resetBtn(){
        homeBtn.setSelected(false);
        discoverBtn.setSelected(false);
        shopBtn.setSelected(false);
        mineBtn.setSelected(false);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_index_home:
                //切换到首页
                setSelected(INDEX_HOME);
                break;
            case R.id.btn_index_discover:
                //切换到发现
                setSelected(INDEX_DISCOVER);
                break;
            case R.id.btn_index_shop:
                //切换到商城
                setSelected(INDEX_SHOP);
                break;
            case R.id.btn_index_mine:
                //切换到我
                setSelected(INDEX_MINE);
                break;
            default:
                break;
        }

    }

}
