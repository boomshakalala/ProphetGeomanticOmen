package com.xianzhifengshui.ui.index.home;

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
import com.xianzhifengshui.utils.DeviceUtils;
import com.xianzhifengshui.utils.SizeUtils;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: 首页
 */
public class HomeFragment extends BaseFragment implements HomeContract.View,PullToRefreshBase.OnRefreshListener2{

   /*======== 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/
    private List<Object> data;
    private HomeAdapter adapter;

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
                log("跳转到消息页面");
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
               return adapter.getItemViewDelegate(viewType) instanceof HomeMenuItemDelegate ?  1 :  4;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        ArrayList<Carousel> carousels = new ArrayList<>();
        String[] imgUrls = {"http://img3.fengniao.com/forum/attachpics/913/114/36502745.jpg",
                "http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg",
                "http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg",
                "http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg"};
        for (int i = 0; i < 4; i++) {
            Carousel carousel = new Carousel();
            carousel.setPicUrl(imgUrls[i]);
            carousels.add(carousel);
        }
        data = new ArrayList<>();
        data.add(carousels);
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
        for (int i = 0; i < 8; i++) {
            data.add(new NaviMenu());
        }
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"推荐大师",true));
        for (int i = 0; i < 4; i++) {
            data.add(new Master());
        }
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_SPLIT_LINE,"",false));
        data.add(new ViewSupportModel(ViewSupportModel.VIEW_TYPE_LABEL,"精品讲座",false));
        for (int i = 0; i < 4; i++) {
            data.add(new Lecture());
        }
        adapter = new HomeAdapter(getContext(),data);
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

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
