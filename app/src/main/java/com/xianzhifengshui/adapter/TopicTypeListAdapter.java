package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 话题分类列表适配器
 */
public class TopicTypeListAdapter extends CommonRecyclerAdapter<Topic> {
    public TopicTypeListAdapter(Context context, int layoutId, List<Topic> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Topic topic) {

    }
}
