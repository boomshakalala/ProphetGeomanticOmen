package com.xianzhifengshui.ui.withdrawcrash;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/11/28.
 * 描述:
 */
public class WithdrawCrashActivity extends BaseActivity {

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,WithdrawCrashActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_withdrawal);
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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_withdraw_crash;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }
}
