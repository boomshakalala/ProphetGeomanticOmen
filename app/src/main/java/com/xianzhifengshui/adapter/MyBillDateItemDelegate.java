package com.xianzhifengshui.adapter;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public class MyBillDateItemDelegate implements ItemViewDelegate<Object> {
    @Override
    public int getItemLayoutId() {
        return R.layout.item_my_bill_date;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return o instanceof String;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {

    }
}
