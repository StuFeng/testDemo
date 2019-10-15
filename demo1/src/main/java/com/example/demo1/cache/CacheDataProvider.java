/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.cache;

public interface CacheDataProvider<K, T> {

    /**
     * onLoad all data for the first time
     *
     * @return
     */
    Iterable<T> onLoad();

    /**
     * remove condition
     *
     * @param t
     *
     * @return
     */
    boolean needRemove(T t);

    /**
     * get key property
     *
     * @param t
     *
     * @return
     */
    K getKey(T t);

    /**
     * 远程获取对象
     *
     * @param key
     *
     * @return
     */
    T queryByKey(K key);
}
