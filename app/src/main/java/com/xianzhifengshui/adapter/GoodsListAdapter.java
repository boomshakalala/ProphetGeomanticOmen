package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.common.CommonAdapter;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：
 */

public class GoodsListAdapter extends CommonRecyclerAdapter<Object> {

    public GoodsListAdapter(Context context, int layoutId, List<Object> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o) {

    }
}
