package com.xianzhifengshui.ui.modifypassword;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 修改密码页
 */
public class ModifyPasswordActivity extends BaseActivity implements ModifyPasswordContract.View, View.OnClickListener {

    /*======= 控件声明区 =======*/
    EditText orignalPasswordEt;
    EditText newPasswordEt;
    EditText confirmPasswordEt;
    Button modifyPasswordBtn;
    /*========================*/

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,ModifyPasswordActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_modify_password);
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
        orignalPasswordEt = (EditText) findViewById(R.id.edit_modify_password_original_password);
        newPasswordEt = (EditText) findViewById(R.id.edit_modify_password_new_password);
        confirmPasswordEt = (EditText) findViewById(R.id.edit_modify_password_confirm_password);
        modifyPasswordBtn = (Button) findViewById(R.id.btn_modify_password);
        modifyPasswordBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setPresenter(ModifyPasswordContract.Presenter presenter) {

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
            case R.id.btn_modify_password:
                break;
            default:
                break;
        }
    }
}
