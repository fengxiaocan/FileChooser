package com.evil.chooser.iface;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.evil.chooser.iface.ChooserType.AV;
import static com.evil.chooser.iface.ChooserType.IMAGE;
import static com.evil.chooser.iface.ChooserType.VIDEO;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser.iface
 * @创建者: Noah.冯
 * @时间: 19:34
 * @描述： TODO
 */
@StringDef({IMAGE, VIDEO, AV})
@Retention(RetentionPolicy.SOURCE)
public @interface ChooserType {
    String IMAGE = "IMAGE";
    String VIDEO = "VIDEO";
    String AV = "AV";
}
