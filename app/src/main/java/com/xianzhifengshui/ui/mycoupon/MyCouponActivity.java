package com.xianzhifengshui.ui.mycoupon;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.MyCouponListAdapter;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.common.HeaderAndFooterCommonAdapter;
import com.xianzhifengshui.common.MultiItemTypeSupport;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述: 我的优惠券页
 */
public class MyCouponActivity extends BaseActivity implements MyCouponContract.View,PullToRefreshBase.OnRefreshListener2, View.OnClickListener {

    private final int HEADER = 1;
    private final int BODY = 2;
    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    /*=========================*/
    private MyCouponListAdapter innerAdapter;
    private HeaderAndFooterCommonAdapter adapter;
    private List<String> data;
    private int currentPage = 0;
    private MyCouponContract.Presenter presenter;

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,MyCouponActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_my_coupon);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HeaderAndFooterCommonAdapter(innerAdapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_my_coupon_header, null);
        TextView giftBtn = (TextView) headerView.findViewById(R.id.btn_my_coupon_gift);
        giftBtn.setOnClickListener(this);
        adapter.addHeaderView(headerView);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
                data.add("");
        }
        innerAdapter = new MyCouponListAdapter(this,R.layout.item_coupon_list,data);


    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(MyCouponContract.Presenter presenter) {
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

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_my_coupon_gift:
                log("邀请好友");
                break;
            default:
                break;
        }
    }
}
