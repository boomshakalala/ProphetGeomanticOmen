package com.xianzhifengshui.ui.lecturedetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.utils.ImageUtils;
import com.xianzhifengshui.widget.auto.AutoTabLayout;

/**
 * 作者: chengx
 * 日期: 2016/11/3.
 * 描述: 讲座详情页
 */
public class LectureDetailActivity extends BaseActivity implements LectureDetailContract.View, View.OnClickListener {
    /*======= 控件声明区 =======*/
    private RelativeLayout headLayout;
    private TextView masterNameTv;
    private TextView masterTitleTv;
    private TextView masterDescTv;
    private TextView masterAddressTv;
    private TextView masterLevelTv;
    private TextView singleVolumeTv;
    private TextView collectTv;
    private TextView startTimeTv;
    private TextView lectureDurationTv;
    private TextView lectureDescTv;
    private TextView lectureAddressTv;
    private TextView lectureTitleTv;
    private TextView showAllBtn;
    private TextView colloctBtn;
    private TextView signUpBtn;
    private RatingBar levelRating;
    /*=========================*/
    private String lectureCode;
    private String userCode;
    private LectureDetailContract.Presenter presenter;
    private boolean isCollected;

    public static void launcher(Context context,String lectureCode){
        Intent intent = new Intent();
        intent.putExtra("lectureCode",lectureCode);
        intent.setClass(context,LectureDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setNavigationIcon(R.drawable.commen_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(getString(R.string.text_lecture_detail));
    }

    @Override
    protected void initViews() {
        headLayout = (RelativeLayout) findViewById(R.id.layout_lecture_detail_head);
        masterNameTv = (TextView) findViewById(R.id.text_lecture_detail_name);
        masterTitleTv = (TextView) findViewById(R.id.text_lecture_detail_master_title);
        masterDescTv = (TextView) findViewById(R.id.text_lecture_detail_master_desc);
        masterAddressTv = (TextView) findViewById(R.id.text_lecture_detail_master_address);
        masterLevelTv = (TextView) findViewById(R.id.text_lecture_detail_rating_info);
        singleVolumeTv = (TextView) findViewById(R.id.text_lecture_detail_single_volume);
        collectTv = (TextView) findViewById(R.id.text_lecture_detail_collect);
        startTimeTv = (TextView) findViewById(R.id.text_lecture_detail_date);
        lectureDurationTv = (TextView) findViewById(R.id.text_lecture_detail_duration);
        lectureDescTv = (TextView) findViewById(R.id.text_lecture_detail_desc);
        lectureAddressTv = (TextView) findViewById(R.id.text_lecture_detail_address);
        lectureTitleTv = (TextView) findViewById(R.id.text_lecture_detail_title);
        showAllBtn = (TextView) findViewById(R.id.btn_lecture_detail_show_all);
        colloctBtn = (TextView) findViewById(R.id.btn_lecture_detail_collect);
        signUpBtn = (TextView) findViewById(R.id.btn_lecture_detail_sign_up);
        levelRating = (RatingBar) findViewById(R.id.rating_lecture_detail);
        colloctBtn.setOnClickListener(this);
        emptyLayout.setErrorButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.requestData(lectureCode,userCode);
            }
        });
        emptyLayout.setShowErrorButton(true);
        presenter.requestData(lectureCode,userCode);
    }

    @Override
    protected void initData() {
        presenter = new LectureDetailPresenter(this);
        this.lectureCode = getIntent().getStringExtra("lectureCode");
        this.userCode = getUserCode();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_lecture_detail;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void loadData(Lecture lecture) {
        Glide.with(this).load(lecture.getPic()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                headLayout.setBackgroundDrawable(ImageUtils.bitmap2Drawable(getResources(), resource));
            }
        });
        masterNameTv.setText(lecture.getMasterName());
        masterTitleTv.setText(lecture.getMasterTitle());
        masterAddressTv.setText("北京");
        masterLevelTv.setText("高级");
        levelRating.setRating(5);
        singleVolumeTv.setText("88");
        collectTv.setText(lecture.getCollection());
        lectureTitleTv.setText(lecture.getTitle());
        startTimeTv.setText(lecture.getStartTime());
        lectureDurationTv.setText(lecture.getDuration());
        lectureAddressTv.setText(lecture.getAddress());
        lectureDescTv.setText(lecture.getDesc());
        masterDescTv.setText(lecture.getMasterDesc());
    }

    @Override
    public void showFailure() {

    }

    @Override
    public void showCollect() {
        collectTv.setText("已经收藏");
    }

    @Override
    public void showUnCollect() {
        collectTv.setText("收藏讲座");
    }

    @Override
    public void setPresenter(LectureDetailContract.Presenter presenter) {
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_lecture_detail_collect:
                if (isCollected)
                    presenter.unCollectMaster(lectureCode);
                else
                    presenter.collectMaster(lectureCode);
                break;
            default:
                break;
        }
    }
}
