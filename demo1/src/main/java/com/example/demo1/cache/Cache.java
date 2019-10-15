/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.cache;

import java.util.Set;

public interface Cache<K, T> {
    /**
     * get value by pointed key, null will return if key not exists
     *
     * @param key key
     *
     * @return cache object
     */
    T get(K key);

    /**
     * get value list by pointed keys, null value will be removed
     *
     * @param keys keys
     *
     * @return cache objects
     */
    Iterable<T> getListByKeys(Set<K> keys);

    /**
     * get all cached value
     *
     * @return cache objects
     */
    Iterable<T> getAll();
}
