package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.HomeItemModle;
import com.xianzhifengshui.common.ItemViewDelegateManager;
import com.xianzhifengshui.common.MultiItemCommonAdapter;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class HomeAdapter extends MultiItemCommonAdapter<Object>{


    public HomeAdapter(Context context, List<Object> data) {
        super(context, data);
        addItemViewDelegate(new HomeBannerItemDelegate(context));
        addItemViewDelegate(new HomeMasterItemDelegate());
        addItemViewDelegate(new HomeLectureItemDelegate());
        addItemViewDelegate(new DividerItemDelegate());
        addItemViewDelegate(new LabelItemDelegate());
        addItemViewDelegate(new HomeMenuItemDelegate());
    }
}
