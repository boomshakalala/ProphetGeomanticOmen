package com.xianzhifengshui.ui.register;

import android.os.CountDownTimer;

import com.xianzhifengshui.api.model.Verify;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.common.PagerAdapter;
import com.xianzhifengshui.utils.ConstUtils;
import com.xianzhifengshui.utils.RegexUtils;

/**
 * 作者: chengx
 * 日期: 2016/11/21.
 * 描述:
 */
public class RegisterPresenter extends BasePresenter implements RegisterContract.Presenter {

    RegisterContract.View view;

    private TimeCount timeCount;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void register(String phoneNum, String password, String nickName, String verifyCode) {
        if (!RegexUtils.isMobileExact(phoneNum)){
            view.showTip("手机号格式正确");
            return;
        }
        if (!RegexUtils.isVerifyCode(verifyCode)){
            view.showTip("验证码格式不正确");
            return;
        }
        view.showWaiting();
        api.userSaveUserInfo(phoneNum, password, nickName, new ActionCallbackListener<Void>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(Void data) {
                view.showTip("注册成功");
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.showTip(message);
            }
        });
    }

    @Override
    public void getVerifyCode(String phoneNum) {
        if (timeCount == null){
            timeCount = new TimeCount(ConstUtils.MIN,ConstUtils.SEC);
        }
        timeCount.start();
        api.userSendSms(phoneNum, "1", new ActionCallbackListener<Verify>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(Verify data) {
                view.closeWait();
                view.showTip("获取验证码成功");
                view.setVerifyCode(data.getVcode());
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.closeWait();
                view.showTip(message);
                timeCount.onFinish();
            }
        });
    }
    class TimeCount extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            view.setTimeCount(millisUntilFinished / ConstUtils.SEC + "s");
            view.setClickble(false);
        }

        @Override
        public void onFinish() {
            view.setTimeCount("获取验证码");
            view.setClickble(true);
        }
    }
}
