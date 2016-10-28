package com.xianzhifengshui.ui.search;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/28.
 * 描述:
 */
public class SearchActivity extends BaseActivity implements SearchContract.View{



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void launcher(Activity context,View v){
        Intent intent = new Intent();
        intent.setClass(context,SearchActivity.class);
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context, v, "searchView").toBundle());
    }

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.showSearchView(true);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAfterTransition(SearchActivity.this);
            }
        });
    }


    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showFailure() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showTip(String text) {

    }
}
