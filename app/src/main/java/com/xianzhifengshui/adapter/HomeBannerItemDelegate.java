package com.xianzhifengshui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Carousel;
import com.xianzhifengshui.api.model.HomeItemModle;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.KLog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.stx.xhb.xbanner.transformers.Transformer.Alpha;
import static com.stx.xhb.xbanner.transformers.Transformer.Cube;
import static com.stx.xhb.xbanner.transformers.Transformer.Rotate;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class HomeBannerItemDelegate implements ItemViewDelegate<Object>, XBanner.XBannerAdapter {

    private ArrayList<Carousel> data;
    private Context context;

    public HomeBannerItemDelegate(Context context) {
        this.context = context;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_home_banner;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        boolean isForViewType = false;
        try {
            ArrayList<Carousel> list = (ArrayList<Carousel>) o;
            if (list !=null && list.size()>0){
                if (list.get(0) instanceof Carousel){
                    isForViewType = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            isForViewType = false;
        }
        return isForViewType;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {
        data = (ArrayList<Carousel>) o;
        ArrayList<String> imageUrls = new ArrayList<>();
        for (Carousel carousel : data) {
            imageUrls.add(carousel.getPicUrl());
        }
        XBanner banner = holder.getView(R.id.banner);
        banner.removeAllViews();
        banner.setData(imageUrls, null);
        banner.setPageTransformer(Rotate);
        banner.setmAdapter(this);


    }


    @Override
    public void loadBanner(XBanner xBanner, View view, int i) {
        Glide.with(context).load(data.get(i).getPicUrl()).into((ImageView) view);
    }
}
