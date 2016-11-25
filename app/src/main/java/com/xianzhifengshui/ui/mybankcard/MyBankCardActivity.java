package com.xianzhifengshui.ui.mybankcard;

import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.ui.photopicker.PhotoPickerContract;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public class MyBankCardActivity extends BaseActivity implements MyBankCardContract.View{
    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return 0;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void refreshData(List<Topic> data) {

    }

    @Override
    public void loadMore(List<Topic> data) {

    }

    @Override
    public void showEmpty() {
        emptyLayout.showEmpty();
    }

    @Override
    public void showFailure() {
        emptyLayout.setShowErrorButton(true);
        emptyLayout.showError();
    }

    @Override
    public void closeLoadMore() {

    }

    @Override
    public void setPresenter(MyBankCardContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showTip(String text) {

    }
}
