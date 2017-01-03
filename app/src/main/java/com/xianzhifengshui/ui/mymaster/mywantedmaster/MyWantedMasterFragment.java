package com.xianzhifengshui.ui.mymaster.mywantedmaster;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.MasterListAdapter;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我想约的大师列表
 */
public class MyWantedMasterFragment extends BaseFragment implements MyWantedMasterContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView> {

    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/

    private MasterListAdapter adapter;
    private MyWantedMasterContract.Presenter presenter;
    private ArrayList<Master> data;
    private int currentPage = 0;

    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) rootView.findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
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
        adapter = new MasterListAdapter(getContext(),R.layout.item_master_list,data);
        presenter = new MyWantedMasterPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_my_wanted_master_list;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void setPresenter(MyWantedMasterContract.Presenter presenter) {
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
    public void refreshData(List<Master> data) {
        adapter.setData(data);
        emptyLayout.hide();
    }

    @Override
    public void loadMore(List<Master> data) {
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
    public void showWaiting() {
        if (pullToRefreshRecyclerView.isRefreshing())
            return;
        if (data.size() == 0){
            emptyLayout.showLoading();
        }else
            super.showWaiting();
    }

    @Override
    public void closeWait() {
        if (pullToRefreshRecyclerView.isRefreshing()){
            pullToRefreshRecyclerView.onRefreshComplete();
        }else if (isProgressDialogShowing()){
            super.closeWait();
        }else {
            emptyLayout.hide();
        }
    }
}
