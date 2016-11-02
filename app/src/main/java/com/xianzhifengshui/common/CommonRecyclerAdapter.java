package com.xianzhifengshui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public interface OnRecyclerViewItemClickListener<T> {
        void onItemClick(View view , T data);
    }

    public CommonRecyclerAdapter(Context context, int layoutId, List<T> data) {
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder holder = RecyclerViewHolder.get(context, parent, layoutId);
        View contentView = holder.getConvertView();
        if (onRecyclerViewItemClickListener != null) {
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecyclerViewItemClickListener.onItemClick(v,(T)v.getTag());
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        if (onRecyclerViewItemClickListener !=null){
            holder.getConvertView().setTag(data.get(position));
        }
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
