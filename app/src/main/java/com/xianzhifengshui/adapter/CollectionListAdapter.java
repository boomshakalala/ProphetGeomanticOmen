package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.Goods;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者：chengx
 * 日期：2016/12/12
 * 描述：
 */

public class CollectionListAdapter extends CommonRecyclerAdapter<Goods> {

    public CollectionListAdapter(Context context, int layoutId, List<Goods> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Goods goods) {

    }
}
