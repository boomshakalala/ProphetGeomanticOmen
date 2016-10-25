package com.xianzhifengshui.common;

/**
 * 作者: chengx
 * 日期: 2016/10/25.
 * 描述:
 */
public interface ItemViewDelegate<T> {

    int getItemLayoutId();

    boolean isForViewType(T t,int position);

    void convert(RecyclerViewHolder holder,T t,int position);
}
