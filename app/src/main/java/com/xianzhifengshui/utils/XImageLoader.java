package com.xianzhifengshui.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xianzhifengshui.R;

/**
 * 作者: chengx
 * 日期: 2016/9/27.
 * 描述: universal-imageloader二次封装类
 */
public class XImageLoader extends ImageLoader {

    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    private static volatile XImageLoader instance;



    public static XImageLoader getInstance() {
        if(instance == null) {
            synchronized(XImageLoader.class) {
                if(instance == null) {
                    instance = new XImageLoader();
                }
            }
        }
        return instance;
    }

    protected XImageLoader(){
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.webdefault_icon)
                .showImageOnFail(R.drawable.webdefault_icon)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    /**
     * 加载图片(本地、网络)
     * @param imageView 要加载图片的ImageView
     * @param uri 图片uri "
     * http://site.com/image.png" // from Web
     * "file:///mnt/sdcard/image.png" // from SD card
     * "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
     * "content://media/external/images/media/13" // from content provider
     * "content://media/external/video/media/13" // from content provider (video thumbnail)
     * "assets://image.png" // from assets
     * "drawable://" + R.drawable.img // from drawables (non-9patch images)
     */
    public void display(ImageView imageView, String uri){
        imageLoader.displayImage(uri,imageView,options);
    }


}
