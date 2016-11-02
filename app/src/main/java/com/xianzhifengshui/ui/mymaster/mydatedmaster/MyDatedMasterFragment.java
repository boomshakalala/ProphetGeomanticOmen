package com.xianzhifengshui.ui.mymaster.mydatedmaster;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.MasterListAdapter;
import com.xianzhifengshui.adapter.MyDatedMasterListAdapter;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.ui.masterdetail.MasterDetailActivity;
import com.xianzhifengshui.ui.mymaster.mywantedmaster.MyWantedMasterContract;
import com.xianzhifengshui.ui.mymaster.mywantedmaster.MyWantedMasterPresent;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我想约的大师列表
 */
public class MyDatedMasterFragment extends BaseFragment implements MyDatedMasterContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView>,CommonRecyclerAdapter.OnRecyclerViewItemClickListener {

    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;

    private MyDatedMasterListAdapter adapter;
    MyDatedMasterContract.Present present;
    private List<String> data;
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
        present.refreshData();
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        adapter = new MyDatedMasterListAdapter(getContext(),data,R.layout.item_my_dated_master_list);
        adapter.setOnItemClickListener(this);
        present = new MyDatedMasterPresent(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_my_dated_master_list;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void setPresenter(MyDatedMasterContract.Present presenter) {

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
    public void refreshData(List<String> data) {
        adapter.setData(data);
    }

    @Override
    public void loadMore(List<String> data) {
        adapter.loadMore(data);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showFailure() {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        present.refreshData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        present.loadMore();
    }

    @Override
    public void showWaiting() {
        if (!pullToRefreshRecyclerView.isRefreshing()){
            super.showWaiting();
        }
    }

    @Override
    public void closeWait() {
        if (pullToRefreshRecyclerView.isRefreshing()){
            pullToRefreshRecyclerView.onRefreshComplete();
        }else {
            super.closeWait();
        }
    }

    @Override
    public void onItemClick(View view, Object data) {
        MasterDetailActivity.launcher(getContext());
    }
}
