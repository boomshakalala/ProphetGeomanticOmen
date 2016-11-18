package com.xianzhifengshui.widget.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.ImageDirListAdapter;
import com.xianzhifengshui.api.model.ImageFloder;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述: 图片路径选择弹窗
 */
public class ListImageDirPopupWindow extends PopupWindow implements View.OnTouchListener, CommonRecyclerAdapter.OnRecyclerViewItemClickListener<ImageFloder> {

    private List<ImageFloder> data;
    private RecyclerView recyclerView;
    private ImageDirListAdapter adapter;
    private OnImageDirSelectedListener onImageDirSelectedListener;

    public interface OnImageDirSelectedListener{
        void onSelected(ImageFloder floder);
    }

    public void setOnImageDirSelectedListener(OnImageDirSelectedListener onImageDirSelectedListener) {
        this.onImageDirSelectedListener = onImageDirSelectedListener;
    }

    public ListImageDirPopupWindow(Context context) {
        super(View.inflate(context, R.layout.widget_pop_list_image_dir, null), LinearLayout.LayoutParams.MATCH_PARENT, (int) (ScreenUtils.getScreenHeight(context) * 0.7), true);
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        setTouchable(true);
        setTouchInterceptor(this);
        setAnimationStyle(R.style.bottomDialogAnim);
        initData();
        initViews();
    }

    private void initData() {
        data = new ArrayList<>();
        adapter = new ImageDirListAdapter(getContext(),R.layout.item_photo_picker_pop,data);
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private View findViewById(int resId){
        return getContentView().findViewById(resId);
    }

    private Context getContext(){
        return getContentView().getContext();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE){
            dismiss();
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(View view, ImageFloder data) {
        if (onImageDirSelectedListener != null) {
            onImageDirSelectedListener.onSelected(data);
        }
    }

    public void loadDir(List<ImageFloder> dirs){
        adapter.setData(dirs);
    }
}
