package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/22.
 * 描述: 下拉列表弹窗适配器
 */
public class ListPopupAdapter extends CommonRecyclerAdapter<String> {

    public ListPopupAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {
        holder.setText(R.id.text_select_pop_item,s);
    }
}
