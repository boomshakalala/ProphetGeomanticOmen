package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.widget.tag.TagAdapter;
import com.xianzhifengshui.widget.tag.TagLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 讲座列表适配器
 */
public class LectureListAdapter extends CommonRecyclerAdapter<String> {
    public LectureListAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {

    }
}
