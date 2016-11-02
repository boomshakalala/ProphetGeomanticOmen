package com.xianzhifengshui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.CommonAdapter;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.common.ViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 标签适配器
 */
public class TagAdapter extends CommonRecyclerAdapter<String> {

    public TagAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {
        holder.setText(R.id.text_tag,s);
    }
}
