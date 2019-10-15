/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.cache;

import java.util.Set;

/**
 * 多个索引缓存
 * K: default key
 * 可以按indexName 构建不同的key
 * T: 实体
 */
public interface MultiIndexCache<K, T> extends RMICache<K, T> {

    /**
     * 从本地内存中获取缓存对象
     * 当是key --> value 是1:1 使用些方法
     * 当有多个value时 返回first
     *
     * @param indexName 索引名名称、一个对外可能构建多个索引
     * @param key       索引
     *
     * @return 缓存对象
     */
    T get(String indexName, Object key);

    /**
     * 从本地内从中获取对象
     *
     * @param indexName
     * @param keys
     *
     * @return
     */
    Iterable<T> getListByKeys(String indexName, Set<Object> keys);

    /**
     * 从本地内存中获取key的多个object
     * 当是key --> value 是1:N 使用些方法
     *
     * @param indexName 索引名名称、一个对外可能构建多个索引
     * @param key       索引
     *
     * @return 缓存对象
     */
    Iterable<T> getMultiValue(String indexName, Object key);

    /**
     * 根据key重建缓存
     * T不存在时删除
     * T存在时更新
     *
     * @param key
     */
    void updateCacheByKey(K key);

    String ID_INDEX = "id";
}
