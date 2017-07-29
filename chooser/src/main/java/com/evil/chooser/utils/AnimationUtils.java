package com.evil.chooser.utils;


import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser.utils
 * @创建者: Noah.冯
 * @时间: 18:25
 * @描述： TODO
 */

public class AnimationUtils {
    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom(long duration) {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(duration);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation moveToViewLocation(long duration) {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(duration);
        return mHiddenAction;
    }

    /**
     * 隐藏
     */
    public static AlphaAnimation hideAlpha(long duration) {
        AlphaAnimation mHiddenAction = new AlphaAnimation(1.0f, 0.0f);
        mHiddenAction.setDuration(duration);
        return mHiddenAction;
    }

    /**
     * 隐藏
     */
    public static AlphaAnimation showAlpha(long duration) {
        AlphaAnimation mHiddenAction = new AlphaAnimation(0.0f, 1.0f);
        mHiddenAction.setDuration(duration);
        return mHiddenAction;
    }


    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static void moveToViewBottom(View view, long duration) {
        view.setVisibility(View.GONE);
        view.setAnimation(moveToViewBottom(duration));
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static void moveToViewLocation(View view, long duration) {
        view.setVisibility(View.VISIBLE);
        view.setAnimation(moveToViewLocation(duration));
    }

    /**
     * 隐藏
     */
    public static void hideAlpha(View view, long duration) {
        view.setVisibility(View.GONE);
        view.setAnimation(hideAlpha(duration));
    }

    /**
     * 隐藏
     */
    public static void showAlpha(View view, long duration) {
        view.setVisibility(View.VISIBLE);
        view.setAnimation(showAlpha(duration));
    }

}
