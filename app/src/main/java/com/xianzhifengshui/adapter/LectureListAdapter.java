package com.xianzhifengshui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 讲座列表适配器
 */
public class LectureListAdapter extends CommonRecyclerAdapter<Lecture>{
    public LectureListAdapter(Context context, int layoutId, List<Lecture> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Lecture lecture) {

    }



}
