package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/17.
 * 描述: 我约过的大师列表适配器
 */
public class MyDatedMasterListAdapter extends CommonRecyclerAdapter<String> {


    public MyDatedMasterListAdapter(Context context, List<String> data, int layoutId) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {

    }
}
