package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 话题分类列表适配器
 */
public class TopicListAdapter extends CommonRecyclerAdapter<String> {
    public TopicListAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {

    }
}
