package com.xianzhifengshui.adapter;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Goods;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;

/**
 * 作者：chengx
 * 日期：2016/12/13
 * 描述：
 */

public class ShoppingChartItemDelegate implements ItemViewDelegate<Object> {
    @Override
    public int getItemLayoutId() {
        return R.layout.item_shopping_chart;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return o instanceof Goods;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {
        Goods goods = (Goods) o;

    }
}
