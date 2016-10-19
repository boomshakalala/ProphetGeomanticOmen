package com.xianzhifengshui.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.ui.feed.FeedActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 帮助与反馈页面
 */
public class HelpAndFeedActivity extends BaseActivity implements View.OnClickListener {
    /*======= 控件声明区 =======*/
    private RelativeLayout aboutBtn;
    private RelativeLayout helpBtn;
    private RelativeLayout feedBtn;
    /*========================*/


    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,HelpAndFeedActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_help_and_feed);
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
        aboutBtn = (RelativeLayout) findViewById(R.id.btn_help_and_feed_about);
        helpBtn = (RelativeLayout) findViewById(R.id.btn_help_and_feed_help);
        feedBtn = (RelativeLayout) findViewById(R.id.btn_help_and_feed_feed);
        aboutBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);
        feedBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_help_and_feed;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_help_and_feed_about:
                //TODO:跳转到关于先知页面

                break;
            case R.id.btn_help_and_feed_help:
                //TODO:跳转到使用帮助页面

                break;
            case R.id.btn_help_and_feed_feed:
                //TODO:跳转到用户反馈页
                FeedActivity.launcher(this);
                break;
            default:
                break;
        }
    }
}
