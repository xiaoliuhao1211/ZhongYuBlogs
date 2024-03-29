package com.lh.cache.core.support.evict;


import com.lh.cache.core.model.CacheEntry;
import com.lh.cahe.api.ICache;
import com.lh.cahe.api.ICacheEvictContext;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 丢弃策略-先进先出
 */
public class CacheEvictFifo<K,V> extends AbstractCacheEvict<K,V> {

    /**
     * queue 信息
     * @since 0.0.2
     */
    private final Queue<K> queue = new LinkedList<>();

    @Override
    public CacheEntry<K,V> doEvict(ICacheEvictContext<K, V> context) {
        CacheEntry<K,V> result = null;

        final ICache<K,V> cache = context.getCache();
        // 超过限制，执行移除
        if(cache.size() >= context.getSize()) {
            K evictKey = queue.remove();
            // 移除最开始的元素
            V evictValue = cache.remove(evictKey);
            result = new CacheEntry<>(evictKey, evictValue);
        }

        // 将新加的元素放入队尾
        final K key = context.getKey();
        queue.add(key);

        return result;
    }


}
