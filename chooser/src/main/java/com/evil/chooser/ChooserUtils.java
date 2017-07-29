package com.evil.chooser;

import android.util.Log;

import com.evil.chooser.iface.ChooseCallback;
import com.evil.chooser.iface.OnChooseCallback;
import com.evil.chooser.iface.RefreshCallback;
import com.evil.chooser.iface.SelectCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 15:04
 * @描述： TODO
 */

/**
 * @hide
 */
class ChooserUtils {
    /*保存浏览详情的图片信息*/
    public static List<ChooseInfo> sChooseInfos;
    /*保存选择的信息*/
    public static List<ChooseInfo> sSelectChooseInfos = new ArrayList<>();
    /*是否查找结束*/
    public static boolean isFinishFind;

    /*是否是预览模式*/
    static int sLookType = 0;

    /*选择模式的标题文字*/
    public static String sChooserString;
    /*保存图片分类列表*/
    static HashMap<String, List<ChooseInfo>> sParentMap;
    /*选择GridView列表的回调*/
    static SelectCallback sSelectCallback;
    /*选择列表的回调*/
    static ChooseCallback sChooseCallback;
    /*选择更新回调*/
    static RefreshCallback sRefreshCallback;
    /*是否是选择全部*/
    static boolean isSelectAll;

    static OnChooseCallback sOnChooseCallback;

    public static void setChooseCallback(ChooseCallback chooseCallback) {
        sChooseCallback = chooseCallback;
    }

    public static void setSelectCallback(SelectCallback selectCallback) {
        sSelectCallback = selectCallback;
    }

    public static void setRefreshCallback(RefreshCallback refreshCallback) {
        sRefreshCallback = refreshCallback;
    }

    public static void finishFinish() {
        isFinishFind = true;
    }

    public static void log(String message) {
        Log.e("fengxiaocan", message);
    }

    public static void clearAll() {
        if (sChooseInfos != null) {
            sChooseInfos.clear();
        }
        if (sSelectChooseInfos != null) {
            sSelectChooseInfos.clear();
        }
        if (sParentMap != null) {
            sParentMap.clear();
        }
    }
}
