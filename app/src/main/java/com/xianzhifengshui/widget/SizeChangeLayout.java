package com.xianzhifengshui.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.zhy.autolayout.AutoRelativeLayout;

/**
 * 作者: chengx
 * 日期: 2016/12/1.
 * 描述:
 */
public class SizeChangeLayout extends AutoRelativeLayout {

    public interface OnSizeChangeListener{
        void onSizeChanged(int w, int h, int oldw, int oldh);
    }

    public SizeChangeLayout(Context context) {
        super(context);
    }

    public SizeChangeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SizeChangeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
