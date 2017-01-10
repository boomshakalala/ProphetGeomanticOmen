package com.xianzhifengshui.ui.resetpassword;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者：chengx
 * 日期：2017/1/10
 * 描述：
 */

public class ResetPasswordActivity extends BaseActivity implements ResetPasswordContract.View {

    /*======= 控件声明区 =======*/
    private EditText phoneNumEt;
    private EditText verifyCodeEt;
    private EditText passwordEt;
    private TextView verifyCodeBtn;
    private Button resetBtn;
    /*========================*/
    ResetPasswordContract.Presenter presenter;
    public static void launcher(Context context){
        Intent intent = new Intent(context,ResetPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        phoneNumEt = (EditText) findViewById(R.id.edit_reset_password_phone_number);
        verifyCodeEt = (EditText) findViewById(R.id.edit_reset_verify_code);
        passwordEt = (EditText) findViewById(R.id.edit_reset_password_new_password);
        verifyCodeBtn = (TextView) findViewById(R.id.btn_reset_password_get_verify_code);
        resetBtn = (Button) findViewById(R.id.btn_reset_password);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_forget_psd);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.drawable.commen_back);
    }

    @Override
    protected void initData() {
        presenter = new ResetPasswordPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(ResetPasswordContract.Presenter presenter) {
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
}
