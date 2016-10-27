package com.xianzhifengshui.adapter;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class HomeLectureItemDelegate implements ItemViewDelegate<Object> {
    @Override
    public int getItemLayoutId() {
        return R.layout.item_lecture_list;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return o instanceof Lecture;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {

    }
}
