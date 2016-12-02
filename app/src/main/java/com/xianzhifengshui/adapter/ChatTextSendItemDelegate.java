package com.xianzhifengshui.adapter;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.widget.dialog.DialogOnClickListener;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cz.msebera.android.httpclient.client.methods.HttpOptions;

/**
 * 作者: chengx
 * 日期: 2016/12/2.
 * 描述:
 */
public class ChatTextSendItemDelegate implements ItemViewDelegate<Message>, View.OnClickListener {
    @Override
    public int getItemLayoutId() {
        return R.layout.item_chat_text_send;
    }

    @Override
    public boolean isForViewType(Message message, int position) {
        return message.getContentType() == ContentType.text && message.getDirect() == MessageDirect.send;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, Message message, int position) {
        holder.setText(R.id.jmui_msg_content,((TextContent)message.getContent()).getText());
        UserInfo userInfo = message.getFromUser();
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getAvatar())) {
            userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                @Override
                public void gotResult(int i, String s, Bitmap bitmap) {
                    if (i==0){
                        holder.setImageUrlBtimap(R.id.jmui_msg_avatar,bitmap);
                    }else {
                        holder.setImageResource(R.id.jmui_msg_avatar, R.drawable.pic1);
                    }
                }
            });
        }else {
            holder.setImageResource(R.id.jmui_msg_avatar, R.drawable.pic1);
        }
        holder.setOnclickListener(R.id.jmui_fail_resend_btn,this);
        switch (message.getStatus()){
            case send_success:
                holder.setVisibility(R.id.jmui_sending_iv, View.GONE);
                holder.setVisibility(R.id.jmui_fail_resend_btn,View.GONE);
                break;
            case send_fail:
                holder.setVisibility(R.id.jmui_sending_iv,View.GONE);
                holder.setVisibility(R.id.jmui_fail_resend_btn,View.VISIBLE);
                break;
            case send_going:
                holder.setVisibility(R.id.jmui_sending_iv,View.VISIBLE);
                holder.setVisibility(R.id.jmui_fail_resend_btn,View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jmui_fail_resend_btn:

                break;
        }
    }
}
