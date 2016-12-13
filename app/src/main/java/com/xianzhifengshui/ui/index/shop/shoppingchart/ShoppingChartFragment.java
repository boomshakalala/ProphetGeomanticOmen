package com.xianzhifengshui.ui.index.shop.shoppingchart;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.HomeAdapter;
import com.xianzhifengshui.adapter.ShoppingChartAdapter;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.index.home.HomeContract;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：
 */

public class ShoppingChartFragment extends BaseFragment implements ShoppingChartContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView> {

    /*======== 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/
    private List<Object> data;
    private ShoppingChartAdapter adapter;
    private ShoppingChartContract.Presenter presenter;

    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) rootView.findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.DISABLED);
        recyclerView.setAdapter(adapter);
        emptyLayout.setErrorButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.refreshData();
            }
        });
        presenter.refreshData();
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        adapter = new ShoppingChartAdapter(getContext(),data);
        presenter = new ShoppingChartPresenter(this);
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
    public void refreshData(List<Object> data) {
        adapter.setData(data);
        emptyLayout.hide();
    }

    @Override
    public void loadMore(List<Object> data) {
        adapter.loadMore(data);
    }

    @Override
    public void showEmpty() {
        emptyLayout.setShowEmptyButton(true);
        emptyLayout.showEmpty();
    }

    @Override
    public void showFailure() {
        emptyLayout.setShowErrorButton(true);
        emptyLayout.showError();
    }

    @Override
    public void closeLoadMore() {
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        presenter.refreshData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        presenter.loadMore();
    }
}
