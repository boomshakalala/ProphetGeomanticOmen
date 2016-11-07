package com.xianzhifengshui.adapter;

import android.content.Context;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Topic;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.ui.ImageBrowserActivity;
import com.xianzhifengshui.widget.MultiImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 作者: chengx
 * 日期: 2016/11/7.
 * 描述: 话题列表适配器
 */
public class TopicListAdapter extends CommonRecyclerAdapter<Topic> {
    public TopicListAdapter(Context context, int layoutId, List<Topic> data) {
        super(context, layoutId, data);
    }

    @Override
    public void convert(RecyclerViewHolder holder, Topic topic) {
        MultiImageView imageView = holder.getView(R.id.image_topic_list_images);

        final ArrayList<String> imgs = new ArrayList<>();
        String[] imgUrls = {"http://pic7.nipic.com/20100609/5136651_124423001651_2.jpg",
                "http://g.hiphotos.baidu.com/image/pic/item/9e3df8dcd100baa1cb7acbdb4210b912c8fc2e7f.jpg",
                "http://f.hiphotos.baidu.com/image/pic/item/32fa828ba61ea8d360a750ea930a304e241f5852.jpg",
                "http://i2.cqnews.net/car/attachement/jpg/site82/20120817/5404a6b61e3c1197fb211d.jpg",
                "http://h.hiphotos.baidu.com/image/pic/item/dc54564e9258d1093cf78e5cd558ccbf6d814dc3.jpg",
                "http://a.hiphotos.baidu.com/image/pic/item/279759ee3d6d55fb924d52c869224f4a21a4dd50.jpg",
                "http://e.hiphotos.baidu.com/image/pic/item/738b4710b912c8fc7778d223f8039245d7882150.jpg",
                "http://f.hiphotos.baidu.com/image/pic/item/203fb80e7bec54e753da379aba389b504fc26a7b.jpg",
                "http://d.hiphotos.baidu.com/image/pic/item/562c11dfa9ec8a13f075f10cf303918fa1ecc0eb.jpg",};
        int count = new Random().nextInt(10);
        imgs.addAll(Arrays.asList(imgUrls).subList(0, count));
        imageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageBrowserActivity.launcher(context, imgs, position);
            }
        });
        imageView.setList(imgs);

    }
}
