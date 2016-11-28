package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.BankCard;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/28.
 * 描述:
 */
public class BankCardListAdapter extends CommonRecyclerAdapter<BankCard> {

    public BankCardListAdapter(Context context, int layoutId, List<BankCard> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, BankCard bankCard) {

    }
}
