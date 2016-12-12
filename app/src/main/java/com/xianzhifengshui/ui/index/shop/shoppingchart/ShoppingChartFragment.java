package com.xianzhifengshui.ui.index.shop.shoppingchart;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseFragment;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：
 */

public class ShoppingChartFragment extends BaseFragment implements ShoppingChartContract.View {
    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_shopping_chart;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void setPresenter(ShoppingChartContract.Presenter presenter) {

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
