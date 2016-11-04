package com.xianzhifengshui.ui.evaluate;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.EvaluateListAdapter;
import com.xianzhifengshui.api.model.Evaluate;
import com.xianzhifengshui.api.model.Evaluate;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.evaluate.EvaluateContract;
import com.xianzhifengshui.ui.evaluate.EvaluatePresenter;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述:
 */
public class EvaluateFragment extends BaseFragment implements EvaluateContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView>{

    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/

    private EvaluateListAdapter adapter;
    private EvaluateContract.Presenter presenter;
    private List<Evaluate> data;

    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) rootView.findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        this.presenter = new EvaluatePresenter(this);
        data = new ArrayList<>();
        adapter = new EvaluateListAdapter(getContext(),R.layout.item_evaluate_list,data);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_evaluate;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void refreshData(List<Evaluate> data) {
        adapter.setData(data);
    }

    @Override
    public void loadMore(List<Evaluate> data) {
        adapter.loadMore(data);
    }

    @Override
    public void closeLoadMore() {
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showFailure() {

    }

    @Override
    public void setPresenter(EvaluateContract.Presenter presenter) {
        this.presenter = presenter;
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
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        presenter.refreshData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        presenter.loadMore();
    }

    @Override
    public void showWaiting() {
        if (!pullToRefreshRecyclerView.isRefreshing())
            super.showWaiting();
    }

    @Override
    public void closeWait() {
        if (pullToRefreshRecyclerView.isRefreshing())
            pullToRefreshRecyclerView.onRefreshComplete();
        else
            super.closeWait();
    }
}
