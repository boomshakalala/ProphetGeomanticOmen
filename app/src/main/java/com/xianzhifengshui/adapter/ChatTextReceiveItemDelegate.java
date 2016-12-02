package com.xianzhifengshui.adapter;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;

import com.xianzhifengshui.R;
import com.xianzhifengshui.common.ItemViewDelegate;
import com.xianzhifengshui.common.RecyclerViewHolder;
import com.xianzhifengshui.utils.KLog;

import org.w3c.dom.ProcessingInstruction;

import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * 作者: chengx
 * 日期: 2016/12/2.
 * 描述:
 */
public class ChatTextReceiveItemDelegate implements ItemViewDelegate<Message>, View.OnClickListener {
    private final String TAG = getClass().getSimpleName();


    @Override
    public int getItemLayoutId() {
        return R.layout.item_chat_text_receive;
    }

    @Override
    public boolean isForViewType(Message message, int position) {
        return message.getContentType() == ContentType.text && message.getDirect() == MessageDirect.receive;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, Message message, int position) {
        holder.setText(R.id.jmui_msg_content,((TextContent)message.getContent()).getText());
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
