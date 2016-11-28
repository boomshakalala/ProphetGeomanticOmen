package com.xianzhifengshui.ui.myaccount;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.ui.mybankcard.MyBankCardActivity;
import com.xianzhifengshui.ui.mybill.MyBillActivity;
import com.xianzhifengshui.ui.recharge.RechargeActivity;
import com.xianzhifengshui.ui.recharge.RechargeContract;
import com.xianzhifengshui.ui.withdrawcrash.WithdrawCrashActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述: 我的账户页
 */
public class MyAccountActivity extends BaseActivity implements MyAccountContract.View, View.OnClickListener {

    /*======= 控件声明区 =======*/
    private TextView rechargeBtn;
    private TextView withdrawalBtn;
    private RelativeLayout billBtn;
    private RelativeLayout bankCardBtn;
    private TextView balanceTv;
    /*=========================*/

    private MyAccountContract.Presenter presenter;


    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,MyAccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_my_account);
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
        rechargeBtn = (TextView) findViewById(R.id.btn_my_account_recharge);
        withdrawalBtn = (TextView) findViewById(R.id.btn_my_account_withdrawal);
        billBtn = (RelativeLayout) findViewById(R.id.btn_my_account_bill);
        bankCardBtn = (RelativeLayout) findViewById(R.id.btn_my_account_bank_card);
        balanceTv = (TextView) findViewById(R.id.text_my_account_balance);
        rechargeBtn.setOnClickListener(this);
        withdrawalBtn.setOnClickListener(this);
        billBtn.setOnClickListener(this);
        bankCardBtn.setOnClickListener(this);
        balanceTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        presenter = new MyAccountPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_account;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void showAccountInfo() {
        balanceTv.setText("1,000");
    }

    @Override
    public void setPresenter(MyAccountContract.Presenter presenter) {
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
        switch (v.getId()){
            case R.id.btn_my_account_recharge:
                //跳转到充值页面
                RechargeActivity.launcher(this);
                break;
            case R.id.btn_my_account_withdrawal:
                //跳转到提现页面
                WithdrawCrashActivity.launcher(this);
                break;
            case R.id.btn_my_account_bill:
                //跳转到我的账单页面
                MyBillActivity.launcher(this);
                break;
            case R.id.btn_my_account_bank_card:
                //跳转到我的银行卡页面
                MyBankCardActivity.launcher(this);
                break;
            default:
                break;
        }

    }
}
