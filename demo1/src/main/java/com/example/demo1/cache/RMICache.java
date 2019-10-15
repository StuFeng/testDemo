/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.cache;

import java.util.Set;

/**
 * 本地缓存穿透
 */
public interface RMICache<K, T> extends Cache<K, T> {

    /**
     * get value by pointed key, null will remote call
     *
     * @param key key
     *
     * @return cache object
     */
    T getIfNullRemote(K key);

    /**
     * get value list by pointed keys, null will remote call
     *
     * @param keys keys
     *
     * @return cache objects
     */
    Iterable<T> getListByKeysIfNullRemote(Set<K> keys);

}
