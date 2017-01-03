package com.xianzhifengshui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.xianzhifengshui.R;
import com.xianzhifengshui.utils.SPUtils;
import com.xianzhifengshui.widget.EmptyLayout;
import com.xianzhifengshui.widget.auto.AutoToolbar;


/**
 * 作者: chengx
 * 日期: 2016/9/29.
 * 描述: 自定义Fragment基类
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG;
    protected BaseActivity activity;
    public SPUtils sp;
    protected boolean needToolBar = true;

    protected View rootView;
    protected AutoToolbar toolbar;
    protected EmptyLayout emptyLayout;
    protected boolean isActive;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initData();

    }

    private void init() {
        TAG = getClass().getSimpleName();
        activity = (BaseActivity) getActivity();
        sp = activity.sp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getContentLayoutId(),container,false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyLayout = (EmptyLayout) rootView.findViewById(R.id.emptyLayout);
        initViews();
        if (needToolBar){
            initToolbar();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        isActive = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        isActive = false;
    }

    /**
     * 初始化View
     */
    protected abstract void initViews();

    /**
     * 初始化Data
     */
    protected abstract void initData();

    /**
     * 初始化Toolbar
     */
    protected void initToolbar() {
        toolbar = (AutoToolbar) rootView.findViewById(R.id.toolbar);
    }

    /**
     * 获取布局id
     * @return layoutId
     */
    protected abstract int getContentLayoutId();

    /**
     * 是否需要toolbar
     * @return true or false
     */
    protected abstract boolean isNeedToolbar();

    protected void showToast(String text){
        activity.showToast(text);
    }

    public void showWaiting(){
        activity.showWaiting();
    }

    public boolean isProgressDialogShowing(){
        return activity.isProgressDialogShowing();
    }

    public void closeWait(){
        activity.closeWait();
    }

    protected void log(Object... objects){
        activity.log(objects);
    }

    protected void logJson(String json){
        activity.logJson(json);
    }

    public void toLoginActivity(Context context){
        activity.toLoginActivity(context);
    }
}
