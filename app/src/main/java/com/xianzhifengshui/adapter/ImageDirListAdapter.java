package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.ImageFloder;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述:
 */
public class ImageDirListAdapter extends CommonRecyclerAdapter<ImageFloder> {
    public ImageDirListAdapter(Context context, int layoutId, List<ImageFloder> data) {
        super(context, layoutId, data);
    }


    @Override
    public void convert(RecyclerViewHolder holder, ImageFloder imageFloder) {

    }
}
