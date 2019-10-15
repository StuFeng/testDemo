/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.cache;

import java.util.Set;

public interface CacheContainer<K, T> {

    T put(K key, T value);

    T get(K key);

    T remove(K key);

    Set<K> keys();

    Iterable<T> values();
}
