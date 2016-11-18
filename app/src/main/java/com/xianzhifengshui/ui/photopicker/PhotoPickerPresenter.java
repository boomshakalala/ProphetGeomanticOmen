package com.xianzhifengshui.ui.photopicker;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import com.xianzhifengshui.api.model.ImageFloder;
import com.xianzhifengshui.base.BasePresenter;
import com.xianzhifengshui.widget.popup.ListImageDirPopupWindow;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 作者: chengx
 * 日期: 2016/11/17.
 * 描述:
 */
public class PhotoPickerPresenter extends BasePresenter implements PhotoPickerContract.Presenter {
    private PhotoPickerContract.View view;
    private Runnable getImageRunnable;
    private Handler getImageHandler;
    private List<ImageFloder> imageFloders;
    private ImageFloder maxImagesFloder;

    public PhotoPickerPresenter(PhotoPickerContract.View view) {
        this.view = view;
        imageFloders = new ArrayList<>();
        getImageHandler = new GetImageHandler();
        getImageRunnable = new GetImageRunnable();
        threadPool.execute(getImageRunnable);
    }


    public class GetImageRunnable implements Runnable{

        @Override
        public void run() {
            HashSet<String> dirPaths = new HashSet<>();
            String firstImage = null;
            int maxPicSize = 0;
            imageFloders = new ArrayList<>();
            maxImagesFloder = null;
            maxImagesFloder = new ImageFloder();
            maxImagesFloder.setDirName("所有图片");
            maxImagesFloder.setDir("all");
            final List<String> allImages = new ArrayList<>();
            Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentResolver contentResolver = ((Activity)view).getContentResolver();
            Cursor cursor = contentResolver.query(imageUri,null,MediaStore.Images.Media.MIME_TYPE+
            "=? or " + MediaStore.Images.Media.MIME_TYPE+ "=?", new String[]{"image/jpeg", "image/png"},MediaStore.Images.Media.DATE_MODIFIED);
            while (cursor.moveToNext()){
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                if (firstImage == null){
                    firstImage = path;
                }
                File parentFile = new File(path).getParentFile();
                if (parentFile == null)
                    continue;
                final String dirPath = parentFile.getAbsolutePath();
                ImageFloder floder = null;
                if (dirPaths.contains(dirPath)) {
                    continue;
                }
                else{
                    dirPaths.add(dirPath);
                    floder = new ImageFloder();
                    floder.setDir(dirPath);
                    floder.setDirName(parentFile.getName());
                    floder.setFirstImagePath(path);
                }
                final List<String> paths = new ArrayList<>();
                final String[] fileNames = parentFile.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg")){
                            allImages.add(dirPath+"/"+filename);
                            paths.add(dirPath+"/"+filename);
                            return true;
                        }
                        return false;
                    }
                });
                int picSize = fileNames.length;
                floder.setImageCount(picSize);
                floder.setImags(paths);
                imageFloders.add(floder);
                maxPicSize+=picSize;
            }
            cursor.close();
            dirPaths = null;
            maxImagesFloder.setFirstImagePath(allImages.get(0));
            maxImagesFloder.setImageCount(maxPicSize);
            maxImagesFloder.setImags(allImages);
            imageFloders.add(0,maxImagesFloder);
            getImageHandler.sendEmptyMessage(0x102);

        }
    }

    public class GetImageHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x101:
                    view.showWaiting();
                    break;
                case 0x102:
                    view.loadImage(maxImagesFloder);
                    view.loadDir(imageFloders);
                    view.closeWait();
                    break;
                default:
                    break;
            }

        }
    }

}
