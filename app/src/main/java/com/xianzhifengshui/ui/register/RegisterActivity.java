package com.xianzhifengshui.ui.register;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener,RegisterContract.View {

    /*======= 控件声明区 =======*/
    private ImageView backBtn;
    private EditText phoneNumEt;
    private EditText passwordEt;
    private EditText nickNameEt;
    private EditText verifyCodeEt;
    private Button registerBtn;
    private TextView verifyCodeBtn;
    /*========================*/
    private RegisterContract.Presenter presenter;

    public static void luncher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        backBtn = (ImageView) findViewById(R.id.btn_register_back);
        phoneNumEt = (EditText) findViewById(R.id.edit_register_username);
        passwordEt = (EditText) findViewById(R.id.edit_register_password);
        nickNameEt = (EditText) findViewById(R.id.edit_register_nickname);
        verifyCodeEt = (EditText) findViewById(R.id.edit_register_verify_code);
        registerBtn = (Button) findViewById(R.id.btn_register);
        verifyCodeBtn = (TextView) findViewById(R.id.btn_register_get_verify_code);
        backBtn.setOnClickListener(this);
        verifyCodeBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        presenter = new RegisterPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_back:
                //返回上一级页面
                this.finish();
                break;
            case R.id.btn_register:
                //TODO:注册
                String phoneNum = phoneNumEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                String nickName = nickNameEt.getText().toString().trim();
                String verifyCode = verifyCodeEt.getText().toString().trim();
                presenter.register(phoneNum,password,nickName,verifyCode);
                break;
            case R.id.btn_register_get_verify_code:
                //TODO:获取验证码
                phoneNum = phoneNumEt.getText().toString().trim();
                if (phoneNum!=null&&!TextUtils.isEmpty(phoneNum)){
                    presenter.getVerifyCode(phoneNum);
                }else {
                    showTip("请输入手机号");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
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
    public void setTimeCount(String timeCount) {
        verifyCodeBtn.setText(timeCount);
    }

    @Override
    public void setClickble(boolean clickble) {
        verifyCodeBtn.setClickable(clickble);
    }

    @Override
    public void setVerifyCode(String verifyCode) {
        verifyCodeEt.setText(verifyCode);
    }
}
