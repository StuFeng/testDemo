/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.cache;

public interface MultiValueContainer<K, T> extends CacheContainer<K, T> {

    Iterable<T> getMulti(K key);

    Iterable<T> removeMulti(K key);

    boolean removeByKeyAndValue(K key, T value);

    Iterable<T> replaceValues(K key, Iterable<? extends T> value);
}
