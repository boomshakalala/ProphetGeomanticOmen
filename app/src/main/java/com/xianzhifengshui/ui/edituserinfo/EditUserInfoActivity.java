package com.xianzhifengshui.ui.edituserinfo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 编辑用户资料页面
 */
public class EditUserInfoActivity extends BaseActivity implements EditUserInfoContract.View, View.OnClickListener {

    /*======= 控件声明区 =======*/
    private Button saveBtn;
    private RelativeLayout avatarBtn;
    /*========================*/

    EditUserInfoContract.Presenter presenter;


    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,EditUserInfoActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initViews() {
        saveBtn = (Button) findViewById(R.id.btn_edit_user_info_save);
        avatarBtn = (RelativeLayout) findViewById(R.id.btn_edit_user_info_avatar);
        saveBtn.setOnClickListener(this);
        avatarBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_edit_user_info;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_edit_user_info);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void updateUserInfo() {

    }

    @Override
    public void updateUserAvatar() {

    }


    @Override
    public void setPresenter(EditUserInfoContract.Presenter presenter) {
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {

        }
    }
}
