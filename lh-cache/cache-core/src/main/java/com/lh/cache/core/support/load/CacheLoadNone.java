package com.lh.cache.core.support.load;

import com.lh.cahe.api.ICache;
import com.lh.cahe.api.ICacheLoad;

public class CacheLoadNone<K,V> implements ICacheLoad<K,V> {
    @Override
    public void load(ICache<K, V> cache) {

    }
}
