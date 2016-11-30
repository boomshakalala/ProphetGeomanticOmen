package com.xianzhifengshui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xianzhifengshui.R;

/**
 * 作者: chengx
 * 日期: 2016/11/30.
 * 描述:
 */
public class RecordDialog {
    private Context context;
    private Dialog dialog;
    private ImageView voiceIv;
    private TextView labelTv;
    private int[] res;
    private boolean isCanceling;

    public RecordDialog(Context context) {
        this.context = context;
    }

    public void setIsCanceling(boolean isCanceling) {
        this.isCanceling = isCanceling;
    }

    /**
     * 显示正在录音dialog
     */
    public void showRecordingDialog(){
        if (dialog == null) {
            res = new int[]{R.drawable.jmui_mic_1,R.drawable.jmui_mic_2,R.drawable.jmui_mic_3,R.drawable.jmui_mic_4,R.drawable.jmui_mic_5};
            dialog = new Dialog(context, R.style.AudioDilogStyle);
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.widget_dialog_audio,null);
            dialog.setContentView(dialogView);
            voiceIv = (ImageView) dialog.findViewById(R.id.jmui_volume_hint_iv);
            labelTv = (TextView) dialog.findViewById(R.id.jmui_record_voice_tv);
        }
        dialog.show();
        recording();
    }


    public void recording(){
        if (dialog != null && dialog.isShowing()) {
            voiceIv.setImageResource(R.drawable.jmui_mic_1);
            labelTv.setText("手指上滑，取消发送");
        }
    }

    public void cancel(){
        voiceIv.setImageResource(R.drawable.jmui_cancel_record);
        labelTv.setText("松开手指，取消发送");
    }

    public void dismiss(){
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
            dialog = null;
        }
    }

    public void updateVoiceLevel(int level){
        if (dialog != null && dialog.isShowing()&&!isCanceling) {
            voiceIv.setImageResource(res[level]);
        }
    }
}
