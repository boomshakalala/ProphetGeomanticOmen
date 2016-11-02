package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.Evaluate;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 评论列表适配器
 */
public class EvaluateListAdapter extends CommonRecyclerAdapter<Evaluate> {
    public EvaluateListAdapter(Context context, int layoutId, List<Evaluate> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Evaluate m) {

    }
}
