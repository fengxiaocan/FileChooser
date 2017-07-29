package com.evil.chooser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.evil.chooser.pmsion.CheckPermissionsCallback;
import com.evil.chooser.pmsion.RequestPermissionsCallback;

/**
 *  @项目名： BaseApp
 *  @包名： com.fxc.sss.pmsion
 *  @创建者: Noah.冯
 *  @时间: 12:59
 *  @描述： 申请权限工具类
 */

/**
 * @hide
 */
class RequestPermissionsUtils {
    /**
     * 检查是否有权限
     * @param activity activity
     * @param requestCode 申请码
     * @param permissions 权限组
     * @param callback 检测权限回调
     */
    public static void checkPermissions(Activity activity,
                                        int requestCode,
                                        @Nullable String[] permissions,
                                        CheckPermissionsCallback callback)
    {
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity,
                                                  permissions[i]) != PackageManager.PERMISSION_GRANTED)
            {
                callback.nonePremissions(requestCode, permissions);
                return;
            }
        }
        //已有权限
        callback.hasPremissions(requestCode);
    }

    /**
     * 申请权限
     * @param activity activity
     * @param requestCode 请求码
     * @param permissions 权限组
     */
    public static void requestPermissions(Activity activity,
                                          int requestCode,
                                          @Nullable String[] permissions)
    {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    /**
     * 申请权限
     * @param activity activity
     * @param requestCode 请求码
     * @param permission 权限
     */
    public static void requestPermissions(Activity activity,
                                          int requestCode,
                                          @Nullable String permission)
    {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    /**
     * 检测以及申请权限
     * @param activity activity
     * @param requestCode 请求码
     * @param permissions 权限
     */
    public static void checkAndRequestPermissions(Activity activity,
                                                  int requestCode,
                                                  @Nullable String[] permissions,
                                                  RequestPermissionsCallback callback)
    {
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity,
                                                  permissions[i]) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(activity, requestCode, permissions);
                return;
            }
        }
        //已有权限
        callback.requestPremissionsSuccess(requestCode);
    }

    /**
     * 检测以及申请权限
     * @param activity activity
     * @param requestCode 请求码
     * @param permissions 权限
     * @param requestPermissonsCause 申请权限原因
     */
    public static void checkAndRequestPermissions(Activity activity,
                                                  final int requestCode,
                                                  @Nullable final String[] permissions,
                                                  final RequestPermissionsCallback callback,
                                                  String requestPermissonsCause)
    {
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity,
                                                  permissions[i]) != PackageManager.PERMISSION_GRANTED)
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[i])) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage(requestPermissonsCause);
                    builder.setNeutralButton("确定",
                                             new TOnClickListener<Activity>(
                                                     activity)
                                             {
                                                 @Override
                                                 public void onClick(DialogInterface dialog,
                                                                     int which)
                                                 {
                                                     requestPermissions(t,requestCode,permissions);
                                                     dialog.dismiss();
                                                 }
                                             });
                    builder.setNegativeButton("取消",
                                              new TOnClickListener<RequestPermissionsCallback>(
                                                      callback)
                                              {
                                                  @Override
                                                  public void onClick(DialogInterface dialog,
                                                                      int which)
                                                  {
                                                      callback.requestPremissionsDefeat(requestCode,permissions);
                                                      dialog.dismiss();
                                                  }
                                              });
                    builder.show();
                } else {
                    requestPermissions(activity, requestCode, permissions);
                }
                return;
            }
        }
        //已有权限
        callback.requestPremissionsSuccess(requestCode);
    }

    /**
     * 重写回调方法
     * @param <T>
     */
    protected static abstract class TOnClickListener<T>
            implements DialogInterface.OnClickListener
    {
        protected T t;

        public TOnClickListener(T t) {
            this.t = t;
        }
    }
}
