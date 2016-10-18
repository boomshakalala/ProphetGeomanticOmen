package com.xianzhifengshui.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 公共Adapter 适用于ListView GridView
 */
public abstract class CommonAdapter<T> extends BaseAdapter{
    protected LayoutInflater inflater;
    protected Context context;
    protected List<T> data;
    protected int itemLayoutId;

    public CommonAdapter(Context context, List<T> data, int itemLayoutId) {
        this.context = context;
        this.data = data;
        this.itemLayoutId = itemLayoutId;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(context, convertView, parent, itemLayoutId, position);
    }
    public abstract void convert(ViewHolder holder, T t);
}
