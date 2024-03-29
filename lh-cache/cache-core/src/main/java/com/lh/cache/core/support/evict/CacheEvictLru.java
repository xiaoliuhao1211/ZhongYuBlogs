package com.lh.cache.core.support.evict;


import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.lh.cache.core.model.CacheEntry;
import com.lh.cahe.api.ICache;
import com.lh.cahe.api.ICacheEntry;
import com.lh.cahe.api.ICacheEvictContext;

import java.util.LinkedList;
import java.util.List;

/**
 * 丢弃策略-LRU 最近最少使用
 */
public class CacheEvictLru<K,V> extends AbstractCacheEvict<K,V> {

    private static final Log log = LogFactory.getLog(CacheEvictLru.class);

    /**
     * list 信息
     */
    private final List<K> list = new LinkedList<>();

    @Override
    protected ICacheEntry<K, V> doEvict(ICacheEvictContext<K, V> context) {
        ICacheEntry<K, V> result = null;
        final ICache<K,V> cache = context.getCache();
        // 超过限制，移除队尾的元素
        if(cache.size() >= context.getSize()) {
            K evictKey = list.get(list.size()-1);
            V evictValue = cache.remove(evictKey);
            result = new CacheEntry<>(evictKey, evictValue);
        }

        return result;
    }


    /**
     * 放入元素
     * （1）删除已经存在的
     * （2）新元素放到元素头部
     *
     * @param key 元素
     */
    @Override
    public void updateKey(final K key) {
        this.list.remove(key);
        this.list.add(0, key);
    }

    /**
     * 移除元素
     * @param key 元素
     */
    @Override
    public void removeKey(final K key) {
        this.list.remove(key);
    }

}
