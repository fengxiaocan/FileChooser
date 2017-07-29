package com.evil.chooser.iface;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 10:07
 * @描述： TODO
 */

public interface ImageLoader {

    void loadRes(Context context,@DrawableRes int res, ImageView iv);

    void loadFile(Context context,String path,@DrawableRes int loadingRes,@DrawableRes int errorRes,ImageView iv);
}
