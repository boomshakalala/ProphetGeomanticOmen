package com.xianzhifengshui.ui.servicetype;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.ServiceTypeListAdapter;
import com.xianzhifengshui.api.model.ServiceType;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述:
 */
public class ServiceTypeFragment extends BaseFragment implements ServiceTypeContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView>{

    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/

    private ServiceTypeListAdapter adapter;
    private ServiceTypeContract.Presenter presenter;
    private List<ServiceType> data;

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
        this.presenter = new ServiceTypePresenter(this);
        data = new ArrayList<>();
        adapter = new ServiceTypeListAdapter(getContext(),R.layout.item_service_type,data);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_service_type;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void refreshData(List<ServiceType> data) {
        adapter.setData(data);
    }

    @Override
    public void loadMore(List<ServiceType> data) {
        adapter.loadMore(data);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showFailure() {

    }

    @Override
    public void setPresenter(ServiceTypeContract.Presenter presenter) {
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
}
