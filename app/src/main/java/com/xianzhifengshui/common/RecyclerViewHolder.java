package com.xianzhifengshui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xianzhifengshui.utils.XImageLoader;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 通用的ViewHolder RecyclerView 拓展
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> views;
    private View convertView;
    private Context context;

    public RecyclerViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        this.context = context;
        convertView = itemView;
        views = new SparseArray<>();
        AutoUtils.auto(itemView);
    }
    public static RecyclerViewHolder get(Context context,ViewGroup parent,int layoutId){
        View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        return new RecyclerViewHolder(context,itemView,parent);
    }

    public static RecyclerViewHolder get(ViewGroup parent,View itemView){
        return new RecyclerViewHolder(parent.getContext(),itemView,parent);
    }

    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T)view;
    }

    /*======= ViewHolder辅助方法 =======*/

    /**
     * 设置TextView的值
     * @param viewId TextView id
     * @param text TextView value
     * @return this
     */
    public RecyclerViewHolder setText(int viewId,String text){
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 设置ImageView的值
     * @param viewId ImageView id
     * @param resId ImageView value
     * @return this
     */
    public RecyclerViewHolder setImageResource(int viewId,int resId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     * ImageView 加载网络图片
     * @param viewId ImageView id
     * @param imageUrl 图片地址
     * @return this
     */
    public RecyclerViewHolder setImageUrl(int viewId,String imageUrl){
        ImageView imageView = getView(viewId);
        XImageLoader.getInstance().display(imageView, imageUrl);
        return this;
    }

    /**
     * 添加点击事件监听
     * @param viewId View id
     * @param listener onClickListener
     * @return this
     */
    public RecyclerViewHolder setOnclickListener(int viewId,View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    /*=================================*/

}
