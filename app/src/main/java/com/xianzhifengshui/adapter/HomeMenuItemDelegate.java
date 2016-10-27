package com.xianzhifengshui.adapter;

import android.support.v7.view.menu.MenuView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.NaviMenu;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class HomeMenuItemDelegate implements ItemViewDelegate<Object> {
    @Override
    public int getItemLayoutId() {
        return R.layout.item_home_menu;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return o instanceof NaviMenu;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {

    }
}
