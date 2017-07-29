package com.evil.chooser.iface;

/**
 * @项目名： FileChooser
 * @包名： com.evil.chooser
 * @创建者: Noah.冯
 * @时间: 11:36
 * @描述： TODO
 */

public interface FindFileCallback<T,V> {
    void update(T t,V v);
    void finish(T t,V v);
}
