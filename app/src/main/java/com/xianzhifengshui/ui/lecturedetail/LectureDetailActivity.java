package com.xianzhifengshui.ui.lecturedetail;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.api.model.Lecture;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.widget.auto.AutoTabLayout;

/**
 * 作者: chengx
 * 日期: 2016/11/3.
 * 描述: 讲座详情页
 */
public class LectureDetailActivity extends BaseActivity implements LectureDetailContract.View{
    /*======= 控件声明区 =======*/
    private RelativeLayout headLayout;
    private TextView nameTv;
    private TextView descTv;
    private TextView locationTv;
    private TextView lavelTv;
    private TextView pointOfPraiseTv;
    private TextView collectionTv;
    private TextView singleVolumeTv;
    /*=========================*/


    private String lectureCode;
    private String userCode;
    private LectureDetailContract.Presenter presenter;


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

    }

    @Override
    public void showFailure() {

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
}
