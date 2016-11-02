package com.xianzhifengshui.widget.tag;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhy.autolayout.utils.AutoLayoutHelper;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 标签布局
 */
public class TagLayout extends ViewGroup{

    private final AutoLayoutHelper helper = new AutoLayoutHelper(this);
    private int lineSpacing;
    private int tagSpacing;
    private BaseAdapter tagAdapter;

    private TagItemClickListener listener;
    private DataChangeObserver observer;


    public TagLayout(Context context) {
        super(context);
        init(context,null,0);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context,AttributeSet attrs,int defStyleAttr){
        TagConfig config = new TagConfig(context,attrs);
        lineSpacing = config.getLineSpacing();
        tagSpacing = config.getTagSpacing();
    }

    private void drawLayout(){
        if (tagAdapter == null||tagAdapter.getCount() == 0) {
            return;
        }
        this.removeAllViews();
        for (int i = 0; i < tagAdapter.getCount(); i++) {
            View view = tagAdapter.getView(i,null,null);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.itemClick(position);
                    }
                }
            });
            this.addView(view);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()){
            helper.adjustChildren();
        }
        int wantHeight = 0;
        int wantWidth = resolveSize(0,widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int childLeft = paddingLeft;
        int childTop = paddingTop;
        int lineHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            LayoutParams params = childView.getLayoutParams();
            childView.measure(getChildMeasureSpec(widthMeasureSpec,paddingLeft+paddingRight,params.width),
                    getChildMeasureSpec(heightMeasureSpec,paddingTop+paddingBottom,params.height));
            int childHeight = childView.getMeasuredHeight();
            int childWidth = childView.getMeasuredWidth();
            lineHeight = Math.max(lineHeight,childHeight);
            if (childLeft+childWidth+paddingRight>wantWidth){
                childLeft = paddingLeft;
                childTop+=childHeight+lineSpacing;
                lineHeight = childHeight;
            }
            childLeft+=tagSpacing+childWidth;
        }
        wantHeight+=childTop+lineHeight+paddingBottom;
        setMeasuredDimension(wantWidth, resolveSize(wantHeight, heightMeasureSpec));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int childLeft = paddingLeft;
        int childTop = paddingTop;
        int lineHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == View.GONE)
                continue;
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            lineHeight = Math.max(lineHeight,childHeight);
            if (childLeft+childWidth+paddingRight>width){
                childLeft = paddingLeft;
                childTop+=lineHeight+lineSpacing;
                lineHeight = childHeight;
            }
            childView.layout(childLeft,childTop,childLeft+childWidth,childTop+childHeight);
            childLeft+=childWidth+tagSpacing;
        }
    }
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(this.getContext(), attrs);
    }

    public void setAdapter(BaseAdapter adapter){
        if (tagAdapter == null) {
            this.tagAdapter = adapter;
            if (observer == null) {
                observer = new DataChangeObserver();
                adapter.registerDataSetObserver(observer);
            }
            drawLayout();
        }
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public int getTagSpacing() {
        return tagSpacing;
    }

    public void setTagSpacing(int tagSpacing) {
        this.tagSpacing = tagSpacing;
    }

    public TagItemClickListener getTagItemListener() {
        return listener;
    }

    public void setTagItemListener(TagItemClickListener tagItemListener) {
        this.listener = tagItemListener;
    }

    class DataChangeObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            TagLayout.this.drawLayout();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }
}
