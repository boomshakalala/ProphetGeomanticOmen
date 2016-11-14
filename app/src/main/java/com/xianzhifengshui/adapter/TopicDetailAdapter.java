package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.common.MultiItemCommonAdapter;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/8.
 * 描述: 话题详情适配器
 */
public class TopicDetailAdapter extends MultiItemCommonAdapter<Object> {
    public TopicDetailAdapter(Context context, List<Object> data) {
        super(context, data);
        addItemViewDelegate(new TopicDetailLabelItemDelegate());
        addItemViewDelegate(new TopicDetailEvaluateItemDelegate());
        addItemViewDelegate(new TopicDetailItemDelegate());
        addItemViewDelegate(new DividerItemDelegate());
    }
}
