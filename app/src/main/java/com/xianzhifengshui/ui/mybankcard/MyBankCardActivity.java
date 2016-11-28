package com.xianzhifengshui.ui.mybankcard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.BankCardListAdapter;
import com.xianzhifengshui.adapter.MyBillListAdapter;
import com.xianzhifengshui.api.model.BankCard;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.HeaderAndFooterCommonAdapter;
import com.xianzhifengshui.common.MultiItemCommonAdapter;
import com.xianzhifengshui.ui.addbankcard.AddBankCardActivity;
import com.xianzhifengshui.ui.mybill.MyBillContract;
import com.xianzhifengshui.ui.mybill.MyBillPresenter;
import com.xianzhifengshui.ui.photopicker.PhotoPickerContract;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshBase;
import com.xianzhifengshui.widget.pull2refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public class MyBankCardActivity extends BaseActivity implements MyBankCardContract.View,PullToRefreshBase.OnRefreshListener2<RecyclerView>,CommonRecyclerAdapter.OnRecyclerViewItemClickListener<BankCard>, View.OnClickListener {
    /*======= 控件声明区 =======*/
    private PullToRefreshRecyclerView pullToRefreshRecyclerView;
    private RecyclerView recyclerView;
    private Button addBankCardBtn;
    /*========================*/
    private HeaderAndFooterCommonAdapter adapter;
    private BankCardListAdapter innerAdapter;
    private List<BankCard> data;
    private MyBankCardContract.Presenter presenter;

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context, MyBankCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(getString(R.string.text_my_bank_card));
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initViews() {
        pullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.recyclerView);
        pullToRefreshRecyclerView.setScrollingWhileRefreshingEnabled(true);
        recyclerView = pullToRefreshRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pullToRefreshRecyclerView.setOnRefreshListener(this);
        pullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        View footerView = LayoutInflater.from(this).inflate(R.layout.layout_my_bank_card_footer,null);
        addBankCardBtn = (Button) footerView.findViewById(R.id.btn_my_bank_card_list_add);
        addBankCardBtn.setOnClickListener(this);
        adapter.addFooterView(footerView);
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
        data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            data.add(new BankCard());
        }
        innerAdapter = new BankCardListAdapter(this,R.layout.item_my_bank_card_list,data);
        presenter = new MyBankCardPresenter(this);
        innerAdapter.setOnItemClickListener(this);
        adapter = new HeaderAndFooterCommonAdapter(innerAdapter);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_bank_card;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void refreshData(List<Topic> data) {

    }

    @Override
    public void loadMore(List<Topic> data) {

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

    }

    @Override
    public void setPresenter(MyBankCardContract.Presenter presenter) {

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

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

    }

    @Override
    public void onItemClick(View view, BankCard data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_my_bank_card_list_add:
                AddBankCardActivity.launcher(this);
                break;
            default:
                break;
        }
    }
}
