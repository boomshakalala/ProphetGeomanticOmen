package com.xianzhifengshui.ui.search;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.MasterListAdapter;
import com.xianzhifengshui.adapter.TagAdapter;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.StringUtils;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;
import com.xianzhifengshui.widget.tag.TagLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/28.
 * 描述:
 */
public class SearchActivity extends BaseActivity implements SearchContract.View, View.OnClickListener,
        CommonRecyclerAdapter.OnRecyclerViewItemClickListener<String>,PullToRefreshBase.OnRefreshListener2<RecyclerView> {
    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    private RecyclerView tagLayout;
    private LinearLayout contentLayout;
    private LinearLayout initLayout;
    /*========================*/

    private SearchContract.Presenter presenter;
    private List<String> tags;
    private TagAdapter tagAdapter;
    private MasterListAdapter adapter;
    private List<Master> data;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void launcher(Activity context,View v){
        Intent intent = new Intent();
        intent.setClass(context,SearchActivity.class);
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context, v, "searchView").toBundle());
    }

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.showSearchView(true);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAfterTransition(SearchActivity.this);
            }
        });
        toolbar.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.loadData(toolbar.getSearchText());
                }
                return false;
            }
        });
    }

    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        tagLayout = (RecyclerView) findViewById(R.id.layout_search_tag);
        initLayout = (LinearLayout) findViewById(R.id.layout_search_init);
        contentLayout = (LinearLayout) findViewById(R.id.layout_search_content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        tagLayout.setLayoutManager(gridLayoutManager);
        tagLayout.setAdapter(tagAdapter);
        recyclerView.setAdapter(adapter);
        emptyLayout.setErrorButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData(null);
            }
        });
       showInit();
    }


    @Override
    protected void initData() {
        presenter = new SearchPresenter(this);
        tags = Arrays.asList(getResources().getStringArray(R.array.tag_search));
        tagAdapter = new TagAdapter(this,R.layout.item_search_tag,tags);
        tagAdapter.setOnItemClickListener(this);
        data = new ArrayList<>();
        adapter = new MasterListAdapter(this,R.layout.item_master_list,data);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void showEmpty() {
        contentLayout.setVisibility(View.VISIBLE);
        initLayout.setVisibility(View.GONE);
        emptyLayout.showEmpty();
    }

    @Override
    public void showFailure(String message) {
        contentLayout.setVisibility(View.VISIBLE);
        initLayout.setVisibility(View.GONE);
        emptyLayout.setShowErrorButton(true);
        emptyLayout.showError();
    }

    @Override
    public void loadData(ArrayList<Master> data) {
        adapter.setData(data);
        contentLayout.setVisibility(View.VISIBLE);
        initLayout.setVisibility(View.GONE);
    }

    @Override
    public void loadMore(ArrayList<Master> data) {
        adapter.loadMore(data);
    }

    @Override
    public void showInit() {
        initLayout.setVisibility(View.VISIBLE);
        contentLayout.setVisibility(View.GONE);
    }

    @Override
    public void setKeyword(String keyword) {
        toolbar.setKeyword(keyword);
    }

    @Override
    public void closeLoadMore() {
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.DISABLED);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
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
    public void onClick(View v) {

    }


    @Override
    public void onItemClick(View view, String data) {
        presenter.loadData(data);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        return;
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
