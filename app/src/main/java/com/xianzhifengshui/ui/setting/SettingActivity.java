package com.xianzhifengshui.ui.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.modifypassword.ModifyPasswordActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 设置页面
 */
public class SettingActivity extends BaseActivity implements SettingContract.View, View.OnClickListener {

    /*======= 控件声明区 =======*/
    private RelativeLayout phoneNumBtn;
    private RelativeLayout modifyPasswordBtn;
    private Button logoutBtn;
    /*========================*/

    private SettingContract.Presenter presenter;


    public static void launcher(BaseFragment context,int opt){
        Intent intent = new Intent();
        intent.setClass(context.getContext(),SettingActivity.class);
        context.startActivityForResult(intent,opt);
    }

    @Override
    protected void initViews() {
        phoneNumBtn = (RelativeLayout) findViewById(R.id.btn_setting_phone_number);
        modifyPasswordBtn = (RelativeLayout) findViewById(R.id.btn_setting_modify_password);
        logoutBtn = (Button) findViewById(R.id.btn_setting_logout);
        phoneNumBtn.setOnClickListener(this);
        modifyPasswordBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
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
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setTitle(R.string.text_setting);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_setting_logout:
                //退出登录
                sp.clear();
                setResult(AppConfig.RESULT_LOGOUT);
                finish();
                break;
            case R.id.btn_setting_modify_password:
                //修改密码
                ModifyPasswordActivity.launcher(this);
                break;
            case R.id.btn_setting_phone_number:
                //TODO:修改手机号
                break;
            default:
                break;
        }
    }
}
