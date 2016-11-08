package com.xianzhifengshui.ui.article;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.ArticleListAdapter;
import com.xianzhifengshui.api.model.Article;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.widget.EmptyLayout;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/2.
 * 描述:
 */
public class ArticleFragment extends BaseFragment implements ArticleContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView>{

    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/

    private ArticleListAdapter adapter;
    private ArticleContract.Presenter presenter;
    private List<Article> data;

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
    }

    @Override
    protected void initData() {
        this.presenter = new ArticlePresenter(this);
        data = new ArrayList<>();
        adapter = new ArticleListAdapter(getContext(),R.layout.item_article_list,data);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void refreshData(ArrayList<Article> data) {
        adapter.setData(data);
        emptyLayout.hide();
    }

    @Override
    public void loadMore(ArrayList<Article> data) {
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
    public void setPresenter(ArticleContract.Presenter presenter) {
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
}
