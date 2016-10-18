package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.widget.tag.TagAdapter;
import com.xianzhifengshui.widget.tag.TagLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 大师列表适配器
 */
public class MasterListAdapter extends CommonRecyclerAdapter<String> {
    public MasterListAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {
        TagLayout tagLayout = holder.getView(R.id.layout_master_list_tag);
        List<String> tags = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tags.add("家居风水");
        }
        tagLayout.setAdapter(new TagAdapter(context,tags,R.layout.layout_master_list_tag));
    }
}
