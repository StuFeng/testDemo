/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.demo1.cache;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Queues;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalMultiIndexCache<K, T> extends LocalFullCache<K, T> implements MultiIndexCache<K, T> {

    protected volatile Map<String, CacheContainer<Object, T>> containerMap = Maps.newHashMap();

    protected final Map<String, Function<T, Object>> indexingFunctions = Maps.newHashMap();

    protected final Map<String, Pair<Function<T, List<?>>, Function<Object, Object>>> pairFunctions = Maps.newHashMap();

    protected final Queue<K> cachedKeyQueue = Queues.newLinkedBlockingDeque();

    protected final ReentrantLock loadingLock = new ReentrantLock();

    protected volatile String mainKeyName;

    private boolean incrDisable;

    public LocalMultiIndexCache() {
    }

    protected LocalMultiIndexCache(MultiIndexCacheBuilder<K, T> builder) {
        Preconditions.checkNotNull(builder.mainKeyName, "main index name not be empty");
        Preconditions.checkNotNull(builder.dataProvider, "dataProvider not be null");
        Preconditions.checkArgument(MapUtils.isNotEmpty(builder.indexingFunctions), "index function not be null");
        this.mainKeyName = builder.mainKeyName;
        this.dataProvider = builder.dataProvider;
        this.incrDisable = builder.incrDisable;
        this.fullPeriod = builder.fullPeriod == 0 ? super.fullPeriod : builder.fullPeriod;
        for (Map.Entry<String, Function<T, Object>> item : builder.indexingFunctions.entrySet()) {
            addIndexFunction(item.getKey(), item.getValue());
        }
        for (Map.Entry<String, Pair<Function<T, List<?>>, Function<Object, Object>>> item :
                builder.listIndexingFunctions
                        .entrySet()) {
            addPairIndexFunction(item.getKey(), item.getValue());
        }
        init();
    }

    protected void init(MultiIndexCacheBuilder<K, T> builder) {
        Preconditions.checkNotNull(builder.mainKeyName, "main index name not be empty");
        Preconditions.checkNotNull(builder.dataProvider, "dataProvider not be null");
        Preconditions.checkArgument(MapUtils.isNotEmpty(builder.indexingFunctions), "index function not be null");
        this.mainKeyName = builder.mainKeyName;
        this.dataProvider = builder.dataProvider;
        this.incrDisable = builder.incrDisable;
        this.fullPeriod = builder.fullPeriod == 0 ? super.fullPeriod : builder.fullPeriod;
        for (Map.Entry<String, Function<T, Object>> item : builder.indexingFunctions.entrySet()) {
            addIndexFunction(item.getKey(), item.getValue());
        }
        for (Map.Entry<String, Pair<Function<T, List<?>>, Function<Object, Object>>> item :
                builder.listIndexingFunctions
                        .entrySet()) {
            addPairIndexFunction(item.getKey(), item.getValue());
        }
        init();
    }

    @Override
    public void init() {
        this.containerMap = buildContainer();
        onReloadAll();
        // 如果需要定时全量加载
        if (fullPeriod > 0) {
            schedule.scheduleWithFixedDelay(this::onReloadAll, fullPeriod, fullPeriod, TimeUnit.MILLISECONDS);
        }
        started();
    }

    @Override
    protected void loadDataToContainer(Iterable<T> dataList, LoadType loadType) {
        Map<String, CacheContainer<Object, T>> _containerMap = buildContainer();
        Safes.of(dataList).forEach(o -> {
            if (o == null || dataProvider.needRemove(o)) {
                return;
            }
            indexingFunctions.forEach((indexName, function) -> {
                log.debug("onLoad entity: {}. {}", indexName, o.getClass());
                _containerMap.get(indexName).put(function.apply(o), o);

            });
            pairFunctions.forEach((indexName, listFunction) -> {
                log.debug("onLoad entity: {}. {}", indexName, o.getClass());
                for (Object obj : Safes.of(listFunction.getLeft().apply(o))) {
                    _containerMap.get(indexName).put(listFunction.getRight().apply(obj), o);
                }
            });
        });
        this.containerMap = _containerMap;
    }

    @Override
    protected void processRemain() {
        while (cachedKeyQueue.peek() != null) {
            K key = cachedKeyQueue.poll();
            log.info("consume cached key after reloadAll. key : {}", key);
            updateCacheByKey(key, true);
        }
    }

    public void updateCacheByKey(K key) {
        updateCacheByKey(key, false);
    }

    /**
     * @param key
     * @param consumeCacheKeys 是否是消费缓存队列中的key
     */
    private void updateCacheByKey(K key, boolean consumeCacheKeys) {
        if (incrDisable) {
            throw new RuntimeException("incrDisable is true , not support operation");
        }

        if (key == null) {
            return;
        }

        if (reloadAll && !consumeCacheKeys) {
            try {
                cachedKeyQueue.add(key);
                log.info("cache key for reloading all now. key : {}", key);
            } catch (Exception e) {
                log.warn("can't cache key for insufficient capacity. key: {}", key, e);
                throw e;
            }
        }

        try {
            if (!loadingLock.tryLock(1, TimeUnit.SECONDS)) {
                log.warn("long time loading cause i release the thread, key: {},  waiting for retry...", key);
                throw new RuntimeException("I need some help, please retry after some time");
            }

            T t = dataProvider.queryByKey(key);
            final Map<String, CacheContainer<Object, T>> _containerMap = containerMap;
            Function<T, Object> mainFunction = indexingFunctions.get(mainKeyName);
            T old = get(key);
            if (t == null && old == null) {
                return;
            }
            indexingFunctions.forEach((indexName, function) -> {
                //当新对象为Null 要用old 去取 旧的数据
                Iterable<T> iterable = getMultiValue(indexName, function.apply(t == null ? old : t));
                //把跟主key相等的移除掉
                removeKeyIfExist(key, mainFunction,
                        iterable);
                if (t != null && !dataProvider.needRemove(t)) {
                    _containerMap.get(indexName).put(function.apply(t), t);
                }
            });

            pairFunctions.forEach((indexName, pairFunction) -> {
                for (Object obj : Safes.of(pairFunction.getLeft().apply(t == null ? old : t))) {
                    Iterable<T> iterable = getMultiValue(indexName, pairFunction.getRight().apply(obj));
                    //把跟主key相等的移除掉
                    removeKeyIfExist(key, mainFunction, iterable);
                    if (t != null && !dataProvider.needRemove(t)) {
                        _containerMap.get(indexName).put(pairFunction.getRight().apply(obj), t);
                    }
                }
            });
        } catch (InterruptedException e) {
            Thread.interrupted();
            throw new RuntimeException("Thread interrupted, need retry", e);
        } finally {
            if (loadingLock.isHeldByCurrentThread()) {
                loadingLock.unlock();
            }
        }
    }

    private void removeKeyIfExist(K key, Function<T, Object> mainFunction, Iterable<T> iterable) {
        if (!Iterables.isEmpty(iterable)) {
            Iterator<T> ts = iterable.iterator();
            while (ts.hasNext()) {
                T t1 = ts.next();
                if (key.equals(mainFunction.apply(t1))) {
                    ts.remove();
                }
            }
        }
    }

    protected Map<String, CacheContainer<Object, T>> buildContainer() {
        final Map<String, CacheContainer<Object, T>> _containerMap = Maps.newHashMap();
        for (Map.Entry<String, Function<T, Object>> item : this.indexingFunctions.entrySet()) {
            _containerMap.put(item.getKey(), incrDisable ? buildUnThreadSafeContainer() : buildThreadSafeContainer());
        }
        for (Map.Entry<String, Pair<Function<T, List<?>>, Function<Object, Object>>> item : this.pairFunctions
                .entrySet()) {
            _containerMap.put(item.getKey(), incrDisable ? buildUnThreadSafeContainer() : buildThreadSafeContainer());
        }
        return _containerMap;
    }

    private MultiValueContainer<Object, T> buildThreadSafeContainer() {
        return new MultiValueContainer<Object, T>() {
            final Multimap<Object, T> unThreadSafe = Multimaps.synchronizedMultimap(HashMultimap.create());

            @Override
            public T put(Object key, T value) {
                unThreadSafe.put(key, value);
                return value;
            }

            @Override
            public T get(Object key) {
                return Safes.first(unThreadSafe.get(key));
            }

            @Override
            public T remove(Object key) {
                return Safes.first(unThreadSafe.removeAll(key));
            }

            @Override
            public Set<Object> keys() {
                return unThreadSafe.keySet();
            }

            @Override
            public Iterable<T> values() {
                return unThreadSafe.values();
            }

            @Override
            public Iterable<T> getMulti(Object key) {
                return unThreadSafe.get(key);
            }

            @Override
            public Iterable<T> removeMulti(Object key) {
                return unThreadSafe.removeAll(key);
            }

            @Override
            public boolean removeByKeyAndValue(Object key, T value) {
                return unThreadSafe.remove(key, value);
            }

            @Override
            public Iterable<T> replaceValues(Object key, Iterable<? extends T> value) {
                return unThreadSafe.replaceValues(key, value);
            }
        };
    }

    private MultiValueContainer<Object, T> buildUnThreadSafeContainer() {
        return new MultiValueContainer<Object, T>() {
            final Multimap<Object, T> unThreadSafe = HashMultimap.create();

            @Override
            public T put(Object key, T value) {
                unThreadSafe.put(key, value);
                return value;
            }

            @Override
            public T get(Object key) {
                return Safes.first(unThreadSafe.get(key));
            }

            @Override
            public T remove(Object key) {
                return Safes.first(unThreadSafe.removeAll(key));
            }

            @Override
            public Set<Object> keys() {
                return unThreadSafe.keySet();
            }

            @Override
            public Iterable<T> values() {
                return unThreadSafe.values();
            }

            @Override
            public Iterable<T> getMulti(Object key) {
                return unThreadSafe.get(key);
            }

            @Override
            public Iterable<T> removeMulti(Object key) {
                return unThreadSafe.removeAll(key);
            }

            @Override
            public boolean removeByKeyAndValue(Object key, T value) {
                return unThreadSafe.remove(key, value);
            }

            @Override
            public Iterable<T> replaceValues(Object key, Iterable<? extends T> value) {
                return unThreadSafe.replaceValues(key, value);
            }
        };
    }

    private void addIndexFunction(String indexName, Function<T, Object> function) {
        indexingFunctions.put(indexName, function);
    }

    private void addPairIndexFunction(String indexName,
                                      Pair<Function<T, List<?>>, Function<Object, Object>> pairFunction) {
        this.pairFunctions.put(indexName, pairFunction);
    }

    protected CacheContainer<Object, T> getContainer(String indexName) {
        return containerMap.get(indexName);
    }

    @Override
    public T get(String indexName, Object key) {
        return getContainer(indexName).get(key);
    }

    @Override
    public Iterable<T> getListByKeys(String indexName, Set<Object> keys) {
        List<T> list = Lists.newArrayListWithCapacity(keys.size());
        for (Object key : keys) {
            T value = get(indexName, key);
            if (value != null) {
                list.add(value);
            }
        }
        return list;
    }

    public Iterable<T> getMultiValue(String indexName, Object key) {
        MultiValueContainer<Object, T> container = (MultiValueContainer<Object, T>) getContainer(indexName);
        return container.getMulti(key);
    }

    @Override
    public T get(K key) {
        return getContainer(mainKeyName).get(key);
    }

    @Override
    public Iterable<T> getAll() {
        return Lists.newArrayList(getContainer(mainKeyName).values());
    }

    public static class MultiIndexCacheBuilder<K, T> {

        Map<String, Function<T, Object>> indexingFunctions = Maps.newHashMap();

        Map<String, Pair<Function<T, List<?>>, Function<Object, Object>>> listIndexingFunctions = Maps.newHashMap();

        CacheDataProvider<K, T> dataProvider;

        String mainKeyName;

        long fullPeriod;
        /**
         * 是否禁止增量
         */
        boolean incrDisable = true;

        public MultiIndexCacheBuilder<K, T> fullPeriod(long fullPeriod) {
            this.fullPeriod = fullPeriod;
            return this;
        }

        public MultiIndexCacheBuilder<K, T> incrDisable(boolean incrDisable) {
            this.incrDisable = incrDisable;
            return this;
        }

        public MultiIndexCacheBuilder<K, T> addMainIndexFunction(String indexName, Function<T, Object> indexFunction) {
            Preconditions.checkArgument(StringUtils.isEmpty(mainKeyName), "not repeat add main index function");
            this.mainKeyName = indexName;
            return addIndexFunction(indexName, indexFunction);
        }

        public MultiIndexCacheBuilder<K, T> addDataProvider(CacheDataProvider<K, T> dataProvider) {
            Preconditions.checkNotNull(dataProvider, "dataProvider not be null");
            this.dataProvider = dataProvider;
            return this;
        }

        public MultiIndexCacheBuilder<K, T> addDataProvider(String mainIndexName,
                                                            CacheDataProvider<K, T> dataProvider) {
            Preconditions.checkNotNull(dataProvider, "dataProvider not be null");
            this.dataProvider = dataProvider;
            this.mainKeyName = mainIndexName;
            this.addIndexFunction(mainIndexName, dataProvider::getKey);
            return this;
        }

        public MultiIndexCacheBuilder<K, T> addIndexFunction(String indexName, Function<T, Object> indexFunction) {
            Preconditions.checkNotNull(indexName, "index name not be empty");
            Preconditions.checkNotNull(indexFunction, "index function not be null");
            this.indexingFunctions.put(indexName, indexFunction);
            return this;
        }

        public MultiIndexCacheBuilder<K, T> addIndexFunction(String indexName, Function<T, List<?>> listFunction,
                                                             Function<Object, Object> indexFunction) {
            Preconditions.checkNotNull(indexName, "index name not be empty");
            Preconditions.checkNotNull(indexFunction, "index function not be null");
            Preconditions.checkNotNull(listFunction, "list function not be null");
            this.listIndexingFunctions.put(indexName, Pair.of(listFunction, indexFunction));
            return this;
        }

        public LocalMultiIndexCache<K, T> create() {
            return new LocalMultiIndexCache<>(this);
        }
    }
}
