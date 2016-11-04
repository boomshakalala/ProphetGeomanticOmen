package com.xianzhifengshui.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.ServiceType;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.TimeUtils;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 大师列表适配器
 */
public class ServiceTypeListAdapter extends CommonRecyclerAdapter<ServiceType> implements View.OnClickListener {
    public ServiceTypeListAdapter(Context context, int layoutId, List<ServiceType> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ServiceType s) {
        holder.setText(R.id.text_service_type_list_type,s.getType());
        holder.setText(R.id.text_service_type_list_price, s.getPrice() + "知币");
        holder.setOnclickListener(R.id.btn_service_type_list_reservation,this);
    }

    @Override
    public void onClick(View v) {
        //TODO:预约逻辑
    }
}
