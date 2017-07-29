package com.evil.chooser.iface;

import com.evil.chooser.ChooseInfo;

import java.util.List;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser.iface
 * @创建者: Noah.冯
 * @时间: 16:09
 * @描述： TODO
 */

public interface OnChooseCallback {
    void onChooseFailure(int requestCode, String msg);

    void onChooseSuccess(int requestCode, List<ChooseInfo> chooseInfos);
}
