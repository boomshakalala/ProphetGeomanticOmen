package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.Order;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者：chengx
 * 日期：2016/12/16
 * 描述：
 */

public class ShopOrderAdapter extends CommonRecyclerAdapter<Order> {
    public ShopOrderAdapter(Context context, int layoutId, List<Order> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Order order) {

    }
}
