package com.xianzhifengshui.ui.index.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuBuilder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.utils.DeviceUtils;
import com.xianzhifengshui.utils.SizeUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: 首页
 */
public class HomeFragment extends BaseFragment implements HomeContract.View{


    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setLeftBtnText("北京");
        toolbar.showSearchView(false);
        toolbar.setRightBtnImage(R.drawable.home_message_icon);
        toolbar.setLeftBtnDrawableRight(R.drawable.home_arrow_down_icon);
        toolbar.setOnLeftBtnDrawablePadding(5);
        toolbar.setOnRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("跳转到消息页面");
            }
        });
    }


    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void showTip(String text) {
        showToast(text);
    }
}
