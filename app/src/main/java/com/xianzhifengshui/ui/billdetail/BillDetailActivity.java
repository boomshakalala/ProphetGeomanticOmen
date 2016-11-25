package com.xianzhifengshui.ui.billdetail;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Bill;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public class BillDetailActivity extends BaseActivity implements BillDetailContract.View{



    public static void luncher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,BillDetailActivity.class);
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
        toolbar.setTitle(getString(R.string.text_bill_detail));
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_bill_detail;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void refreshData(Bill bill) {

    }

    @Override
    public void showFailure(String message) {

    }

    @Override
    public void setPresenter(BillDetailContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showTip(String text) {

    }
}
