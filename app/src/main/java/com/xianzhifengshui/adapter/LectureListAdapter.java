package com.xianzhifengshui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.xianzhifengshui.R;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/11.
 * 描述: 讲座列表适配器
 */
public class LectureListAdapter extends CommonRecyclerAdapter<String> implements XBanner.XBannerAdapter {
    List<String> imgesUrl = new ArrayList<>();
    public LectureListAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String s) {
        XBanner banner = holder.getView(R.id.banner);

        imgesUrl.add("http://img3.fengniao.com/forum/attachpics/913/114/36502745.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        //添加广告数据
        banner.setData(imgesUrl,null);//第二个参数为提示文字资源集合
        banner.setmAdapter(this);
    }


    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(context).load(imgesUrl.get(position)).into((ImageView) view);
    }
}
