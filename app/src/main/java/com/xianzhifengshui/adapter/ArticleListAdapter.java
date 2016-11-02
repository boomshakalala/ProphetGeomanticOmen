package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.api.model.Article;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 评论列表适配器
 */
public class ArticleListAdapter extends CommonRecyclerAdapter<Article> {
    public ArticleListAdapter(Context context, int layoutId, List<Article> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Article m) {

    }
}
