package com.xianzhifengshui.widget.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.ImageDirListAdapter;
import com.xianzhifengshui.api.model.ImageFloder;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述: 图片路径选择弹窗
 */
public class ListImageDirPopupWindow extends PopupWindow{

    private List<ImageFloder> data;
    private RecyclerView recyclerView;
    private ImageDirListAdapter adapter;

    @SuppressLint("InflateParams")
    public ListImageDirPopupWindow(Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.widget_pop_list_image_dir, null), LinearLayout.LayoutParams.MATCH_PARENT, (int) (ScreenUtils.getScreenHeight(context) * 0.7), true);
        initData();
        initViews();


    }

    private void initData() {
        data = new ArrayList<>();
        adapter = new ImageDirListAdapter(getContext(),R.layout.item_initiate_topic_image,data);
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private View findViewById(int resId){
        return getContentView().findViewById(resId);
    }

    private Context getContext(){
        return getContentView().getContext();
    }
}
