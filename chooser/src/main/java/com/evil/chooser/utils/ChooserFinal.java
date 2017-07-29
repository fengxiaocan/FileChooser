package com.evil.chooser.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.evil.chooser.ChooseInfo;
import com.evil.chooser.FileChooserActivity;
import com.evil.chooser.R;
import com.evil.chooser.iface.ChooserType;
import com.evil.chooser.iface.OnChooseCallback;

import java.util.List;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 11:39
 * @描述： TODO
 */

public final class ChooserFinal {
    protected static ChooserConfig mChooserConfig;

    public static void init(ChooserConfig config) {
        mChooserConfig = config;
    }

    public static ChooserConfig getChooserConfig() {
        return mChooserConfig;
    }

    public static void onChooseSuccess(List<ChooseInfo> chooseInfos) {
        mChooserConfig.mChooseCallback.onChooseSuccess(mChooserConfig.requestCode, chooseInfos);
    }

    public static void onChooseFailure(String msg) {
        mChooserConfig.mChooseCallback.onChooseFailure(mChooserConfig.requestCode, msg);
    }

    public static Context getContext() {
        return mChooserConfig.getContext();
    }


    /**
     * 选择图片
     *
     * @param requestCode
     * @param maxSize
     * @param callback
     */
    public static void choosePhoto(int requestCode, int maxSize, OnChooseCallback callback) {
        if (mChooserConfig != null) {
            if (maxSize < 0) {
                maxSize = 1;
            }
            mChooserConfig.maxSize = maxSize;
            mChooserConfig.requestCode = requestCode;
            mChooserConfig.mChooseCallback = callback;
            mChooserConfig.sChooserType = ChooserType.IMAGE;
            Intent intent = new Intent(getContext(), FileChooserActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } else {
            if (callback != null) {
                callback.onChooseFailure(requestCode, getContext().getString(R.string.open_chooser_fail));
            }
            Log.e("ChooserFinal", "Please init ChooserFinal.");
        }
    }

    /**
     * 选择视频
     *
     * @param requestCode
     * @param callback
     */
    public static void chooseVideo(int requestCode, OnChooseCallback callback) {
        if (mChooserConfig != null) {
            mChooserConfig.mChooseCallback = callback;
            mChooserConfig.requestCode = requestCode;
            mChooserConfig.sChooserType = ChooserType.VIDEO;
            Intent intent = new Intent(getContext(), FileChooserActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } else {
            if (callback != null) {
                callback.onChooseFailure(requestCode, getContext().getString(R.string.open_chooser_fail));
            }
            Log.e("ChooserFinal", "Please init ChooserFinal.");
        }
    }


}
