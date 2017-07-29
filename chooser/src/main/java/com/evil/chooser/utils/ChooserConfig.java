package com.evil.chooser.utils;

import android.content.Context;

import com.evil.chooser.iface.ChooserType;
import com.evil.chooser.iface.ImageLoader;
import com.evil.chooser.iface.OnChooseCallback;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 19:49
 * @描述： TODO
 */

public final class ChooserConfig {
    protected Context context;
    protected ImageLoader imageLoader;
    protected OnChooseCallback mChooseCallback;
    protected int maxSize;
    protected int requestCode;
    /*图片选择模式,视频选择模式,图视频选择模式*/
    @ChooserType
    String sChooserType = ChooserType.AV;
    //    private File takePhotoFolder;
    //    private File editPhotoCacheFolder;


    public int getRequestCode() {
        return requestCode;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getChooserType() {
        return sChooserType;
    }

    public OnChooseCallback getChooseCallback() {
        return mChooseCallback;
    }

    private ChooserConfig(Builder builder) {
        this.context = builder.context;
        this.imageLoader = builder.imageLoader;
        this.maxSize = builder.maxSize;
        //        this.takePhotoFolder = builder.takePhotoFolder;
        //        this.editPhotoCacheFolder = builder.editPhotoCacheFolder;
        //
        //        if (takePhotoFolder == null) {
        //            takePhotoFolder = SDUtils.getPublicDir(DCIM);
        //        }
        //        if (!takePhotoFolder.exists()) {
        //            takePhotoFolder.mkdirs();
        //        }
        //
        //        if (editPhotoCacheFolder == null) {
        //            editPhotoCacheFolder = SDUtils.getPublicDir(PICTURES);
        //        }
        //        if (!editPhotoCacheFolder.exists()) {
        //            editPhotoCacheFolder.mkdirs();
        //        }
    }

    public static class Builder {
        private Context context;
        private ImageLoader imageLoader;
        private int maxSize;
        //        private File takePhotoFolder;//配置拍照缓存目录
        //        private File editPhotoCacheFolder;//配置编辑图片产生的文件缓存目录

        public Builder(Context context, ImageLoader imageLoader, int maxSize) {
            this.context = context;
            this.imageLoader = imageLoader;
            this.maxSize = maxSize;
        }

        public Builder(Context context, ImageLoader imageLoader) {
            this.context = context;
            this.imageLoader = imageLoader;
            this.maxSize = 1;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }
        //        public Builder setTakePhotoFolder(File takePhotoFolder) {
        //            this.takePhotoFolder = takePhotoFolder;
        //            return this;
        //        }
        //
        //        public Builder setEditPhotoCacheFolder(File editPhotoCacheFolder) {
        //            this.editPhotoCacheFolder = editPhotoCacheFolder;
        //            return this;
        //        }


        public ChooserConfig build() {
            return new ChooserConfig(this);
        }
    }

    public Context getContext() {
        return context;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }


    //    public File getTakePhotoFolder() {
    //        return takePhotoFolder;
    //    }

    //    public File getEditPhotoCacheFolder() {
    //        return editPhotoCacheFolder;
    //    }
}
