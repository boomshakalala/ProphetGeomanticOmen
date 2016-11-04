package com.xianzhifengshui.ui.lecturedetail;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/11/3.
 * 描述: 讲座详情页
 */
public class LectureDetailActivity extends BaseActivity {



    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context,LectureDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(getString(R.string.text_lecture_detail));
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_lecture_detail;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }
}
