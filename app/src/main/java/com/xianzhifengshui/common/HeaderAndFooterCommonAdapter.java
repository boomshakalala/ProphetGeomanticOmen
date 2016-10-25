package com.xianzhifengshui.common;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xianzhifengshui.utils.RecyclerViewUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/25.
 * 描述:
 */
public class HeaderAndFooterCommonAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ITEM_TYPE_HEADER = 100000;
    private final int ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> headerViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> footerViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter innerAdapter;

    public HeaderAndFooterCommonAdapter(RecyclerView.Adapter innerAdapter) {
        this.innerAdapter = innerAdapter;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerViews.get(viewType)!=null){
            return RecyclerViewHolder.get(parent, headerViews.get(viewType));
        }else if (footerViews.get(viewType)!=null){
            return RecyclerViewHolder.get(parent, footerViews.get(viewType));
        }

        return innerAdapter.onCreateViewHolder(parent,viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)){
            return headerViews.keyAt(position);
        }else if (isFooterViewPos(position)){
            return footerViews.keyAt(position);
        }else {
            return innerAdapter.getItemViewType(position - getHeadersCount());
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position)){
            return;
        }
        if (isFooterViewPos(position)){
            return;
        }
        innerAdapter.onBindViewHolder(holder,position-getHeadersCount());
    }


    @Override
    public int getItemCount() {
        return getHeadersCount()+getFootersCount()+getRealItemCount();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerViewUtils.onAttachedRecyclerView(innerAdapter, recyclerView, new RecyclerViewUtils.SpanSizeCallBack() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookUp, int position) {
                int viewType = getItemViewType(position);
                if (headerViews.get(viewType) != null) {
                    return layoutManager.getSpanCount();
                } else if (footerViews.get(viewType) != null) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookUp != null) {
                    return oldLookUp.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        innerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getAdapterPosition();
        if (isHeaderViewPos(position)||isFooterViewPos(position)){
            RecyclerViewUtils.setFullSpan(holder);
        }
    }

    private int getRealItemCount(){
        return innerAdapter.getItemCount();
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    public int getHeadersCount() {
        return headerViews.size();
    }

    public int getFootersCount() {
        return headerViews.size();
    }

    public void addHeaderView(View view){
        headerViews.put(headerViews.size()+ITEM_TYPE_HEADER,view);
    }

    public void addFooterView(View view){
        footerViews.put(footerViews.size()+ITEM_TYPE_FOOTER,view);
    }


}
