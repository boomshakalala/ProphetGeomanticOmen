package com.xianzhifengshui.ui.photopicker;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.PhotoPickerAdapter;
import com.xianzhifengshui.api.model.ImageFloder;
import com.xianzhifengshui.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述: 图片选择页
 */
public class PhotoPickerActivity extends BaseActivity implements PhotoPickerContract.View {

    /*======= 控件声明区 =======*/
    private TextView chooseDirTv;
    private TextView imageCountTv;
    private RelativeLayout chooseDirBtn;
    private RecyclerView recyclerView;
    /*========================*/

    private PhotoPickerContract.Presenter presenter;
    private PhotoPickerAdapter adapter;
    private List<String> data;



    @Override
    protected void initViews() {
        chooseDirTv = (TextView) findViewById(R.id.text_photo_picker_choose_dir);
        imageCountTv = (TextView) findViewById(R.id.text_photo_picker_image_count);
        chooseDirBtn = (RelativeLayout) findViewById(R.id.btn_photo_picker_choose_dir);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        adapter = new PhotoPickerAdapter(this,R.layout.item_initiate_topic_image,data);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_photo_picker;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(PhotoPickerContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void showTip(String text) {
        showToast(text);
    }


    @Override
    public void loadImage(List<String> imgs) {
        adapter.setData(imgs);
    }

    @Override
    public void loadDir(List<ImageFloder> dirs) {

    }
}
