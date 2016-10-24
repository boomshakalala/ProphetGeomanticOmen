package com.xianzhifengshui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 公共的Adapter RecyclerView 拓展
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected Context context;
    protected int layoutId;
    protected List<T> data;
    protected LayoutInflater inflater;

    public CommonRecyclerAdapter(Context context, int layoutId, List<T> data) {
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecyclerViewHolder.get(context, parent, layoutId);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        convert(holder,data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public abstract void convert(RecyclerViewHolder holder, T t);

    public void setData(List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(List<T> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

}
