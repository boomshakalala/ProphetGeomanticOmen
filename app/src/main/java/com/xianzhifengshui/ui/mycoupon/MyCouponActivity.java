package com.xianzhifengshui.ui.mycoupon;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.MyCouponListAdapter;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述: 我的优惠券页
 */
public class MyCouponActivity extends BaseActivity implements MyCouponContract.View{

    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/
    private MyCouponListAdapter adapter;
    private List<String> data;
    private int currentPage = 0;
    private MyCouponContract.Presenter presenter;

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,MyCouponActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.recyclerView);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("");
        }
        adapter = new MyCouponListAdapter(this,R.layout.item_master_list,data);
        adapter.addHeaderView(View.inflate(this,R.layout.item_lecture_list,null));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void setPresenter(MyCouponContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showTip(String text) {

    }
}
