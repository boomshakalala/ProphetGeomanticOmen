package com.xianzhifengshui.adapter;

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
        return 0;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return false;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {

    }
}
