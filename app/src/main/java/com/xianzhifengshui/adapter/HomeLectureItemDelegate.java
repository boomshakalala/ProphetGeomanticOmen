package com.xianzhifengshui.adapter;

import android.content.Context;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.ui.lecturedetail.LectureDetailActivity;

/**
 * 作者: chengx
 * 日期: 2016/10/27.
 * 描述:
 */
public class HomeLectureItemDelegate implements ItemViewDelegate<Object> {
    Context context;

    public HomeLectureItemDelegate(Context context) {
        this.context = context;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_lecture_list;
    }

    @Override
    public boolean isForViewType(Object o, int position) {
        return o instanceof Lecture;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object o, int position) {
        final Lecture lecture = (Lecture) o;
        holder.setText(R.id.text_lecture_list_price,"￥"+lecture.getSellPrice());
        holder.setText(R.id.text_lecture_list_master,lecture.getMasterName());
        holder.setText(R.id.text_lecture_list_title,lecture.getTitle());
        holder.setText(R.id.text_lecture_list_collect,lecture.getCollection());
        holder.setText(R.id.text_lecture_list_date,lecture.getStartTime());
        holder.setText(R.id.text_lecture_list_rank,lecture.getMasterDesc());
        holder.setText(R.id.text_lecture_list_remain_seats,"余"+lecture.getRemainSeats()+"席");
        holder.setImageUrl(R.id.image_lecture_list_icon,lecture.getMasterIcon());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lectureCode = lecture.getLecturesCode();
                LectureDetailActivity.launcher(context,lectureCode);
            }
        });
    }
}
