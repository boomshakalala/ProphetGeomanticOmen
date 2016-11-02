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
        descTv.setText(Html.fromHtml("张三丰，文始派传人，武当派祖师，著有《玄机直讲》、《玄要篇》等。对商务风水和良辰吉日有独到的见解。<br/>张先师曾先后主持过多项大型商务风水项目，获得业界一致好评，在风水行业有着举足轻重的地位。"));
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
