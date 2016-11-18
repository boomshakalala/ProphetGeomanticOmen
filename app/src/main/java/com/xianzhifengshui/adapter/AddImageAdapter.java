package com.xianzhifengshui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.xianzhifengshui.R;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.KLog;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述: 发帖页图片适配器
 */
public class AddImageAdapter extends CommonRecyclerAdapter<String> {

    public AddImageAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {
        KLog.d(TAG,s);
        if (s.equals("add")){
            holder.setImageResource(R.id.btn_initiate_topic_add,R.drawable.initiate_topic_add_image);
            holder.setVisibility(R.id.btn_initiate_topic_delete, View.GONE);
        }else {
            holder.setImageUrlCenterCroup(R.id.btn_initiate_topic_add,s);
            holder.setVisibility(R.id.btn_initiate_topic_delete,View.VISIBLE);
        }
    }
}
