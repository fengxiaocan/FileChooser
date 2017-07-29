package com.evil.chooser;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 10:26
 * @描述： 音视频 AV ( audio / video )
 */

public class ChooseInfo {
    private int avId;

    private boolean isSelect;

    private long size;
    private String path;
    private long changeTime;
    private boolean isPhoto;

    public boolean isPhoto() {
        return isPhoto;
    }

    public void setPhoto(boolean photo) {
        isPhoto = photo;
    }

    public long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(long changeTime) {
        this.changeTime = changeTime;
    }

    boolean isSelect() {
        return isSelect;
    }

    void setSelect(boolean select) {
        isSelect = select;
    }

    public int getAvId() {
        return avId;
    }

    public void setAvId(int avId) {
        this.avId = avId;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
