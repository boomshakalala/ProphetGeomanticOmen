package com.xianzhifengshui.adapter;

import android.content.Context;
import android.view.View;

import com.xianzhifengshui.common.MultiItemCommonAdapter;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class HomeAdapter extends MultiItemCommonAdapter<Object>{
    private HomeMasterItemDelegate masterItemDelegate;

    public HomeAdapter(Context context, List<Object> data) {
        super(context, data);
        addItemViewDelegate(new HomeBannerItemDelegate(context));
        addItemViewDelegate(new HomeLectureItemDelegate(context));
        addItemViewDelegate(new DividerItemDelegate());
        addItemViewDelegate(new HomeLabelItemDelegate());
        addItemViewDelegate(new HomeMenuItemDelegate());
        masterItemDelegate = new HomeMasterItemDelegate(context);
        addItemViewDelegate(masterItemDelegate);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
            masterItemDelegate.setOnClickListener(onClickListener);
    }
}
