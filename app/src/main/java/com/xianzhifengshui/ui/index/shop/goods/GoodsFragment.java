package com.xianzhifengshui.ui.index.shop.goods;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.GoodsListAdapter;
import com.xianzhifengshui.api.model.Goods;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.utils.SizeUtils;
import com.xianzhifengshui.widget.GridSpaceItemDecoration;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：商品列表页
 */

public class GoodsFragment extends BaseFragment implements GoodsContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView>,CommonRecyclerAdapter.OnRecyclerViewItemClickListener {
    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/

    private GoodsListAdapter adapter;
    private GoodsContract.Presenter presenter;
    private List<Goods> data;
    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) rootView.findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.addItemDecoration(new GridSpaceItemDecoration(SizeUtils.dp2px(getContext(),16),2));
        recyclerView.setLayoutManager(layoutManager);
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.DISABLED);
        recyclerView.setAdapter(adapter);
        emptyLayout.setShowErrorButton(true);
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
        this.presenter = new GoodsPresenter(this);
        data = new ArrayList<>();
        adapter = new GoodsListAdapter(getContext(),R.layout.item_goods,data);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_goods;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void setPresenter(GoodsContract.Presenter presenter) {
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
    public void refreshData(List<Goods> data) {
        adapter.setData(data);
        emptyLayout.hide();
    }

    @Override
    public void loadMore(List<Goods> data) {
        adapter.loadMore(data);
    }

    @Override
    public void showEmpty() {
        emptyLayout.showEmpty();
    }

    @Override
    public void showFailure() {
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

    @Override
    public void showWaiting() {
        if (pullToRefreshRecyclerView.isRefreshing())
            return;
        else if (data.size()==0){
            emptyLayout.showLoading();
        }else
            super.showWaiting();
    }

    @Override
    public void closeWait() {
        if (pullToRefreshRecyclerView.isRefreshing())
            pullToRefreshRecyclerView.onRefreshComplete();
        else if (isProgressDialogShowing())
            super.closeWait();
        else
            emptyLayout.hide();
    }


    @Override
    public void onItemClick(View view, Object data) {

    }
}
