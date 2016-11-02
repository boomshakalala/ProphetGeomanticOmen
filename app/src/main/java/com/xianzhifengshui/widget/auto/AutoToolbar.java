package com.xianzhifengshui.widget.auto;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xianzhifengshui.R;
import com.xianzhifengshui.utils.SizeUtils;
import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.utils.AutoLayoutHelper;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.autolayout.utils.DimenUtils;

import java.lang.reflect.Field;

/**
 * 作者: chengx
 * 日期: 2015/12/28.
 * 描述: 支持auto的Toolbar
 */
public class AutoToolbar extends Toolbar {
    private static final int NO_VALID = -1;
    private int mTextSize;
    private int mSubTextSize;
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    @SuppressLint("PrivateResource")
    public AutoToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Toolbar,
                defStyleAttr, R.style.Widget_AppCompat_Toolbar);

        int titleTextAppearance = a.getResourceId(R.styleable.Toolbar_titleTextAppearance,
                R.style.TextAppearance_Widget_AppCompat_Toolbar_Title);

        int subtitleTextAppearance = a.getResourceId(R.styleable.Toolbar_subtitleTextAppearance,
                R.style.TextAppearance_Widget_AppCompat_Toolbar_Subtitle);

        mTextSize = loadTextSizeFromTextAppearance(titleTextAppearance);
        mSubTextSize = loadTextSizeFromTextAppearance(subtitleTextAppearance);

        TypedArray menuA = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Theme,
                defStyleAttr, R.style.ThemeOverlay_AppCompat);
        int menuTextAppearance = menuA.getResourceId(R.styleable.Theme_actionBarTheme,
                R.style.ThemeOverlay_AppCompat_ActionBar);
        int menuTextSize = loadTextSizeFromTextAppearance(menuTextAppearance);

        //防止 menu 定义 textSize，而 Toolbar 无定义 textSize 时，title 的 textSize 随 menu 变化
        if (mTextSize == NO_VALID) mTextSize = menuTextSize;
        if (mSubTextSize == NO_VALID) mSubTextSize = menuTextSize;

        a.recycle();
        menuA.recycle();
    }

    public AutoToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoToolbar(Context context) {
        this(context, null);
    }
    @SuppressLint("PrivateResource")
    private int loadTextSizeFromTextAppearance(int textAppearanceResId) {
        TypedArray a = getContext().obtainStyledAttributes(textAppearanceResId,
                R.styleable.TextAppearance);
        try {
            if (!DimenUtils.isPxVal(a.peekValue(R.styleable.TextAppearance_android_textSize)))
                return NO_VALID;
            return a.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, NO_VALID);
        } finally {
            a.recycle();
        }
    }

    private void setUpTitleTextSize() {
        CharSequence title = getTitle();
        if (!TextUtils.isEmpty(title) && mTextSize != NO_VALID)
            setUpTitleTextSize("mTitleTextView", mTextSize);
        CharSequence subtitle = getSubtitle();
        if (!TextUtils.isEmpty(subtitle) && mSubTextSize != NO_VALID)
            setUpTitleTextSize("mSubtitleTextView", mSubTextSize);
    }

    private void setUpTitleTextSize(String name, int val) {
        try {
            //反射 Toolbar 的 TextView
            Field f = getClass().getSuperclass().getDeclaredField(name);
            f.setAccessible(true);
            TextView textView = (TextView) f.get(this);
            if (textView != null) {
                int autoTextSize = AutoUtils.getPercentHeightSize(val);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, autoTextSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!this.isInEditMode()) {
            setUpTitleTextSize();
            this.mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(this.getContext(), attrs);
    }



    public static class LayoutParams extends Toolbar.LayoutParams implements AutoLayoutHelper.AutoLayoutParams {
        private AutoLayoutInfo mDimenLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.mDimenLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return this.mDimenLayoutInfo;
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }



    }
    @Override
    public void setTitle(CharSequence title){
        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText(title);
    }

    @Override
    public void setTitle(int resId) {
        setTitle(getContext().getString(resId));
    }

    @Override
    public void setTitleTextColor(int color) {
        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setTextColor(color);
    }

    @Override
    public void setNavigationIcon(int resId) {
        ImageView leftBtn = (ImageView) findViewById(R.id.imgBtn_left);
        if (leftBtn.getVisibility() == GONE){
            leftBtn.setVisibility(VISIBLE);
        }
        leftBtn.setImageResource(resId);
    }

    @Override
    public void setNavigationOnClickListener(OnClickListener listener) {
        ImageView leftBtn = (ImageView) findViewById(R.id.imgBtn_left);
        leftBtn.setOnClickListener(listener);
    }

    public void setRightBtnText(String text){
        TextView rightBtn = (TextView) findViewById(R.id.btn_right);
        if (rightBtn.getVisibility() == GONE){
            rightBtn.setVisibility(VISIBLE);
        }
        rightBtn.setText(text);
    }

    public void setRightBtnText(int resId){
        String text = getContext().getString(resId);
        setRightBtnText(text);
    }

    public void setRightBtnImage(int resId){
        ImageView rightBtn = (ImageView) findViewById(R.id.imgBtn_right);
        if (rightBtn.getVisibility() == GONE){
            rightBtn.setVisibility(VISIBLE);
        }
        rightBtn.setImageResource(resId);
    }


    public void setRightBtnDrawableLeft(int resId){
        TextView rightBtn = (TextView) findViewById(R.id.btn_right);
        Drawable drawable = getContext().getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        rightBtn.setCompoundDrawables(drawable, null, null, null);
    }

    public void setRightBtnDrawableRight(int resId){
        TextView rightBtn = (TextView) findViewById(R.id.btn_right);
        Drawable drawable = getContext().getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        rightBtn.setCompoundDrawables(null, null, drawable, null);

    }

    public void setOnRightBtnClickListener(View.OnClickListener onClickListener){
        TextView rightBtn = (TextView) findViewById(R.id.btn_right);
        ImageView rightImgBtn = (ImageView) findViewById(R.id.imgBtn_right);
        if (rightBtn.getVisibility() == VISIBLE)
            rightBtn.setOnClickListener(onClickListener);
        if (rightImgBtn.getVisibility() == VISIBLE)
            rightImgBtn.setOnClickListener(onClickListener);
    }

    public void setOnRightBtnDrawablePadding(int drawablePadding){
        TextView rightBtn = (TextView) findViewById(R.id.btn_right);
        rightBtn.setCompoundDrawablePadding(SizeUtils.dp2px(getContext(), drawablePadding));
    }

    public void setLeftBtnText(String text){
        TextView leftBtn = (TextView) findViewById(R.id.btn_left);
        if (leftBtn.getVisibility() == GONE){
            leftBtn.setVisibility(VISIBLE);
        }
        leftBtn.setText(text);
    }

    public void setLeftBtnText(int resId){
        String text = getContext().getString(resId);
        setLeftBtnText(text);
    }

    public void setLeftBtnDrawableLeft(int resId){
        TextView leftBtn = (TextView) findViewById(R.id.btn_left);
        Drawable drawable = getContext().getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        leftBtn.setCompoundDrawables(drawable, null, null, null);
    }

    public void setLeftBtnDrawableRight(int resId){
        TextView leftBtn = (TextView) findViewById(R.id.btn_left);
        Drawable drawable = getContext().getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        leftBtn.setCompoundDrawables(null, null, drawable, null);
    }

    public void setOnLeftBtnClickListener(View.OnClickListener onClickListener){
        TextView leftBtn = (TextView) findViewById(R.id.btn_left);
        leftBtn.setOnClickListener(onClickListener);
    }

    public void setOnLeftBtnDrawablePadding(int drawablePadding){
        TextView leftBtn = (TextView) findViewById(R.id.btn_left);
        leftBtn.setCompoundDrawablePadding(SizeUtils.dp2px(getContext(), drawablePadding));
    }

    public void showSearchView(boolean searchable){
        EditText searchView = (EditText) findViewById(R.id.searchView);
        if (searchView.getVisibility() == GONE){
            searchView.setVisibility(VISIBLE);
        }
        searchView.setFocusable(searchable);
    }

    public void setOnSearchViewClickListener(View.OnClickListener onClickListener){
        EditText searchView = (EditText) findViewById(R.id.searchView);
        searchView.setOnClickListener(onClickListener);
    }

    public String getSearchText(){
        EditText searchView = (EditText) findViewById(R.id.searchView);
        return searchView.getText().toString().trim();
    }

    public void setOnEditorActionListener(EditText.OnEditorActionListener onEditorActionListener){
        EditText searchView = (EditText) findViewById(R.id.searchView);
        searchView.setOnEditorActionListener(onEditorActionListener);
    }

    public void setKeyword(String keyword) {
        EditText searchView = (EditText) findViewById(R.id.searchView);
        searchView.setText(keyword);
    }
}