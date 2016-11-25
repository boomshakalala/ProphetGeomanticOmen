package com.xianzhifengshui.ui.becomemaster;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseFragment;

/**
 * 作者: chengx
 * 日期: 2016/11/23.
 * 描述:
 */
public class InfomationFragment extends BaseFragment {

    private OnNextListener onNextListener;

    public void setOnNextListener(OnNextListener onNextListener) {
        this.onNextListener = onNextListener;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }
}
