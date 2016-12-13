package com.xianzhifengshui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * 作者：chengx
 * 日期：2016/12/13
 * 描述：
 */

public class LinearSpaceItemDecoration extends RecyclerView.ItemDecoration{
    private Paint paint;
    private Drawable divider;
    private int dividerHeight;


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}
