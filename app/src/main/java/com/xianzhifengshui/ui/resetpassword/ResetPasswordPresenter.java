package com.xianzhifengshui.ui.resetpassword;

import android.os.CountDownTimer;

import com.xianzhifengshui.api.model.Verify;
import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.ui.register.RegisterPresenter;
import com.xianzhifengshui.utils.ConstUtils;
import com.xianzhifengshui.utils.RegexUtils;
import com.xianzhifengshui.utils.StringUtils;

/**
 * 作者：chengx
 * 日期：2017/1/10
 * 描述：
 */

public class ResetPasswordPresenter extends BasePresenter implements ResetPasswordContract.Presenter {
    ResetPasswordContract.View view;
    String verifyCode = "";
    String phoneNum = "";
    TimeCount timeCount;

    public ResetPasswordPresenter(ResetPasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void resetPassword(String phoneNum, String newPassword,String verifyCode) {
        if (!RegexUtils.isMobileExact(phoneNum)){
            view.showTip("手机号格式正确");
            return;
        }
        if (!RegexUtils.isVerifyCode(verifyCode)){
            view.showTip("验证码格式不正确");
            return;
        }
        if (StringUtils.isEmpty(phoneNum)){
            view.showTip("手机号不能为空");
            return;
        }else if (StringUtils.isEmpty(newPassword)){
            view.showTip("请输入新密码");
            return;
        }else if (StringUtils.isEmpty(verifyCode)){
            view.showTip("请输入验证码");
            return;
        }else if (!verifyCode.equals(this.verifyCode)){
            view.showTip("验证码错误");
            return;
        }
        api.userResetPassword(phoneNum, newPassword, new ActionCallbackListener<Void>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(Void data) {
                view.showResetSuccess();
            }

            @Override
            public void onFailure(int errorEvent, String message) {
                view.showTip(message);
            }
        });
    }

    @Override
    public void getVerifyCode(final String phoneNum) {
        if (!RegexUtils.isMobileExact(phoneNum)){
            view.showTip("请输入正确手机号");
            return;
        }
        if (timeCount == null){
            timeCount = new ResetPasswordPresenter.TimeCount(ConstUtils.MIN,ConstUtils.SEC);
        }
        timeCount.start();
        api.userSendSms(phoneNum, "2", new ActionCallbackListener<Verify>() {
            @Override
            public void onProgress(long bytesWritten, long totalSize) {

            }

            @Override
            public void onSuccess(Verify data) {
                verifyCode = data.getVcode();
                ResetPasswordPresenter.this.phoneNum = data.getMobilePhone();
            }

            @Override
            public void onFailure(int errorEvent, String message) {

            }
        });
    }
    class TimeCount extends CountDownTimer {

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
