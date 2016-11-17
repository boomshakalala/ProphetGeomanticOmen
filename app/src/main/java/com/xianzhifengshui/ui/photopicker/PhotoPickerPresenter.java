package com.xianzhifengshui.ui.photopicker;

import com.xianzhifengshui.base.BasePresenter;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述:
 */
public class PhotoPickerPresenter extends BasePresenter implements PhotoPickerContract.Presenter {
    PhotoPickerContract.View view;

    public PhotoPickerPresenter(PhotoPickerContract.View view) {
        this.view = view;
    }

    @Override
    public void loadImage() {

    }

    @Override
    public void loadDir() {

    }
}
