package com.xianzhifengshui.ui.photopicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.PhotoPickerAdapter;
import com.xianzhifengshui.api.model.ImageFloder;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.widget.GridSpaceItemDecoration;
import com.xianzhifengshui.widget.popup.ListImageDirPopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述: 图片选择页
 */
public class PhotoPickerActivity extends BaseActivity implements PhotoPickerContract.View, ListImageDirPopupWindow.OnImageDirSelectedListener, View.OnClickListener, PopupWindow.OnDismissListener {

    /*======= 控件声明区 =======*/
    private TextView chooseDirTv;
    private TextView imageCountTv;
    private RelativeLayout chooseDirBtn;
    private RecyclerView recyclerView;
    private ListImageDirPopupWindow popupWindow;
    /*========================*/

    private PhotoPickerContract.Presenter presenter;
    private PhotoPickerAdapter adapter;
    private List<String> data;


    public static void launcher(Activity context,int requestCode,int selectedCount){
        Intent intent = new Intent();
        intent.setClass(context,PhotoPickerActivity.class);
        intent.putExtra("selectedCount",selectedCount);
        context.startActivityForResult(intent,requestCode);
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_image);
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setRightBtnText(R.string.text_confrim);
        toolbar.setOnRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("result",adapter.getSelectedImage());
                setResult(0,intent);
                finish();
            }
        });
    }

    @Override
    protected void initViews() {
        chooseDirTv = (TextView) findViewById(R.id.text_photo_picker_choose_dir);
        imageCountTv = (TextView) findViewById(R.id.text_photo_picker_image_count);
        chooseDirBtn = (RelativeLayout) findViewById(R.id.btn_photo_picker_choose_dir);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new GridSpaceItemDecoration(1,3));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
        popupWindow = new ListImageDirPopupWindow(this);
        popupWindow.setOnImageDirSelectedListener(this);
        popupWindow.setOnDismissListener(this);
        chooseDirBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        adapter = new PhotoPickerAdapter(this,R.layout.item_photo_picker,data);
        presenter = new PhotoPickerPresenter(this);
        adapter.setSelectedCount(getIntent().getIntExtra("selectedCount",0));
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
    public void loadImage(ImageFloder floder) {
        adapter.setData(floder.getImags());
        if (popupWindow.isShowing())
            popupWindow.dismiss();
        chooseDirTv.setText(floder.getDirName());
        imageCountTv.setText(floder.getImageCount()+"张");
    }

    @Override
    public void loadDir(List<ImageFloder> dirs) {
        popupWindow.loadDir(dirs);
    }

    @Override
    public void onSelected(ImageFloder floder) {
        loadImage(floder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_photo_picker_choose_dir:
                if (popupWindow != null) {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = .3f;
                    getWindow().setAttributes(lp);
                    popupWindow.showAsDropDown(chooseDirBtn,0,0);
                }
                break;
        }

    }

    @Override
    public void onDismiss() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
    }
}
