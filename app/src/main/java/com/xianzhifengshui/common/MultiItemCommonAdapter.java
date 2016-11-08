package com.xianzhifengshui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xianzhifengshui.utils.KLog;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 支持多种ItemViewType的Adapter
 */
public abstract class MultiItemCommonAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    protected Context context;
    protected List<T> data;

    protected ItemViewDelegateManager<T> itemViewDelegateManager;

    public MultiItemCommonAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
        this.itemViewDelegateManager = new ItemViewDelegateManager<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return itemViewDelegateManager.getItemViewType(data.get(position),position);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate<T> itemViewDelegate = itemViewDelegateManager.getItemViewDelegate(viewType);
        int layoutId = itemViewDelegate.getItemLayoutId();
        RecyclerViewHolder holder = RecyclerViewHolder.get(context,parent,layoutId);
        KLog.d(getClass().getSimpleName(),"retun holder");
        return holder;
    }

    public void convert(RecyclerViewHolder holder,T t){
        itemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    @Override
    public int getItemCount() {
        int itemCount = data.size();
        return itemCount;
    }

    public MultiItemCommonAdapter<T> addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        itemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemCommonAdapter<T> addItemViewDelegate(int viewType,ItemViewDelegate<T> itemViewDelegate) {
        itemViewDelegateManager.addDelegate(viewType,itemViewDelegate);
        return this;
    }

    public ItemViewDelegate<T> getItemViewDelegate(int viewType){
        return itemViewDelegateManager.getItemViewDelegate(viewType);
    }

    protected boolean useItemViewDelegateManager(){
        return itemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public void setData(List<T> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(List<T> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
