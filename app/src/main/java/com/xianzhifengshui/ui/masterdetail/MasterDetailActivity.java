package com.xianzhifengshui.ui.masterdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xianzhifengshui.R;
import com.xianzhifengshui.adapter.TabPagerAdapter;
import com.xianzhifengshui.api.model.Evaluate;
import com.xianzhifengshui.api.model.Master;
import com.xianzhifengshui.api.model.ServiceType;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.MasterDescFragment;
import com.xianzhifengshui.ui.article.ArticleFragment;
import com.xianzhifengshui.ui.evaluate.EvaluateFragment;
import com.xianzhifengshui.ui.index.discover.lecture.LectureListFragment;
import com.xianzhifengshui.ui.servicetype.ServiceTypeFragment;
import com.xianzhifengshui.utils.ImageUtils;
import com.xianzhifengshui.widget.auto.AutoTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/10/31.
 * 描述: 大师详情页面
 */
public class MasterDetailActivity extends BaseActivity implements MasterDetailContract.View{
    /*======= 控件声明区 =======*/
    private RelativeLayout headLayout;
    private TextView nameTv;
    private TextView descTv;
    private TextView locationTv;
    private TextView lavelTv;
    private TextView pointOfPraiseTv;
    private TextView collectionTv;
    private TextView singleVolumeTv;
    private ViewPager viewPager;
    private AutoTabLayout tabLayout;
    /*=========================*/
    private MasterDetailContract.Presenter presenter;
    private List<BaseFragment> fragments;
    private TabPagerAdapter adapter;
    private String[] tabs;
    private ServiceTypeFragment serviceTypeFragment;
    private MasterDescFragment masterDescFragment;
    private EvaluateFragment evaluateFragment;
    private ArticleFragment articleFragment;


    public static void launcher(Context context){
        Intent intent = new Intent();
        intent.setClass(context, MasterDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(getString(R.string.text_master_detail));
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setRightBtnImage(R.drawable.commen_share);
        toolbar.setOnRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:分享
            }
        });
    }

    @Override
    protected void initViews() {
        headLayout = (RelativeLayout) findViewById(R.id.layout_master_detail_head);
        nameTv = (TextView) findViewById(R.id.text_master_detail_name);
        descTv = (TextView) findViewById(R.id.text_master_detail_desc);
        locationTv = (TextView) findViewById(R.id.text_master_detail_location);
        lavelTv = (TextView) findViewById(R.id.text_master_detail_lavel);
        pointOfPraiseTv = (TextView) findViewById(R.id.text_master_detail_point_of_praise);
        collectionTv = (TextView) findViewById(R.id.text_master_detail_collection);
        singleVolumeTv = (TextView) findViewById(R.id.text_master_detail_single_volume);
        viewPager = (ViewPager) findViewById(R.id.pager_master_detail);
        viewPager.setOffscreenPageLimit(4);
        tabLayout = (AutoTabLayout) findViewById(R.id.tab_master_detail);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        presenter.requestData();
    }

    @Override
    protected void initData() {
        presenter = new MasterDetailPresenter(this);
        tabs = getResources().getStringArray(R.array.tab_master_detail);
        fragments = new ArrayList<>();
        serviceTypeFragment = new ServiceTypeFragment();
        masterDescFragment = new MasterDescFragment();
        evaluateFragment = new EvaluateFragment();
        articleFragment = new ArticleFragment();
        fragments.add(serviceTypeFragment);
        fragments.add(masterDescFragment);
        fragments.add(evaluateFragment);
        fragments.add(articleFragment);
        adapter = new TabPagerAdapter(getSupportFragmentManager(),tabs,fragments);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_master_detail;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void setPresenter(MasterDetailContract.Presenter presenter) {
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
    public void showInfo(Master master) {
        nameTv.setText(master.getName());
        descTv.setText(master.getTitle());
        locationTv.setText(master.getAddress());
        lavelTv.setText("v"+master.getLevel());
        pointOfPraiseTv.setText(master.getPointOfPraise()+"");
        singleVolumeTv.setText(master.getSingleVolume()+getString(R.string.text_single));
        collectionTv.setText(master.getCollection()+"");
        Glide.with(this).load(master.getPic()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                headLayout.setBackgroundDrawable(ImageUtils.bitmap2Drawable(getResources(), resource));
            }
        });
    }

    @Override
    public void showService(List<ServiceType> serviceTypes) {
        serviceTypeFragment.refreshData(serviceTypes);
    }

    @Override
    public void showAboutMaster(String desc) {
        masterDescFragment.setContent(desc);
    }

    @Override
    public void showArticle() {
//        articleFragment.refreshData();
    }

    @Override
    public void showEvaluate(List<Evaluate> evaluates) {
        evaluateFragment.refreshData(evaluates);
    }

    @Override
    public void closeMoreService() {

    }

    @Override
    public void closeMoreEvaluate() {
        evaluateFragment.closeLoadMore();
    }

    @Override
    public void closeMoreArticle() {

    }

}
