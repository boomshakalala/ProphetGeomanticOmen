package com.xianzhifengshui.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.utils.ScreenUtils;

/**
 * 作者: chengx
 * 日期: 2016/9/28.
 * 描述: 仿IOS AlertDialog
 */
public class NormalAlertDialog implements View.OnClickListener {
    private static Context context;
    private TextView titleTv;
    private TextView contentTv;
    private Button leftBtn;
    private Button rightBtn;
    private Button singleBtn;
    private View splitLine;
    private Dialog dialog;
    private View dialogView;
    private Builder builder;

    public NormalAlertDialog(Builder builder){
        this.builder = builder;
        dialog = new Dialog(context,R.style.NormalDialogStyle);
        dialogView = View.inflate(context,R.layout.widget_dialog_normal,null);
        titleTv = (TextView) dialogView.findViewById(R.id.dialog_normal_title);
        contentTv = (TextView) dialogView.findViewById(R.id.dialog_normal_content);
        leftBtn = (Button) dialogView.findViewById(R.id.dialog_normal_leftbtn);
        rightBtn = (Button) dialogView.findViewById(R.id.dialog_normal_rightbtn);
        singleBtn = (Button) dialogView.findViewById(R.id.dialog_normal_midbtn);
        splitLine = dialogView.findViewById(R.id.dialog_normal_line);
        dialogView.setMinimumHeight((int) (ScreenUtils.getScreenHeight(context) * builder.getHeight()));
        dialogView.setMinimumWidth((int) (ScreenUtils.getScreenWidth(context) * builder.getWidth()));
        dialog.setContentView(dialogView);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (ScreenUtils.getScreenWidth(context) * builder.getWidth());
        layoutParams.height = (int) (ScreenUtils.getScreenHeight(context) * builder.getHeight());
        layoutParams.gravity = Gravity.CENTER;
        initDialog(builder);
    }

    private void initDialog(Builder builder) {
        dialog.setCanceledOnTouchOutside(builder.isTouchOutSide());
        if (builder.isTitleVisible()){
            titleTv.setVisibility(View.VISIBLE);
        }else {
            titleTv.setVisibility(View.GONE);
        }
        if (builder.isSingleMode()){
            singleBtn.setVisibility(View.VISIBLE);
            leftBtn.setVisibility(View.GONE);
            rightBtn.setVisibility(View.GONE);
            splitLine.setVisibility(View.GONE);
        }else {
            singleBtn.setVisibility(View.GONE);
            leftBtn.setVisibility(View.VISIBLE);
            rightBtn.setVisibility(View.VISIBLE);
            splitLine.setVisibility(View.VISIBLE);
        }
        titleTv.setText(builder.getTitleText());
        titleTv.setTextColor(builder.getTitleTextColor());
        titleTv.setTextSize(builder.getTitleTextSize());
        contentTv.setText(builder.getContentText());
        contentTv.setTextColor(builder.getContentTextColor());
        contentTv.setTextSize(builder.getContentTextSize());
        leftBtn.setText(builder.getLeftButtonText());
        leftBtn.setTextColor(builder.getLeftButtonTextColor());
        leftBtn.setTextSize(builder.getButtonTextSize());
        rightBtn.setText(builder.getRightButtonText());
        rightBtn.setTextColor(builder.getRightButtonTextColor());
        rightBtn.setTextSize(builder.getButtonTextSize());
        singleBtn.setText(builder.getSingleButtonText());
        singleBtn.setTextColor(builder.getSingleButtonTextColor());
        singleBtn.setTextSize(builder.getButtonTextSize());
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        singleBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.dialog_normal_leftbtn && builder.getDialogClickListener()!=null){
            builder.getDialogClickListener().clickLeftButton(v);
        }else if (viewId == R.id.dialog_normal_rightbtn && builder.getDialogClickListener()!=null){
            builder.getDialogClickListener().clickRightButton(v);
        }else if (viewId == R.id.dialog_normal_midbtn && builder.getSingleClickListener()!=null){
            builder.getSingleClickListener().onClick(v);
        }
    }

    public void show(){
        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }

    public static class Builder{
        private final String DEFAULT_TITLE = "温馨提示";
        private final String DEFAULT_CONTENT = "";
        private final String DEFAULT_LEFT_BUTTON_TEXT = "取消";
        private final String DEFAULT_RIGHT_BUTTON_TEXT = "确定";
        private final int DEFAULT_TEXT_COLOR = R.color.black_light;
        private final float DEFAULT_HEIGHT = 0.23f;
        private final float DEFAULT_WIDTH = 0.65f;
        private final int DEFAULT_TITLE_TEXT_SIZE = 16;
        private final int DEFAULT_TEXT_SIZE = 14;

        private String titleText;
        private int titleTextColor;
        private int titleTextSize;
        private String contentText;
        private int contentTextColor;
        private int contentTextSize;
        private boolean isSingleMode;
        private String singleButtonText;
        private int singleButtonTextColor;
        private String leftButtonText;
        private int leftButtonTextColor;
        private String rightButtonText;
        private int rightButtonTextColor;
        private int buttonTextSize;
        private DialogOnClickListener dialogClickListener;
        private View.OnClickListener singleClickListener;
        private boolean isTitleVisible;
        private boolean isTouchOutSide;
        private float width;
        private float height;

        public Builder(Context context){
            NormalAlertDialog.context = context;
            titleText = DEFAULT_TITLE;
            titleTextColor = ContextCompat.getColor(NormalAlertDialog.context,DEFAULT_TEXT_COLOR);
            contentText = DEFAULT_CONTENT;
            contentTextColor = ContextCompat.getColor(NormalAlertDialog.context,DEFAULT_TEXT_COLOR);
            isSingleMode = false;
            singleButtonText = DEFAULT_RIGHT_BUTTON_TEXT;
            singleButtonTextColor = ContextCompat.getColor(NormalAlertDialog.context,DEFAULT_TEXT_COLOR);
            leftButtonText = DEFAULT_LEFT_BUTTON_TEXT;
            leftButtonTextColor = DEFAULT_TEXT_COLOR;
            rightButtonText = DEFAULT_RIGHT_BUTTON_TEXT;
            rightButtonTextColor = ContextCompat.getColor(NormalAlertDialog.context,DEFAULT_TEXT_COLOR);
            dialogClickListener = null;
            singleClickListener = null;
            isTitleVisible = false;
            isTouchOutSide = true;
            height = DEFAULT_HEIGHT;
            width = DEFAULT_WIDTH;
            titleTextSize = DEFAULT_TITLE_TEXT_SIZE;
            contentTextSize = DEFAULT_TEXT_SIZE;
            buttonTextSize = DEFAULT_TEXT_SIZE;
        }


        public String getTitleText() {
            return titleText;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public int getTitleTextColor() {
            return titleTextColor;
        }

        public Builder setTitleTextColor(int titleTextColor) {
            this.titleTextColor = ContextCompat.getColor(context,titleTextColor);
            return this;
        }

        public int getTitleTextSize() {
            return titleTextSize;
        }

        public Builder setTitleTextSize(int titleTextSize) {
            this.titleTextSize = titleTextSize;
            return this;
        }

        public String getContentText() {
            return contentText;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public int getContentTextColor() {
            return contentTextColor;
        }

        public Builder setContentTextColor(int contentTextColor) {
            this.contentTextColor = ContextCompat.getColor(context,contentTextColor);
            return this;
        }

        public int getContentTextSize() {
            return contentTextSize;
        }

        public Builder setContentTextSize(int contentTextSize) {
            this.contentTextSize = contentTextSize;
            return this;
        }

        public boolean isSingleMode() {
            return isSingleMode;
        }

        public Builder setIsSingleMode(boolean isSingleMode) {
            this.isSingleMode = isSingleMode;
            return this;
        }

        public String getSingleButtonText() {
            return singleButtonText;
        }

        public Builder setSingleButtonText(String singleButtonText) {
            this.singleButtonText = singleButtonText;
            return this;
        }

        public int getSingleButtonTextColor() {
            return singleButtonTextColor;
        }

        public Builder setSingleButtonTextColor(int singleButtonTextColor) {
            this.singleButtonTextColor = ContextCompat.getColor(context,singleButtonTextColor);
            return this;
        }

        public String getLeftButtonText() {
            return leftButtonText;
        }

        public Builder setLeftButtonText(String leftButtonText) {
            this.leftButtonText = leftButtonText;
            return this;
        }

        public int getLeftButtonTextColor() {
            return leftButtonTextColor;
        }

        public Builder setLeftButtonTextColor(int leftButtonTextColor) {
            this.leftButtonTextColor = ContextCompat.getColor(context,leftButtonTextColor);
            return this;
        }

        public String getRightButtonText() {
            return rightButtonText;
        }

        public Builder setRightButtonText(String rightButtonText) {
            this.rightButtonText = rightButtonText;
            return this;
        }

        public int getRightButtonTextColor() {
            return rightButtonTextColor;
        }

        public Builder setRightButtonTextColor(int rightButtonTextColor) {
            this.rightButtonTextColor = ContextCompat.getColor(context,rightButtonTextColor);
            return this;
        }

        public int getButtonTextSize() {
            return buttonTextSize;
        }

        public Builder setButtonTextSize(int buttonTextSize) {
            this.buttonTextSize = buttonTextSize;
            return this;
        }

        public DialogOnClickListener getDialogClickListener() {
            return dialogClickListener;
        }

        public Builder setDialogClickListener(DialogOnClickListener dialogClickListener) {
            this.dialogClickListener = dialogClickListener;
            return this;
        }

        public View.OnClickListener getSingleClickListener() {
            return singleClickListener;
        }

        public Builder setSingleClickListener(View.OnClickListener singleClickListener) {
            this.singleClickListener = singleClickListener;
            return this;
        }

        public boolean isTitleVisible() {
            return isTitleVisible;
        }

        public Builder setIsTitleVisible(boolean isTitleVisible) {
            this.isTitleVisible = isTitleVisible;
            return this;
        }

        public boolean isTouchOutSide() {
            return isTouchOutSide;
        }

        public Builder setIsTouchOutSide(boolean isTouchOutSide) {
            this.isTouchOutSide = isTouchOutSide;
            return this;
        }

        public float getWidth() {
            return width;
        }

        public Builder setWidth(float width) {
            this.width = width;
            return this;
        }

        public float getHeight() {
            return height;
        }

        public Builder setHeight(float height) {
            this.height = height;
            return this;
        }

        public NormalAlertDialog build(){
            return new NormalAlertDialog(this);
        }
    }
}
