package com.xianzhifengshui.ui.register;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    /*======= 控件声明区 =======*/
    private ImageView backBtn;
    private EditText phoneNumEt;
    private EditText passwordEt;
    private EditText nickNameEt;
    private EditText verifyCodeEt;
    private Button registerBtn;
    private TextView verifyCodeBtn;
    /*========================*/

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

    }
}
