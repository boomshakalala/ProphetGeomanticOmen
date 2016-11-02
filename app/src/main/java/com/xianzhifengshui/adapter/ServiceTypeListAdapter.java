package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.ServiceType;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 大师列表适配器
 */
public class ServiceTypeListAdapter extends CommonRecyclerAdapter<ServiceType> {
    public ServiceTypeListAdapter(Context context, int layoutId, List<ServiceType> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ServiceType m) {

    }
}
