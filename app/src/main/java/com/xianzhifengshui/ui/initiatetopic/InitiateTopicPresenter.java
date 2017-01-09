package com.xianzhifengshui.ui.initiatetopic;

import com.xianzhifengshui.base.BasePresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: chengx
 * 日期: 2016/11/14.
 * 描述:
 */
public class InitiateTopicPresenter extends BasePresenter implements InitiateTopicContract.Presenter {
    private InitiateTopicContract.View view;

    public InitiateTopicPresenter(InitiateTopicContract.View view) {
        this.view = view;
    }

    @Override
    public void uploadFiles(List<String> files) {
        File file1 = new File(files.get(0));
        File file2 = new File(files.get(1));
        List<File> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        api.fileUploadBatch(fileList);
    }
}
