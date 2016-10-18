package com.xianzhifengshui.widget.tag;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.xianzhifengshui.R;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: TagLayout配置文件
 */
public class TagConfig {

    //默认行间距
    private final int DEFAULT_LINE_SPACING = 5;

    //默认标签间距
    private final int DEFAULT_TAG_SPACING = 10;

    //默认列数
    private final int DEFAULT_COLUMN_SIZE = 3;

    private int lineSpacing;
    private int tagSpacing;
    private int columnSize;

    public TagConfig(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TagLayout);
        try {
            lineSpacing = typedArray.getDimensionPixelSize(R.styleable.TagLayout_lineSpacing,DEFAULT_LINE_SPACING);
            tagSpacing = typedArray.getDimensionPixelSize(R.styleable.TagLayout_tagSpacing,DEFAULT_TAG_SPACING);
            columnSize = typedArray.getDimensionPixelSize(R.styleable.TagLayout_columnSize,DEFAULT_COLUMN_SIZE);
        }finally {
            typedArray.recycle();
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

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }
}
