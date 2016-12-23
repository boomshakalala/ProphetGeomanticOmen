package com.xianzhifengshui.ui.index.discover.master;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.MasterListAdapter;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.ui.masterdetail.MasterDetailActivity;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import org.w3c.dom.ProcessingInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: 大师列表页
 */
public class MasterListFragment extends BaseFragment implements MasterListContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView>, View.OnClickListener, CommonRecyclerAdapter.OnRecyclerViewItemClickListener<Master> {

    private final int INDEX_HOT = 0;
    private final int INDEX_LOCAL = 1;
    private final int INDEX_ALL = 2;

    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    private TextView hotBtn;
    private TextView localBtn;
    private TextView allBtn;
    /*=========================*/

    private MasterListAdapter adapter;
    private MasterListContract.Presenter presenter;
    private List<Master> data;
    private int currentIndex = INDEX_HOT;

    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) rootView.findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        hotBtn = (TextView) rootView.findViewById(R.id.btn_master_list_hot);
        localBtn = (TextView) rootView.findViewById(R.id.btn_master_list_local);
        allBtn = (TextView) rootView.findViewById(R.id.btn_master_list_all);
        hotBtn.setOnClickListener(this);
        localBtn.setOnClickListener(this);
        allBtn.setOnClickListener(this);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerView.setAdapter(adapter);
        emptyLayout.setErrorButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(currentIndex);
            }
        });
        setSelected(currentIndex);
    }

    @Override
    protected void initData() {
        this.presenter = new MasterListPresenter(this);
        data = new ArrayList<>();
        adapter = new MasterListAdapter(getContext(),R.layout.item_master_list,data);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_master_list;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
        presenter.refreshData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        presenter.loadMore();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_master_list_hot:
                //切换到热门
                currentIndex = INDEX_HOT;
                setSelected(INDEX_HOT);
                break;
            case R.id.btn_master_list_local:
                //切换到本地
                currentIndex = INDEX_LOCAL;
                setSelected(INDEX_LOCAL);
                break;
            case R.id.btn_master_list_all:
                //切换到全部
                currentIndex = INDEX_ALL;
                setSelected(INDEX_ALL);
                break;
            default:
                break;
        }

    }

    private void setSelected(int index){
        resetBtn();
        switch (index){
            case INDEX_HOT:
                presenter.refreshData();
                hotBtn.setSelected(true);
                hotBtn.setClickable(false);
                break;
            case INDEX_LOCAL:
                presenter.refreshData();
                localBtn.setSelected(true);
                localBtn.setClickable(false);
                break;
            case INDEX_ALL:
                presenter.refreshData();
                allBtn.setSelected(true);
                allBtn.setClickable(false);
                break;
            default:
                break;
        }
    }

    private void resetBtn() {
        hotBtn.setSelected(false);
        localBtn.setSelected(false);
        allBtn.setSelected(false);
        hotBtn.setClickable(true);
        localBtn.setClickable(true);
        allBtn.setClickable(true);
    }

    @Override
    public void refreshData(ArrayList<Master> data) {
        adapter.setData(data);
    }

    @Override
    public void loadMore(ArrayList<Master> data) {
        adapter.loadMore(data);
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
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
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
        emptyLayout.hide();

    }

    @Override
    public void setPresenter(MasterListContract.Presenter presenter) {
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
    public void  onItemClick(View view, Master data) {
        MasterDetailActivity.launcher(getContext());
    }
}
