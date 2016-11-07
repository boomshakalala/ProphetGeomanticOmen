package com.xianzhifengshui.ui.topicdetail;

import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/11/7.
 * 描述: 话题详情页
 */
public class TopicDetailActivity extends BaseActivity implements TopicDetailContract.View{
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
    public void setPresenter(TopicDetailContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showTip(String text) {

    }
}
