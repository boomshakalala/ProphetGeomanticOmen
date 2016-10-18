package com.xianzhifengshui.widget.tag;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.CommonAdapter;
import com.xianzhifengshui.common.ViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 标签适配器
 */
public class TagAdapter extends CommonAdapter<String> {

    public TagAdapter(Context context, List<String> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder holder, String s) {
        holder.setText(R.id.text_tag,s);
    }
}
