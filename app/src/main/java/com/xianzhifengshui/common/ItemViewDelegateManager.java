package com.xianzhifengshui.common;

import android.support.v4.util.SparseArrayCompat;

import java.util.IllegalFormatCodePointException;

/**
 * 作者: chengx
 * 日期: 2016/10/25.
 * 描述:
 */
public class ItemViewDelegateManager<T> {
    SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat<>();

    public int getItemViewDelegateCount(){
        return delegates.size();
    }

    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate)
    {
        if (delegates.get(viewType) != null)
        {
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemViewDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }


    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate){
        int viewType = delegates.size();
        if (delegate != null) {
            delegates.put(viewType,delegate);
            viewType++;
        }
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate){
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);
        if (indexToRemove>=0){
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(int itemType){
        int indexToRemove = delegates.indexOfKey(itemType);
        if (indexToRemove>=0){
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T t,int position){
        int delegateCount = delegates.size();
        for (int i = delegateCount-1; i >=0 ; i--) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(t,position)){
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void convert(RecyclerViewHolder holder,T t,int position){
        int delegateCount = delegates.size();
        for (int i = 0; i < delegateCount; i++) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(t,position)){
                delegate.convert(holder,t,position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public ItemViewDelegate<T> getItemViewDelegate(int viewType){
        return delegates.get(viewType);
    }

    public int getItemViewLayoutId(int viewType){
        return getItemViewDelegate(viewType).getItemLayoutId();
    }

    public int getItemViewType(ItemViewDelegate<T> delegate){
        return delegates.indexOfValue(delegate);
    }
}
