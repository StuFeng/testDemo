/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.cache;

public interface CacheIncrDataProvider<K, T> extends CacheDataProvider<K, T> {

    /**
     * onLoad update data for every polling
     *
     * @param lastModifiedSince previous refresh time
     *
     * @return
     */
    Iterable<T> refresh(long lastModifiedSince);
}
