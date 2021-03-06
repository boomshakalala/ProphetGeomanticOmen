package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.ItemViewDelegateManager;
import com.xianzhifengshui.common.MultiItemCommonAdapter;
import com.xianzhifengshui.common.MultiItemTypeSupport;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.KLog;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/24.
 * 描述: 优惠券列表适配器
 */
public class MyCouponListAdapter extends CommonRecyclerAdapter<String> {


    public MyCouponListAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {

    }
}
