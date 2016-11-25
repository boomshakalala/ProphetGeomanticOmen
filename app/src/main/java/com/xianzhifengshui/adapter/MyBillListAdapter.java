package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.MultiItemCommonAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/24.
 * 描述:
 */
public class MyBillListAdapter extends MultiItemCommonAdapter<Object> {

    public MyBillListAdapter(Context context, List<Object> data) {
        super(context, data);
        addItemViewDelegate(new MyBillListItemDelegate());
        addItemViewDelegate(new MyBillDateItemDelegate());
    }
}
