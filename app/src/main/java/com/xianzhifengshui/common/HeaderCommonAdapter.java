package com.xianzhifengshui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.EventListener;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述: RecyclerView header footer 拓展
 */
public abstract class HeaderCommonAdapter<T> extends CommonRecyclerAdapter<T> {

    private final int HEADER = 1;
    private final int NORMAL = 2;
    private final int FOOTER = 3;
    private View headerView;
    private View footerView;

    public HeaderCommonAdapter(Context context, int layoutId, List<T> data) {
        super(context, layoutId, data);
    }

    @Override
    public int getItemCount() {
        int itemCount = super.getItemCount();
        if (headerView !=null&&footerView==null||headerView==null&&footerView!=null){
            return itemCount+1;
        }else if (headerView!=null && footerView!=null){
            return itemCount+2;
        }else {
            return itemCount;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0&&headerView!=null) {
            return HEADER;
        }else if (position == getItemCount()&&footerView!=null){
            return FOOTER;
        }else {
            return NORMAL;
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER){
            return new RecyclerViewHolder(context,headerView,parent);
        }else if (viewType == FOOTER){
            return new RecyclerViewHolder(context,footerView,parent);
        }else {
            return super.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (getItemViewType(getRealPosition(holder)) == NORMAL)
            super.onBindViewHolder(holder,position);
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    public void addHeaderView(View headerView){
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    public void addFooterView(View footerView){
        this.footerView = footerView;
        notifyItemInserted(getItemCount());
    }
}
