package com.xianzhifengshui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.xianzhifengshui.ui.becomemaster.InfomationFragment;
import com.xianzhifengshui.utils.ConstUtils;
import com.xianzhifengshui.utils.FileUtils;
import com.xianzhifengshui.utils.KLog;
import com.xianzhifengshui.utils.SDCardUtils;
import com.xianzhifengshui.utils.ToastUtils;
import com.xianzhifengshui.widget.dialog.RecordDialog;

import java.io.File;

/**
 * 作者: chengx
 * 日期: 2016/11/30.
 * 描述:
 */
public class RecordVoiceButton extends Button {

    private final String TAG = getClass().getSimpleName();

    private float touchDownY;
    private float touchUpY;
    private float touchMoveY;
    private long touchDownTime;
    private long touchUpTime;
    private RecordDialog dialog;
    private OnRecordListener onRecordListener;

    public interface OnRecordListener{
        void onRecordStart();
        void onRecordComplete();
        void onRecordCancel();
    }

    public RecordVoiceButton(Context context) {
        super(context);
        init();
    }

    public RecordVoiceButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecordVoiceButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        dialog = new RecordDialog(getContext());
    }

    public void setOnRecordListener(OnRecordListener onRecordListener) {
        this.onRecordListener = onRecordListener;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.setPressed(true);
                setText("松开 结束");
                if (onRecordListener!=null){
                    onRecordListener.onRecordStart();
                }
                touchDownY = event.getY();
                touchDownTime = System.currentTimeMillis();
                if (SDCardUtils.isSDCardEnable()){
                    dialog.showRecordingDialog();
                }else {
                    ToastUtils.showToast(getContext(),"请插入SD卡");
                    setText("按住 说话");
                    setPressed(false);
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                this.setPressed(false);
                setText("按住 说话");
                touchUpY =  event.getY();
                touchUpTime = System.currentTimeMillis();
                if ((touchUpTime-touchDownTime)<ConstUtils.SEC){
                    dialog.dismiss();
                    //取消录制
                    if (onRecordListener != null) {
                        KLog.d(TAG,"取消录制");
                        onRecordListener.onRecordCancel();
                    }

                }else if (Math.abs(touchDownY - touchUpY)>300){
                    dialog.dismiss();
                    //取消录制
                    if (onRecordListener != null) {
                        KLog.d(TAG,"取消录制");
                        onRecordListener.onRecordCancel();
                    }
                }else if ((touchUpTime-touchDownTime)<ConstUtils.MIN){
                    dialog.dismiss();
                    //完成录制
                    if (onRecordListener != null) {
                        KLog.d(TAG,"完成录制");
                        onRecordListener.onRecordComplete();
                    }

                }
                break;
            case MotionEvent.ACTION_MOVE:
                touchMoveY = event.getY();
                if (Math.abs(touchDownY-touchMoveY)>300){
                    setText("松开手指，取消发送");
                    dialog.cancel();
                    dialog.setIsCanceling(true);
                }else {
                    setText("松开 结束");
                    dialog.setIsCanceling(false);
                    dialog.showRecordingDialog();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                setText("按住 说话");
                //取消录制
                if (onRecordListener != null) {
                    KLog.d(TAG,"取消录制");
                    onRecordListener.onRecordCancel();
                }
                break;
        }
        return true;
    }
}
