package com.xianzhifengshui.ui;

import android.content.Context;
import android.content.Intent;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.widget.CircleIndicator;
import com.xianzhifengshui.widget.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/7.
 * 描述: 图片浏览页面
 */
public class ImageBrowserActivity extends BaseActivity {

    /*======= 控件声明区 =======*/
    private PhotoViewPager viewPager;
    private CircleIndicator indicator;
    /*========================*/

    private List<String> imageUrls;

    private int position;
    public static void launcher(Context context,ArrayList<String> imageUrls,int position){
        Intent intent = new Intent();
        intent.setClass(context,ImageBrowserActivity.class);
        intent.putStringArrayListExtra("imageUrls", imageUrls);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        setNeedFullScreen(true);
        viewPager = (PhotoViewPager) findViewById(R.id.pager_image_browser);
        indicator = (CircleIndicator) findViewById(R.id.indicator_image_browser);
        viewPager.setImagUrls(imageUrls);
        indicator.setViewPager(viewPager);
        viewPager.setCurrentItem(position);
    }

    @Override
    protected void initData() {
        imageUrls = getIntent().getStringArrayListExtra("imageUrls");
        position = getIntent().getIntExtra("position",0);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_image_browser;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }
}
