package com.xianzhifengshui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.ToastUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述:
 */
public class PhotoPickerAdapter extends CommonRecyclerAdapter<String> {

    private ArrayList<String> selectedImage = new ArrayList<>();

    private int selectedCount = 0;

    public PhotoPickerAdapter(Context context, int layoutId, List<String> data) {
        super(context, layoutId, data);

    }



    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }

    public ArrayList<String> getSelectedImage() {
        return selectedImage;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, final String s) {
        KLog.d(TAG, s);
        holder.setImageResource(R.id.image_photo_picker, R.drawable.pictures_no);
        holder.setImageResource(R.id.btn_photo_picker_selected, R.drawable.picture_unselected);
        holder.setImageUrlCenterCroup(R.id.image_photo_picker, s);
        final ImageView imageView = holder.getView(R.id.image_photo_picker);
        imageView.setColorFilter(null);
        holder.setOnclickListener(R.id.image_photo_picker, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImage.contains(s)) {
                    selectedImage.remove(s);
                    holder.setImageResource(R.id.btn_photo_picker_selected, R.drawable.picture_unselected);
                    imageView.setColorFilter(null);
                } else {
                    if (selectedImage.size() >= 9-selectedCount) {
                        ToastUtils.showToast(context,"最多只能选择9张图片");
                        return;
                    }
                    selectedImage.add(s);
                    holder.setImageResource(R.id.btn_photo_picker_selected, R.drawable.pictures_selected);
                    imageView.setColorFilter(Color.parseColor("#88000000"));
                }
            }
        });
        if (selectedImage.contains(s)){
            holder.setImageResource(R.id.btn_photo_picker_selected,R.drawable.pictures_selected);
            imageView.setColorFilter(Color.parseColor("#88000000"));
        }

    }
}
