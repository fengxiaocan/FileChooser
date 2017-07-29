package com.evil.chooser.iface;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.evil.chooser.iface.IFileType.ALARMS;
import static com.evil.chooser.iface.IFileType.DCIM;
import static com.evil.chooser.iface.IFileType.DOWNLOADS;
import static com.evil.chooser.iface.IFileType.MOVIES;
import static com.evil.chooser.iface.IFileType.MUSIC;
import static com.evil.chooser.iface.IFileType.NOTIFICATIONS;
import static com.evil.chooser.iface.IFileType.PICTURES;
import static com.evil.chooser.iface.IFileType.PODCASTS;
import static com.evil.chooser.iface.IFileType.RINGTONES;

/**
 * @项目名： BaseApp
 * @包名： com.fxc.base.intface
 * @创建者: Noah.冯
 * @时间: 15:31
 * @描述： TODO
 */
@StringDef({ALARMS,DCIM,DOWNLOADS,MOVIES,MUSIC,NOTIFICATIONS,PICTURES,PODCASTS,RINGTONES})
@Retention(RetentionPolicy.SOURCE)
public @interface IFileType {

    //警报的铃声
    String ALARMS = "Alarms";
    //相机拍摄的图片和视频保存的位置
    String DCIM = "DCIM";
    //下载文件保存的位置
    String DOWNLOADS = "Download";
    //电影保存的位置， 比如 通过google play下载的电影
    String MOVIES = "Movies";
    //音乐保存的位置
    String MUSIC = "Music";
    //通知音保存的位置
    String NOTIFICATIONS = "Notifications";
    //下载的图片保存的位置
    String PICTURES = "Pictures";
    //用于保存podcast(博客)的音频文件
    String PODCASTS = "Podcasts";
    //保存铃声的位置
    String RINGTONES = "Ringtones";
}
