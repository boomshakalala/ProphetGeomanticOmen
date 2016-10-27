package com.xianzhifengshui.adapter;

import org.w3c.dom.ProcessingInstruction;

import java.io.Serializable;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class ViewSupportModel implements Serializable {
    private static final long serialVersionUID = -3205706735259288549L;
    public static int VIEW_TYPE_SPLIT_LINE = 1;
    public static int VIEW_TYPE_LABEL = 2;

    private String label;
    private boolean hasBtn;
    private int viewType;

    public ViewSupportModel(int viewType, String label, boolean hasBtn) {
        this.viewType = viewType;
        this.label = label;
        this.hasBtn = hasBtn;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isHasBtn() {
        return hasBtn;
    }

    public void setHasBtn(boolean hasBtn) {
        this.hasBtn = hasBtn;
    }
}
