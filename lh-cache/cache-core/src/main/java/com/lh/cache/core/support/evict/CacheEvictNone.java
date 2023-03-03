package com.lh.cache.core.support.evict;


import com.lh.cahe.api.ICacheEntry;
import com.lh.cahe.api.ICacheEvictContext;

/**
 * 丢弃策略
 * @author binbin.hou
 */
public class CacheEvictNone<K,V> extends AbstractCacheEvict<K,V> {

    @Override
    protected ICacheEntry<K, V> doEvict(ICacheEvictContext<K, V> context) {
        return null;
    }

}
