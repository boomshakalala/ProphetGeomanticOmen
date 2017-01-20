package com.xianzhifengshui.ui.feed;

import android.content.Context;
import android.view.View;

import com.xianzhifengshui.api.net.ActionCallbackListener;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.utils.RegexUtils;
import com.xianzhifengshui.utils.StringUtils;

/**
 * 作者: chengx
 * 日期: 2016/10/19.
 * 描述: 用户反馈控制器
 */
public class FeedPresenter extends BasePresenter implements FeedContract.Presenter {

    FeedContract.View view;

    public FeedPresenter(FeedContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void feedBack(String email, String content) {
        if (!RegexUtils.isEmail(email)){
            view.showTip("请输入正确的邮箱");
            return;
        }
        if (StringUtils.isEmpty(content)){
            view.showTip("请输入反馈内容");
            return;
        }
        if (isLogin()){
            view.showWaiting();
            api.feedBack(getUserCode(), email, content, new ActionCallbackListener<Void>() {
                @Override
                public void onProgress(long bytesWritten, long totalSize) {

                }

                @Override
                public void onSuccess(Void data) {
                    view.showTip("反馈成功");
                    view.closeWait();
                }

                @Override
                public void onFailure(int errorEvent, String message) {
                    view.showTip(message);
                    view.closeWait();
                }
            });
        }else {
            view.toLoginActivity((Context) view);
        }

    }
}
