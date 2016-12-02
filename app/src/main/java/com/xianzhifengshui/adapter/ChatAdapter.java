package com.xianzhifengshui.adapter;

import android.content.Context;

import com.xianzhifengshui.common.MultiItemCommonAdapter;

import java.util.List;

import cn.jpush.im.android.api.model.Message;

/**
 * 作者: chengx
 * 日期: 2016/12/2.
 * 描述:
 */
public class ChatAdapter extends MultiItemCommonAdapter<Message> {
    public ChatAdapter(Context context, List<Message> data) {
        super(context, data);
        addItemViewDelegate(new ChatTextSendItemDelegate());
        addItemViewDelegate(new ChatTextReceiveItemDelegate());
    }


    public void addMsgToList(Message message){
        data.add(message);
        notifyDataSetChanged();
    }
}
