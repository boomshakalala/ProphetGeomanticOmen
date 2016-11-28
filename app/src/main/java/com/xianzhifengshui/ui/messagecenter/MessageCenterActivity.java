package com.xianzhifengshui.ui.messagecenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/11/21.
 * 描述:
 */
public class MessageCenterActivity extends BaseActivity implements View.OnClickListener {

    /*======= 控件声明区 =======*/
    private RelativeLayout activeBtn;
    private RelativeLayout serviceBtn;
    private RelativeLayout systemBtn;
    /*========================*/



    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,MessageCenterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(getString(R.string.text_message_center));
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
        activeBtn = (RelativeLayout) findViewById(R.id.btn_message_center_active);
        serviceBtn = (RelativeLayout) findViewById(R.id.btn_message_center_service);
        systemBtn = (RelativeLayout) findViewById(R.id.btn_message_center_system);
        activeBtn.setOnClickListener(this);
        serviceBtn.setOnClickListener(this);
        systemBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_message_center;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_message_center_active:
                break;
            case R.id.btn_message_center_service:
                break;
            case R.id.btn_message_center_system:
                break;
        }
    }
}
