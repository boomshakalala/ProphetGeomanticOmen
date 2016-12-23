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
        final ArrayList<String> imgs = (ArrayList<String>) topic.getPhoto();
        imageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ImageBrowserActivity.launcher(context, imgs, position);
            }
        });
        imageView.setList(imgs);
        holder.setText(R.id.text_topic_list_title,topic.getTitle());
        holder.setText(R.id.text_topic_list_content,topic.getContent());
        holder.setText(R.id.text_topic_list_nick_name,topic.getNickname());
        holder.setImageUrl(R.id.image_topic_list_avatar,topic.getIcon());
        holder.setText(R.id.text_topic_list_point_of_Praise,topic.getPointOfPraise()+"");
        holder.setText(R.id.text_topic_list_comment_count,topic.getComment()+"");
    }
}
