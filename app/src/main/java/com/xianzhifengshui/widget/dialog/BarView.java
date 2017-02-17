package com.xianzhifengshui.widget.dialog;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.xianzhifengshui.R;

/**
 * 作者: chengx
 * 日期: 2016/9/29.
 * 描述: 进度条动画效果
 */
public class BarView extends ImageView {
    private float rotateDegrees;
    private Runnable updateViewRunnable;
    private int frameTime;
    private boolean needToUpdateView;
    private int src = R.drawable.progress_bar;


    public BarView(Context context) {
        this(context,null,0);
    }

    public BarView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public BarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BarView, defStyleAttr, 0);
        this.src = a.getInt(R.styleable.BarView_bar_src,R.drawable.progress_bar);
        a.recycle();
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){

        this.setImageResource(src);
        this.updateViewRunnable = new Runnable() {
            @Override
            public void run() {
                rotateDegrees += 30.0f;
                frameTime = 83;
                rotateDegrees = rotateDegrees< 360.0f?rotateDegrees:rotateDegrees - 360.0f;
                invalidate();
                if (needToUpdateView){
                    postDelayed(this,frameTime);
                }
            }
        };
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.rotate(rotateDegrees,getWidth()/2,getHeight()/2);
        super.onDraw(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        needToUpdateView = true;
        post(updateViewRunnable);
    }

    @Override
    protected void onDetachedFromWindow() {
        needToUpdateView = false;
        super.onDetachedFromWindow();
    }
}
