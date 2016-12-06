package com.xianzhifengshui.adapter;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseApplication;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.ScreenUtils;
import com.xianzhifengshui.utils.SizeUtils;
import com.xianzhifengshui.widget.auto.AutoScrollView;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2016/12/3.
 */

public class ChatVoiceSendItemDelegate implements ItemViewDelegate<Message> {
    @Override
    public int getItemLayoutId() {
        return R.layout.item_chat_voice_send;
    }

    @Override
    public boolean isForViewType(Message message, int position) {
        return message.getContentType() == ContentType.voice && message.getDirect() == MessageDirect.send;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, Message message, int position) {
        VoiceContent voiceContent = (VoiceContent) message.getContent();
        int length = voiceContent.getDuration();
        int width = ScreenUtils.getScreenWidth(BaseApplication.getAppContext())/ 120 * length
                + SizeUtils.dp2px(BaseApplication.getAppContext(),60);
        ImageView imageView = holder.getView(R.id.jmui_msg_content);
        if (imageView != null) {
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            if (params != null) {
                params.width = width;
            }
        }
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
        holder.setText(R.id.jmui_msg_length,length+"s");
        switch (message.getStatus()){
            case send_success:
                holder.setVisibility(R.id.jmui_sending_iv, View.GONE);
                holder.setVisibility(R.id.jmui_fail_resend_btn,View.GONE);
                break;
            case send_going:
                holder.setVisibility(R.id.jmui_sending_iv,View.VISIBLE);
                holder.setVisibility(R.id.jmui_fail_resend_btn,View.GONE);
                break;
            case send_fail:
                holder.setVisibility(R.id.jmui_sending_iv,View.GONE);
                holder.setVisibility(R.id.jmui_fail_resend_btn,View.VISIBLE);
                break;
        }
    }
}
