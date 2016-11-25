package com.xianzhifengshui.ui.initiatetopic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.AddImageAdapter;
import com.xianzhifengshui.adapter.TagAdapter;
import com.xianzhifengshui.adapter.TagListAdapter;
import com.xianzhifengshui.api.model.ImageFloder;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.ui.photopicker.PhotoPickerActivity;
import com.xianzhifengshui.utils.CameraUtils;
import com.xianzhifengshui.utils.FileUtils;
import com.xianzhifengshui.utils.ImageUtils;
import com.xianzhifengshui.utils.SDCardUtils;
import com.xianzhifengshui.widget.GridSpaceItemDecoration;
import com.xianzhifengshui.widget.dialog.DialogOnItemClickListener;
import com.xianzhifengshui.widget.dialog.NormalSelectDialog;
import com.xianzhifengshui.widget.tag.TagLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.sax.TemplatesHandler;

/**
 * 作者: chengx
 * 日期: 2016/11/14.
 * 描述:
 */
public class InitiateTopicActivity extends BaseActivity implements InitiateTopicContract.View, CommonRecyclerAdapter.OnRecyclerViewItemClickListener<String>,DialogOnItemClickListener, AddImageAdapter.OnDeleteListener {


    private final int REQUEST_CAMERA = 0x00;
    private final int REQUEST_PICTURE = 0xa1;

    /*======= 控件声明区 =======*/
    private TagLayout typeLayout;
    private EditText titleEt;
    private EditText contentEt;
    private RecyclerView recyclerView;
    private TextView initiateBtn;
    private NormalSelectDialog selectDialog;
    /*========================*/

    private InitiateTopicContract.Presenter presenter;
    private TagListAdapter tagAdapter;
    private AddImageAdapter addImageAdapter;
    private List<String> result;
    private int selectedCount;


    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context, InitiateTopicActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setTitle(getString(R.string.text_initiate_topic));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initViews() {
        typeLayout = (TagLayout) findViewById(R.id.layout_initiate_topic_tag);
        titleEt = (EditText) findViewById(R.id.edit_initiate_topic_title);
        contentEt = (EditText) findViewById(R.id.edit_initiate_topic_content);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_initiate_topic_image);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        initiateBtn = (TextView) findViewById(R.id.btn_initiate_topic);
        typeLayout.setAdapter(tagAdapter);
        recyclerView.setAdapter(addImageAdapter);
        recyclerView.addItemDecoration(new GridSpaceItemDecoration(5));
        selectDialog = new NormalSelectDialog.Builder(this).setOnItemClickListener(this).build();
        selectDialog.setDataList(Arrays.asList(getResources().getStringArray(R.array.select_initiate_topic)));

    }

    @Override
    protected void initData() {
        presenter = new InitiateTopicPresenter(this);
        tagAdapter = new TagListAdapter(this, Arrays.asList(getResources().getStringArray(R.array.tag_initiate_topic)),R.layout.item_search_tag);
        result = new ArrayList<>();
        result.add("add");
        addImageAdapter = new AddImageAdapter(this,R.layout.item_initiate_topic_image,result);
        addImageAdapter.setOnDeleteListener(this);
        addImageAdapter.setOnItemClickListener(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_initiate_topic;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(InitiateTopicContract.Presenter presenter) {
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
    public void onItemClick(View view, String data) {
        if (data.equals("add")){
            selectDialog.show();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position){
            case 0:
                startActivityForResult(CameraUtils.getOpenCameraIntent(), REQUEST_CAMERA);
                break;
            case 1:
                PhotoPickerActivity.launcher(this,REQUEST_PICTURE,selectedCount);
                selectDialog.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectedCount = 0;
        List<String> selectedImages = new ArrayList<>();
        for (String s :result) {
            if (!s.equals("add")){
                selectedImages.add(s);
                selectedCount++;
            }
        }
        if (requestCode == REQUEST_PICTURE){
            List<String> result = null;
            try {
                result = data.getStringArrayListExtra("result");
            }catch (Exception e){
                result = null;
            }
            if (result != null) {
                result.addAll(0, selectedImages);
                selectedCount = result.size();
                log("selectedCount===========>" + selectedCount);
                if (selectedCount<9)
                    result.add("add");
                this.result = result;
                log("resultSize======>"+this.result.size());
                addImageAdapter.setData(result);
            }
        }else if (requestCode == REQUEST_CAMERA){
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                String filePath = AppConfig.APP_PIC_PATH+System.currentTimeMillis()+".jpeg";
                ImageUtils.save(photo,filePath, Bitmap.CompressFormat.JPEG);
                List<String> result = new ArrayList<>();
                result.add(filePath);
                result.addAll(0, selectedImages);
                selectedCount = result.size();
                log("selectedCount===========>" + selectedCount);
                if (selectedCount<9)
                    result.add("add");
                this.result = result;
                log("resultSize======>"+this.result.size());
                addImageAdapter.setData(result);
            }

        }

    }

    @Override
    public void onDelete(RecyclerView.ViewHolder holder, String s) {
        result.remove(holder.getAdapterPosition());
        List<String> newImages = result;
        selectedCount = 0;
        for (String str:newImages) {
            if (!str.equals("add")){
                selectedCount++;
            }
        }
        if (selectedCount<9 && !newImages.contains("add"))
            newImages.add("add");
        addImageAdapter.setData(newImages);
    }
}
