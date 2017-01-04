package com.xianzhifengshui.adapter;

import android.content.Context;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.MasterDetailModel;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.ui.masterdetail.MasterDetailActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class HomeMasterItemDelegate implements ItemViewDelegate<Object> {
    private View.OnClickListener onClickListener;
    private Context context;

    public HomeMasterItemDelegate(Context context) {
        this.context = context;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_master_list;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return o instanceof Master;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, Object o, int position) {
        final Master m = (Master) o;
        holder.setText(R.id.text_master_list_title,m.getNickname());
        holder.setText(R.id.text_master_list_desc,m.getSummary());
        holder.setImageUrl(R.id.image_master_list_icon, m.getIcon());
        holder.setText(R.id.text_master_list_point_of_praise, String.valueOf(m.getPointOfPraise()));
        holder.setText(R.id.text_master_list_single_volume,String.valueOf(m.getSingleVolume()));
        holder.setText(R.id.text_master_list_level,"v"+m.getLevel());
        holder.setSelected(R.id.text_master_list_point_of_praise,false);
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masterCode = m.getMasterCode();
                MasterDetailActivity.launcher(context,masterCode);
            }
        });
        if (onClickListener != null) {
            holder.setOnclickListener(R.id.text_master_list_point_of_praise, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setSelected(true);
                    int position = holder.getAdapterPosition();
                    //TODO:更改列表中点赞状态
                    view.setTag(view.getId(),m);
                    onClickListener.onClick(view);
                }
            });
        }

    }
}
