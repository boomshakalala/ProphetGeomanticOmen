package com.xianzhifengshui.ui.index.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.AppConfig;
import com.xianzhifengshui.base.BaseFragment;
import com.xianzhifengshui.ui.HelpAndFeedActivity;
import com.xianzhifengshui.ui.edituserinfo.EditUserInfoActivity;
import com.xianzhifengshui.ui.login.LoginActivity;
import com.xianzhifengshui.ui.mylecture.MyLectureActivity;
import com.xianzhifengshui.ui.mymaster.MyMasterActivity;
import com.xianzhifengshui.ui.setting.SettingActivity;
import com.xianzhifengshui.widget.CircleImageView;

import java.util.IllegalFormatCodePointException;
import java.util.concurrent.BrokenBarrierException;

/**
 * 作者: chengx
 * 日期: 2016/10/10.
 * 描述: 个人中心页
 */
public class MineFragment extends BaseFragment implements View.OnClickListener,MineContract.View {


    public static final int OPT_MY_MASTER = 1;//跳转到我的大师页
    public static final int OPT_MY_LECTURE = 2;//跳转到我的讲座页
    public static final int OPT_MY_TOPIC = 3;//跳转到我的话题页
    public static final int OPT_BECOME_MASTER = 4;//跳转到成为大师页
    public static final int OPT_EDIT_USER_INFO = 5;//跳转到修改个人信息页
    public static final int OPT_SETTING = 6;//跳转到设置页
    /*======= 控件声明区 =======*/
    private RelativeLayout loginBtn;
    private RelativeLayout myMastBtn;
    private RelativeLayout myLectureBtn;
    private RelativeLayout myTopicBtn;
    private RelativeLayout becomeMasterBtn;
    private RelativeLayout helpBtn;
    private RelativeLayout settingBtn;
    private CircleImageView avatarIv;
    private TextView nickNameTv;
    private TextView userNameTv;
    /*=========================*/

    private MineContract.Presenter presenter;

    public void onEventMainThread(boolean isLogin){
        presenter.checkIsLoginUpdateUI(sp);
    }

    @Override
    protected void initViews() {
        loginBtn = (RelativeLayout) rootView.findViewById(R.id.btn_mine_login);
        myMastBtn = (RelativeLayout) rootView.findViewById(R.id.btn_mine_my_master);
        myLectureBtn = (RelativeLayout) rootView.findViewById(R.id.btn_mine_my_lecture);
        myTopicBtn = (RelativeLayout) rootView.findViewById(R.id.btn_mine_my_topic);
        becomeMasterBtn = (RelativeLayout) rootView.findViewById(R.id.btn_mine_become_master);
        helpBtn = (RelativeLayout) rootView.findViewById(R.id.btn_mine_help);
        settingBtn = (RelativeLayout) rootView.findViewById(R.id.btn_mine_setting);
        avatarIv = (CircleImageView) rootView.findViewById(R.id.image_mine_avatar);
        userNameTv = (TextView) rootView.findViewById(R.id.text_mine_user_name);
        nickNameTv = (TextView) rootView.findViewById(R.id.text_mine_nick_name);
        loginBtn.setOnClickListener(this);
        myMastBtn.setOnClickListener(this);
        myLectureBtn.setOnClickListener(this);
        myTopicBtn.setOnClickListener(this);
        becomeMasterBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);
        settingBtn.setOnClickListener(this);
        presenter.checkIsLoginUpdateUI(sp);
    }

    @Override
    protected void initData() {
        presenter = new MinePresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected boolean isNeedToolbar() {
        return true;
    }

    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.btn_mine_help){
            //跳转到帮助与反馈页面
           HelpAndFeedActivity.launcher(activity);
        }else {
            int opt = 0;
            switch (v.getId()){
                case R.id.btn_mine_my_master:
                    //跳转到我的大师页面
                    opt = OPT_MY_MASTER;
                    break;
                case R.id.btn_mine_my_lecture:
                    //跳转到我的讲座页面
                    opt = OPT_MY_LECTURE;
                    break;
                case R.id.btn_mine_my_topic:
                    //跳转到我的话题页面
                    opt = OPT_MY_TOPIC;
                    break;
                case R.id.btn_mine_become_master:
                    //跳转到成为大师页面
                    opt = OPT_BECOME_MASTER;
                    break;
                case R.id.btn_mine_login:
                    //跳转到编辑个人信息页面
                    opt = OPT_EDIT_USER_INFO;
                    break;
                case R.id.btn_mine_setting:
                    //跳转到设置页面
                    opt = OPT_SETTING;
                    break;
                default:
                    opt = 0;
                    break;
            }
            presenter.checkIsLoginJump(sp,opt);
        }
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.text_me);
    }

    @Override
    public void toLoginActivity(int opt) {
        LoginActivity.launcher(activity,opt);
    }

    @Override
    public void showLoginInfo() {
        nickNameTv.setText("小米粒");
        userNameTv.setText("18631565231");
        avatarIv.setImageResource(R.drawable.pic);
    }

    @Override
    public void showDefaultInfo() {
        nickNameTv.setText("点击登录");
        userNameTv.setText("登录后更精彩哦~");
        avatarIv.setImageResource(R.drawable.avatar_not_login_icon);
    }

    @Override
    public void jumpToActivity(int opt) {
        switch (opt){
            case OPT_MY_MASTER:
                MyMasterActivity.launcher(activity);
                break;
            case OPT_MY_LECTURE:
                MyLectureActivity.launcher(activity);
                break;
            case OPT_MY_TOPIC:
                log("跳转到我的话题页面");
                break;
            case OPT_BECOME_MASTER:
                log("跳转到成为大师页面");
                break;
            case OPT_EDIT_USER_INFO:
                EditUserInfoActivity.launcher(activity);
                break;
            case OPT_SETTING:
                SettingActivity.launcher(activity);
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppConfig.RESULT_LOGIN){
            presenter.checkIsLogin(sp,true,true,requestCode);
        }
    }
}
