package com.xianzhifengshui.ui.photopicker;

import com.xianzhifengshui.api.model.ImageFloder;
import com.xianzhifengshui.base.IPresenter;
import com.xianzhifengshui.base.IView;

import java.io.File;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述:
 */
public interface PhotoPickerContract {
    interface View extends IView<Presenter>{
        void loadImage(ImageFloder floder);
        void loadDir(List<ImageFloder> dirs);
    }

    interface  Presenter extends IPresenter{

    }

}
