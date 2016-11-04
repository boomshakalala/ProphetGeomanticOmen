package com.xianzhifengshui.widget.auto;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.utils.AutoLayoutHelper;


public class AutoScrollView extends ScrollView {
    private final AutoLayoutHelper helper = new AutoLayoutHelper(this);

    public AutoScrollView(Context context) {
        super(context);
    }

    public AutoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()){
            helper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    public AutoScrollView.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new AutoScrollView.LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends ScrollView.LayoutParams implements AutoLayoutHelper.AutoLayoutParams{

        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c,attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int width, int height, int gravity) {
            super(width, height, gravity);

        }

        public LayoutParams(FrameLayout.LayoutParams source) {
            super((MarginLayoutParams) source);
            gravity = source.gravity;
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(LayoutParams source)
        {
            this((FrameLayout.LayoutParams) source);
            mAutoLayoutInfo = source.mAutoLayoutInfo;
        }
        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return mAutoLayoutInfo;
        }
    }

}
