package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.User;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 大师列表适配器
 */
public class MasterListAdapter extends CommonRecyclerAdapter<Master> {
    public MasterListAdapter(Context context, int layoutId, List<Master> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Master m) {
        holder.setText(R.id.text_master_list_title,m.getNickname());
        holder.setText(R.id.text_master_list_desc,m.getSummary());
        holder.setImageUrl(R.id.image_master_list_icon, m.getIcon());
        holder.setText(R.id.text_master_list_point_of_praise, String.valueOf(m.getPointOfPraise()));
        holder.setText(R.id.text_master_list_single_volume,String.valueOf(m.getSingleVolume()));
        holder.setText(R.id.text_master_list_level,"v"+m.getLevel());
    }
}
