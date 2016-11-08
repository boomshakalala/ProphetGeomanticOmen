package com.xianzhifengshui.adapter;

import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class HomeLabelItemDelegate implements ItemViewDelegate<Object> {
    @Override
    public int getItemLayoutId() {
        return R.layout.item_home_label;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return o instanceof ViewSupportModel && ((ViewSupportModel) o).getViewType() == ViewSupportModel.VIEW_TYPE_LABEL;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {
        holder.setVisibility(R.id.btn_change,((ViewSupportModel)o).isHasBtn()? View.VISIBLE: View.GONE);
        holder.setText(R.id.text_home_label,((ViewSupportModel)o).getLabel());
    }
}
