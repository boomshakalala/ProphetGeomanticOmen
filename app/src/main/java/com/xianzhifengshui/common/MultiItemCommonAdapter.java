package com.xianzhifengshui.common;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 支持多种ItemViewType的Adapter
 */
public abstract class MultiItemCommonAdapter<T> extends CommonRecyclerAdapter<T> {

    protected MultiItemTypeSupport<T> multiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> data, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, data);
        this.multiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return multiItemTypeSupport.getItemViewType(position, data.get(position));
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = multiItemTypeSupport.getLayoutId(viewType);
        return RecyclerViewHolder.get(context, parent, layoutId);
    }

}
