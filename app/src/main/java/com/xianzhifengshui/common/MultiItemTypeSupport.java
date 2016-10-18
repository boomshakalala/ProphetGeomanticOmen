package com.xianzhifengshui.common;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: MultiItemCommonAdapter 辅助接口
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int itemType);

    int getItemViewType(int position,T t);
}
