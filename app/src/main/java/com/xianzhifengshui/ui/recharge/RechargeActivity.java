package com.xianzhifengshui.ui.recharge;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述:
 */
public class RechargeActivity extends BaseActivity implements RechargeContract.View, View.OnClickListener {

    public static final int ALIPAY = 1;
    public static final int WECHATPAY = 2;
    public static final int UNIONPAY = 3;
    /*======= 控件声明区 =======*/
    private TextView balanceTv;
    private TextView rechargeAmountTv;
    private TextView arrivalAmountTv;
    private LinearLayout alipayBtn;
    private LinearLayout wechatpayBtn;
    private LinearLayout unionpayBtn;
    private ImageView alipayChx;
    private ImageView wechatpayChx;
    private ImageView unionpayChx;
    private Button payBtn;
    /*=========================*/
    private RechargeContract.Presenter presenter;
    private int payMethod;

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,RechargeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_recharge);
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
        balanceTv = (TextView) findViewById(R.id.text_recharge_balance);
        rechargeAmountTv = (TextView) findViewById(R.id.text_recharge_recharge_amount);
        arrivalAmountTv = (TextView) findViewById(R.id.text_recharge_arrival_amount);
        alipayBtn = (LinearLayout) findViewById(R.id.btn_recharge_alipay);
        wechatpayBtn  = (LinearLayout) findViewById(R.id.btn_recharge_wechatpay);
        unionpayBtn = (LinearLayout) findViewById(R.id.btn_recharge_unionpay);
        alipayChx = (ImageView) findViewById(R.id.chx_recharge_alipay);
        wechatpayChx = (ImageView) findViewById(R.id.chx_recharge_wechatpay);
        unionpayChx = (ImageView) findViewById(R.id.chx_recharge_unionpay);
        payBtn = (Button) findViewById(R.id.btn_recharge_pay);
        alipayBtn.setOnClickListener(this);
        wechatpayBtn.setOnClickListener(this);
        unionpayBtn.setOnClickListener(this);
        payBtn.setOnClickListener(this);
        check(payMethod);
    }

    @Override
    protected void initData() {
        presenter = new RechargePresenter(this);
        payMethod = ALIPAY;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(RechargeContract.Presenter presenter) {
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

    private void check(int payMethod){
        resetBtn();
        switch (payMethod){
            case ALIPAY:
                alipayChx.setSelected(true);
                break;
            case WECHATPAY:
                wechatpayChx.setSelected(true);
                break;
            case UNIONPAY:
                unionpayChx.setSelected(true);
                break;
            default:
                break;
        }
    }

    private void resetBtn(){
        alipayChx.setSelected(false);
        wechatpayChx.setSelected(false);
        unionpayChx.setSelected(false);
    }

    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.btn_recharge_pay){
           presenter.pay(payMethod);
       }else {
           switch (v.getId()){
               case R.id.btn_recharge_alipay:
                   payMethod = ALIPAY;
                   break;
               case R.id.btn_recharge_wechatpay:
                   payMethod = WECHATPAY;
                   break;
               case R.id.btn_recharge_unionpay:
                   payMethod = UNIONPAY;
                   break;
               default:
                   break;
           }
           check(payMethod);
       }
    }
}
