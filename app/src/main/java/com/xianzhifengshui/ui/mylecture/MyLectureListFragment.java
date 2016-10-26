package com.xianzhifengshui.ui.mylecture;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.LectureListAdapter;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.ThreadPoolUtils;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: 讲座列表页
 */
public class MyLectureListFragment extends BaseFragment implements MyLectureListContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView>{

    public static final int TYPE_WANTED = 1;
    public static final int TYPE_DATED = 2;

    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/

    private LectureListAdapter adapter;
    private MyLectureListContract.Presenter presenter;
    private List<String> data;
    private int currentPage = 0;
    private int type;
    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) rootView.findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerView.setAdapter(adapter);
        presenter.refreshData(type);
    }
    @Override
    protected void initData() {
        this.presenter = new MyLectureListPresenter(this);
        data = new ArrayList<>();
        adapter = new LectureListAdapter(getContext(),R.layout.item_home_banner,data);
        type = getArguments().getInt("type",TYPE_WANTED);
        log(TAG,type);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_lecture_list;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
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
    public void setPresenter(MyLectureListContract.Presenter presenter) {
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
        presenter.refreshData(type);
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
        if (pullToRefreshRecyclerView.isRefreshing()){
            pullToRefreshRecyclerView.onRefreshComplete();
        }else
            super.closeWait();
    }
}
