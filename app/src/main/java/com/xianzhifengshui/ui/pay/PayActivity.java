package com.xianzhifengshui.ui.pay;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者：chengx
 * 日期：2017/1/6
 * 描述：
 */

public class PayActivity extends BaseActivity implements PayContract.View, View.OnClickListener {

    private TextView payBtn;

    PayContract.Presenter presenter;

    public static void launcher(Context context){
        Intent intent = new Intent(context,PayActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_pay);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initViews() {
        payBtn = (TextView) findViewById(R.id.btn_pay);
        payBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        presenter = new PayPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void setPresenter(PayContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showTip(String text) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pay:
                presenter.pay();
                break;
        }
    }
}
