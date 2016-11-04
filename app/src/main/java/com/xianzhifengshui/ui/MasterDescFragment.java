package com.xianzhifengshui.ui;

import android.text.Html;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.widget.auto.AutoScrollView;

/**
 * 作者: chengx
 * 日期: 2016/11/1.
 * 描述: 大师详细介绍页
 */
public class MasterDescFragment extends BaseFragment {

    /*======= 控件声明区 =======*/
    private TextView descTv;
    /*=========================*/

    @Override
    protected void initViews() {
        descTv = (TextView) rootView.findViewById(R.id.text_master_desc);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_master_desc;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    public void setContent(String desc){
        descTv.setText(Html.fromHtml(desc));
    }
}
