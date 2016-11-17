package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.CommonAdapter;
import com.xianzhifengshui.common.ViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/16.
 * 描述:
 */
public class TagListAdapter extends CommonAdapter<String> {
    public TagListAdapter(Context context, List<String> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, String s) {
        holder.setText(R.id.text_tag,s);
    }
}
