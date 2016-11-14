package com.xianzhifengshui.adapter;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;

/**
 * 作者: chengx
 * 日期: 2016/11/8.
 * 描述:
 */
public class TopicDetailItemDelegate implements ItemViewDelegate<Object> {
    @Override
    public int getItemLayoutId() {
        return R.layout.item_topic_detail_content;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return o instanceof Topic;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {

    }
}
