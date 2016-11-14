package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/9.
 * 描述:
 */
public class MyJoinedTopicAdapter extends CommonRecyclerAdapter<Topic>{
    public MyJoinedTopicAdapter(Context context, int layoutId, List<Topic> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Topic topic) {

    }
}
