package com.xianzhifengshui.ui.feed;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 用户反馈页面
 */
public class FeedActivity extends BaseActivity implements FeedContract.View, View.OnClickListener {

    /*======= 控件声明区 =======*/
    EditText emailEt;
    EditText contentEt;
    Button feedBackBtn;
    /*========================*/
    private FeedContract.Presenter presenter;

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,FeedActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_user_feed);
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
        emailEt = (EditText) findViewById(R.id.edit_feed_email);
        contentEt = (EditText) findViewById(R.id.edit_feed_content);
        feedBackBtn = (Button) findViewById(R.id.btn_feed_back);
        feedBackBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        presenter = new FeedPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_feed;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(FeedContract.Presenter presenter) {
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_feed_back:
                String email = emailEt.getText().toString().trim();
                String content = contentEt.getText().toString().trim();
                presenter.feedBack(email,content);
                break;
        }
    }
}
