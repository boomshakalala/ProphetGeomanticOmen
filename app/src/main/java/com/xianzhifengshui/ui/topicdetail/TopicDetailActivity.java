package com.xianzhifengshui.ui.topicdetail;

import android.support.v7.widget.RecyclerView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/7.
 * 描述: 话题详情页
 */
public class TopicDetailActivity extends BaseActivity implements TopicDetailContract.View{


    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/

    @Override
    protected void initToolbar() {
        super.initToolbar();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(TopicDetailContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void showTip(String text) {
        showToast(text);
    }

    @Override
    public void refreshData(List<Object> data) {

    }

    @Override
    public void loadMore(List<Object> data) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showFailure() {

    }
}
