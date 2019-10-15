/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.cache;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;

/**
 * 本地全量缓存 支持本地没有击穿
 */
@Slf4j
public class LocalFullCache<K, T> implements RMICache<K, T> {

    final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);

    protected CacheContainer<K, T> container;

    protected CacheDataProvider<K, T> dataProvider;

    protected AtomicBoolean startFlag = new AtomicBoolean(false);

    protected volatile boolean reloadAll = false;

    // 全量周期 默认 10分钟
    protected long fullPeriod = 10 * 60 * 1000;

    /**
     * 构建增量组态对象
     *
     * @param dataProvider 数据提供方
     * @param fullPeriod   全量时间
     */
    public LocalFullCache(CacheDataProvider<K, T> dataProvider, long fullPeriod) {
        this.dataProvider = dataProvider;
        this.fullPeriod = fullPeriod;
        init();
    }

    public LocalFullCache() {
    }

    // 全量加载
    public void onReloadAll() {
        try {
            reloadAll = true;
            log.debug("on reload all ");
            Iterable<T> dataList = dataProvider.onLoad();
            loadDataToContainer(dataList, LoadType.Full);
            processRemain();
        } catch (Exception e) {
            log.error("启动过程中加载缓存失败！！！失败啦！！！夭寿啦！！！\n on reload all exception!.", e);
            // todo 业务报警
            if (!startFlag.get()) {
                throw new RuntimeException("启动过程中加载缓存失败！！！终止应用启动");
            }
        } finally {
            reloadAll = false;
        }
    }

    public void init() {
        this.container = createContainer();
        onReloadAll();
        // 如果需要定时全量加载
        if (fullPeriod > 0) {
            schedule.scheduleWithFixedDelay(this::onReloadAll, fullPeriod, fullPeriod, TimeUnit.MILLISECONDS);
        }
        started();
    }

    public T get(K key) {
        if (!isLegalKey(key)) {
            return null;
        }
        return container.get(key);
    }

    /**
     * key是否是合格的
     *
     * @param key
     *
     * @return
     */
    public boolean isLegalKey(K key) {
        if (key == null) {
            return false;
        }
        if (key instanceof Integer && (Integer) key == 0) {
            return false;
        }
        if (key instanceof Long && (Long) key == 0) {
            return false;
        }
        return !(key instanceof String) || !StringUtils.isEmpty((String) key);
    }

    public Iterable<T> getListByKeys(Set<K> keys) {
        List<T> list = Lists.newArrayListWithCapacity(keys.size());
        for (K key : keys) {
            if (!isLegalKey(key)) {
                continue;
            }
            T value = get(key);
            if (value != null) {
                list.add(value);
            }
        }
        return list;
    }

    public Iterable<T> getAll() {
        return ImmutableList.copyOf(container.values());
    }

    @Override
    public T getIfNullRemote(K key) {
        if (!isLegalKey(key)) {
            return null;
        }
        T t = get(key);
        if (t == null) {
            log.warn("cache miss ,simpleName : {}", dataProvider.getClass().getSimpleName());
            t = dataProvider.queryByKey(key);
            // 待优化可能浪费没有必要的打穿
            if (dataProvider.needRemove(t)) {
                return null;
            }
            return t;
        }
        return t;
    }

    @Override
    public Iterable<T> getListByKeysIfNullRemote(Set<K> keys) {
        List<T> list = Lists.newArrayListWithCapacity(keys.size());
        for (K key : keys) {
            if (!isLegalKey(key)) {
                continue;
            }
            T value = getIfNullRemote(key);
            if (value != null) {
                list.add(value);
            }
        }
        return list;
    }

    protected void loadDataToContainer(Iterable<T> dataList, LoadType loadType) {
        Set<K> keys = Sets.newHashSet();
        for (T entity : dataList) {
            if (entity == null) {
                continue;
            }
            K key = getEntityKey(entity);
            if (dataProvider.needRemove(entity)) {
                container.remove(key);
                log.debug("remove entity: {}. {}", key, entity.getClass());
            } else {
                keys.add(key);
                container.put(key, entity);
                log.debug("onLoad entity: {}. {}", key, entity.getClass());
            }
        }
        if (loadType == LoadType.Full) {
            Set<K> allKeys = container.keys();
            allKeys.stream().forEach(k -> {
                if (!keys.contains(k)) {
                    container.remove(k);
                }
            });
        }
    }

    protected void processRemain() {
    }

    private K getEntityKey(T t) {
        return dataProvider.getKey(t);
    }

    protected CacheContainer<K, T> createContainer() {
        return new CacheContainer<K, T>() {

            ConcurrentMap<K, T> map = Maps.newConcurrentMap();

            @Override
            public T put(K key, T value) {
                return map.put(key, value);
            }

            @Override
            public T get(K key) {
                return map.get(key);
            }

            @Override
            public T remove(K key) {
                return map.remove(key);
            }

            @Override
            public Set<K> keys() {
                return map.keySet();
            }

            @Override
            public Iterable<T> values() {
                return map.values();
            }
        };
    }

    protected void started() {
        this.startFlag.compareAndSet(false, true);
    }
}

