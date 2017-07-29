package com.evil.chooser.utils;

import android.os.Environment;

import com.evil.chooser.iface.IFileType;

import java.io.File;

/**
 * <pre>
 *     time  : 2016/08/11
 *     desc  : SD卡相关工具类
 * </pre>
 */
public class SDUtils {

    private SDUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean sdCardExist() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取SD卡路径
     * <p>先用shell，shell失败再普通方法获取，一般是/storage/emulated/0/</p>
     *
     * @return SD卡路径
     */
    public static String getSDCardPath() {
        if (!sdCardExist()) {
            return null;
        }
        return getSdRoot().getAbsolutePath();
    }

    /**
     * 获取系统SD存储路径
     */
    public static String getSdRootPath() {
        return getSdRoot().getAbsolutePath();
    }

    /**
     * 获取sd卡根目录
     */
    public static File getSdRoot() {
        File sdDir = Environment.getExternalStorageDirectory();//获取根目录
        return sdDir;
    }

    /**
     * 获取保存目录
     */
    public static File getSaveDir(String name) {
        File saveDir;
        if (sdCardExist()) {
            saveDir = new File(getSdRoot(),
                    ChooserFinal.getContext().getPackageCodePath() + "/" +
                    name);//获取根目录
        } else {
            saveDir = new File(ChooserFinal.getContext().getFilesDir(), name);
        }
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        return saveDir;
    }

    /**
     * 获取保存目录
     */
    public static File getSdSaveDir(String name) {
        if (sdCardExist()) {
            File saveDir = new File(getSdRoot(), name);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            return saveDir;
        }
        throw new NullPointerException("SD卡不存在");
    }

    /**
     * 获取公有的保存目录
     */
    public static File getPublicDir(@IFileType String type, String name) {
        if (sdCardExist()) {
            File saveDir = new File(getPublicDir(type), name);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            return saveDir;
        }
        throw new NullPointerException("SD卡不存在");
    }

    /**
     * 获取公有的保存目录
     */
    public static File getPublicDir(@IFileType String type) {
        if (sdCardExist()) {
            return Environment.getExternalStoragePublicDirectory(type);
        }
        throw new NullPointerException("SD卡不存在");
    }


    /**
     * 获取SD卡data路径
     *
     * @return SD卡data路径
     */
    public static String getPackagePath() {
        if (!sdCardExist()) {
            return null;
        }
        return getSdRoot().getAbsolutePath() + "/Android/data/" +
               ChooserFinal.getContext().getPackageName();
    }
}