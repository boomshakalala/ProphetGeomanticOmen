package com.xianzhifengshui.adapter;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;

/**
 * 作者: chengx
 * 日期: 2016/11/8.
 * 描述:
 */
public class TopicDetailLabelItemDelegate implements ItemViewDelegate<Object> {
    @Override
    public int getItemLayoutId() {
        return R.layout.item_topic_detail_label;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return o instanceof ViewSupportModel && ((ViewSupportModel) o).getViewType() == ViewSupportModel.VIEW_TYPE_LABEL;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {
        holder.setText(R.id.text_topic_detail_label,((ViewSupportModel)o).getLabel());
    }
}
