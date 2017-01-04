package com.xianzhifengshui.ui.index.home;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.HomeAdapter;
import com.xianzhifengshui.adapter.HomeMenuItemDelegate;
import com.xianzhifengshui.adapter.ViewSupportModel;
import com.xianzhifengshui.api.model.Carousel;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.NaviMenu;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.common.ItemViewDelegateManager;
import com.xianzhifengshui.ui.chat.ChatActivity;
import com.xianzhifengshui.ui.messagecenter.MessageCenterActivity;
import com.xianzhifengshui.ui.messagecenter.MessageCenterContract;
import com.xianzhifengshui.ui.search.SearchActivity;
import com.xianzhifengshui.utils.DeviceUtils;
import com.xianzhifengshui.utils.SizeUtils;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: 首页
 */
public class HomeFragment extends BaseFragment implements HomeContract.View,PullToRefreshBase.OnRefreshListener2, View.OnClickListener {

   /*======== 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/
    private List<Object> data;
    private HomeAdapter adapter;
    private HomeContract.Presenter presenter;

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setLeftBtnText("北京");
        toolbar.showSearchView(false);
        toolbar.setRightBtnImage(R.drawable.home_message_icon);
        toolbar.setLeftBtnDrawableRight(R.drawable.home_arrow_down_icon);
        toolbar.setOnLeftBtnDrawablePadding(5);
        toolbar.setOnRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatActivity.launcher(getContext());
            }
        });
        toolbar.setOnSearchViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    SearchActivity.launcher(activity,v);
                }else {
                    SearchActivity.launcher(getContext());
                }
            }
        });
    }


    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) rootView.findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),4);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return adapter.getItemViewDelegate(viewType) instanceof HomeMenuItemDelegate ? 1 : 4;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
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
        presenter = new HomePresenter(this);
        data = new ArrayList<>();
        adapter = new HomeAdapter(getContext(),data);
        adapter.setOnClickListener(this);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
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
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        presenter.refreshData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        presenter.loadMore();
    }

    @Override
    public void refreshData(List<Object> data) {
        adapter.setData(data);
    }

    @Override
    public void loadMore(List<Object> data) {
        adapter.loadMore(data);
    }

    @Override
    public void showEmpty() {
        emptyLayout.showEmpty();
    }

    @Override
    public void showFailure() {
        emptyLayout.setShowErrorButton(true);
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
        else
            emptyLayout.hide();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomePresenter)presenter).checkLogin(sp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_master_list_point_of_praise:
                Master master = (Master) view.getTag(R.id.text_master_list_point_of_praise);
                if (master != null) {
                    presenter.praise(master.getMasterCode());
                }
                break;
            default:
                break;
        }
    }
}
