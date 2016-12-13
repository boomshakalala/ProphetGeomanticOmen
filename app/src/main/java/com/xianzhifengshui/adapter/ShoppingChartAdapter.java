package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.common.MultiItemCommonAdapter;

import java.util.List;

/**
 * 作者：chengx
 * 日期：2016/12/13
 * 描述：
 */

public class ShoppingChartAdapter extends MultiItemCommonAdapter<Object> {

    public ShoppingChartAdapter(Context context, List<Object> data) {
        super(context, data);
        addItemViewDelegate(new ShoppingChartItemDelegate());
        addItemViewDelegate(new ShoppingChartLabelDelegate());
    }

}
