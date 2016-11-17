package com.xianzhifengshui.ui.initiatetopic;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.AddImageAdapter;
import com.xianzhifengshui.adapter.TagAdapter;
import com.xianzhifengshui.adapter.TagListAdapter;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.common.CommonRecyclerAdapter;
import com.xianzhifengshui.widget.dialog.NormalSelectDialog;
import com.xianzhifengshui.widget.tag.TagLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/14.
 * 描述:
 */
public class InitiateTopicActivity extends BaseActivity implements InitiateTopicContract.View, CommonRecyclerAdapter.OnRecyclerViewItemClickListener<String> {



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
        selectDialog = new NormalSelectDialog.Builder(this).build();
        selectDialog.setDataList(Arrays.asList(getResources().getStringArray(R.array.select_initiate_topic)));
    }

    @Override
    protected void initData() {
        presenter = new InitiateTopicPresenter(this);
        tagAdapter = new TagListAdapter(this, Arrays.asList(getResources().getStringArray(R.array.tag_initiate_topic)),R.layout.item_search_tag);
        result = new ArrayList<>();
        result.add("add");
        addImageAdapter = new AddImageAdapter(this,R.layout.item_initiate_topic_image,result);
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
}
