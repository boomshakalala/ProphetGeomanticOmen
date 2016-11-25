package com.xianzhifengshui.ui.becomemaster;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;

import org.w3c.dom.ProcessingInstruction;

/**
 * 作者: chengx
 * 日期: 2016/11/23.
 * 描述: 成为大师页面
 */
public class BecomeMasterActivity extends BaseActivity implements OnNextListener{

    public static final int STATE_INFOMATION = 1;
    public static final int STATE_INTRODUCTION = 2;
    public static final int STATE_CONFIRM = 3;
    public static final int STATE_CHECK = 4;
    /*====== 控件声明区 =======*/
    private RelativeLayout stateLayout;
    /*=======================*/
    private InfomationFragment infomationFragment;
    private IntroductionFragment introductionFragment;

    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context, BecomeMasterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_become_master);
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
        stateLayout = (RelativeLayout) findViewById(R.id.layout_become_activity_state);
        selected(STATE_INFOMATION);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_become_master;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    private void selected(int position){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        resetState();
        LinearLayout parentIconLayout = (LinearLayout) stateLayout.getChildAt(0);
        RelativeLayout parentTextLayout = (RelativeLayout) stateLayout.getChildAt(1);
        for (int i = 0; i < position; i++) {
            if (parentIconLayout != null) {
                LinearLayout tempLayout = (LinearLayout) parentIconLayout.getChildAt(i);
                for (int j = 0; j < tempLayout.getChildCount(); j++) {
                    tempLayout.getChildAt(j).setSelected(true);
                }
            }
            if (parentTextLayout!=null){
                parentTextLayout.getChildAt(i).setSelected(true);
            }
        }
        switch (position){
            case STATE_INFOMATION:
                if (infomationFragment == null) {
                    infomationFragment = new InfomationFragment();
                    infomationFragment.setOnNextListener(this);
                    ft.add(R.id.layout_become_activity_container,infomationFragment);
                }
                ft.show(infomationFragment);
                break;
            case STATE_INTRODUCTION:
                if (introductionFragment == null) {
                    introductionFragment = new IntroductionFragment();
                    introductionFragment.setOnNextListener(this);
                    ft.add(R.id.layout_become_activity_container,introductionFragment);
                }
                ft.show(introductionFragment);
                break;
            case STATE_CONFIRM:

                break;
        }
        ft.commit();

    }

    private void resetState(){
        LinearLayout parentIconLayout = (LinearLayout) stateLayout.getChildAt(0);
        RelativeLayout parentTextLayout = (RelativeLayout) stateLayout.getChildAt(1);
        for (int i = 0; i < parentIconLayout.getChildCount(); i++) {
            if (parentIconLayout != null) {
                LinearLayout tempLayout = (LinearLayout) parentIconLayout.getChildAt(i);
                if (tempLayout != null) {
                    for (int j = 0; j < tempLayout.getChildCount(); j++) {
                        tempLayout.getChildAt(j).setSelected(false);
                    }
                }

            }
            if (parentTextLayout!=null){
                parentTextLayout.getChildAt(i).setSelected(false);
            }
        }
    }

    @Override
    public void onNext(int state) {
        switch (state){
            case STATE_INFOMATION:
                selected(STATE_INTRODUCTION);
                break;
            case STATE_INTRODUCTION:
                selected(STATE_CONFIRM);
                break;
            case STATE_CONFIRM:
                selected(STATE_CHECK);
                break;
            case STATE_CHECK:
                selected(STATE_INFOMATION);
                break;
        }
    }
}
