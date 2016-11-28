package com.xianzhifengshui.ui.addbankcard;

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
public class AddBankCardActivity extends BaseActivity {

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,AddBankCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(R.string.text_add_bank_card);
        toolbar.setNavigationIcon(R.drawable.commen_back);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }
}
