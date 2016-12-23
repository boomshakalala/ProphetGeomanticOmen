package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.HomeItemModle;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.api.model.TopicType;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 话题分类列表适配器
 */
public class TopicTypeListAdapter extends CommonRecyclerAdapter<TopicType> {
    public TopicTypeListAdapter(Context context, int layoutId, List<TopicType> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TopicType topic) {
        holder.setText(R.id.text_topic_type_list_title,topic.getTitle());
        holder.setText(R.id.text_topic_type_list_browsing_times,topic.getBrowse()+"次浏览");
        holder.setText(R.id.text_topic_type_list_attention_times,topic.getOnFocus());
    }
}
