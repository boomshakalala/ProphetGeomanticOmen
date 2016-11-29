package com.xianzhifengshui.ui.chat;

import android.content.Context;
import android.content.Intent;

import com.xianzhifengshui.base.BaseActivity;
import com.xianzhifengshui.utils.KLog;

import java.util.List;

import cn.jpush.im.android.api.model.Message;

/**
 * 作者: chengx
 * 日期: 2016/11/29.
 * 描述:
 */
public class ChatActivity extends BaseActivity implements ChatContract.View{

    ChatContract.Presenter presenter;


    public static void launcher(Context context){
        Intent intent = new Intent(context,ChatActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        presenter = new ChatPresenter(this,"admin");
        presenter.init();
    }

    @Override
    protected int getContentLayoutId() {
        return -1;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    @Override
    public void loadHistory(List<Message> data) {
        log(data);
    }

    @Override
    public void loadMessage(Message data) {
        log(data);
    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showTip(String text) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
