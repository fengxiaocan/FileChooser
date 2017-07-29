package com.evil.chooser.iface;

import com.evil.chooser.ChooseInfo;

import java.util.List;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser.iface
 * @创建者: Noah.冯
 * @时间: 15:56
 * @描述： TODO
 */

public interface ChooseCallback {
    void choose(List<ChooseInfo> chooseInfos);
}
