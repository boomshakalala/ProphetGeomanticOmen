package com.xianzhifengshui.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xianzhifengshui.R;
import com.xianzhifengshui.base.BaseApplication;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.ScreenUtils;
import com.xianzhifengshui.utils.SizeUtils;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * 作者: chengx
 * 日期: 2016/12/2.
 * 描述:
 */
public class ChatVoiceReceiveItemDelegate implements ItemViewDelegate<Message>, View.OnClickListener {
    private final String TAG = getClass().getSimpleName();


    @Override
    public int getItemLayoutId() {
        return R.layout.item_chat_voice_receive;
    }

    @Override
    public boolean isForViewType(Message message, int position) {
        return message.getContentType() == ContentType.voice && message.getDirect() == MessageDirect.receive;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, Message message, int position) {
        VoiceContent voiceContent = (VoiceContent) message.getContent();
        int length = voiceContent.getDuration();
        KLog.d(TAG, "length=" + length);
        final ImageView imageView = holder.getView(R.id.jmui_msg_content);
        final AnimationDrawable animation = (AnimationDrawable) imageView.getDrawable();
        animation.stop();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation.start();
            }
        });
        if (imageView != null) {
            int width = ScreenUtils.getScreenWidth(BaseApplication.getAppContext())/ 120 * length
                    + SizeUtils.dp2px(BaseApplication.getAppContext(),60);
            KLog.d(TAG,"width="+width);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            if (params != null) {
                params.width = width;
            }else {
                KLog.d(TAG,"param = null");
            }
        }else {
            KLog.d(TAG,"imageView = null");
        }

        holder.setText(R.id.jmui_msg_length,length+"s");
        UserInfo userInfo = message.getFromUser();
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getAvatar())) {
            KLog.d(TAG,userInfo.getAvatar());
            userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                @Override
                public void gotResult(int status, String info, Bitmap bitmap) {
                    if (status == 0)
                        holder.setImageUrlBtimap(R.id.jmui_msg_avatar,bitmap);
                    else
                        holder.setImageResource(R.id.jmui_msg_avatar,R.drawable.pic1);
                }
            });
        }else {
            holder.setImageResource(R.id.jmui_msg_avatar,R.drawable.pic1);
        }

    }

    @Override
    public void onClick(View v) {

    }
}
