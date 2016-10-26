package com.xianzhifengshui.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 公共ViewHolder
 */
public class ViewHolder
{
    private final SparseArray<View> views;
    private int position;
    private View convertView;
    private Context context;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.position = position;
        this.views = new SparseArray<>();
        this.context = context;
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        AutoUtils.autoSize(convertView);
        convertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     * @param context 上下文
     * @param convertView itemView
     * @param parent listView or gridView
     * @param layoutId itemView layout id
     * @param position current position
     * @return viewHolder
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return convertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId 控件id
     * @return view
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId textView id
     * @param text text
     * @return this
     */
    public ViewHolder setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId imageView id
     * @param drawableId imageRes
     * @return this
     */
    public ViewHolder setImageResource(int viewId, int drawableId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId imageView id
     * @param bm bitmap
     * @return this
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId imageView id;
     * @param url 图片地址
     * @return this
     */
    public ViewHolder setImageByUrl(int viewId, String url) {
        ImageView view = getView(viewId);
        Glide.with(context).load(url).into(view);
        return this;
    }

    public int getPosition() {
        return position;
    }

}
