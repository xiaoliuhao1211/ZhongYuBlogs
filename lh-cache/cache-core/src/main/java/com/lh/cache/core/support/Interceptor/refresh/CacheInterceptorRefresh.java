package com.lh.cache.core.support.Interceptor.refresh;


import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.lh.cahe.api.ICache;
import com.lh.cahe.api.ICacheInterceptor;
import com.lh.cahe.api.ICacheInterceptorContext;

/**
 * 刷新
 */
public class CacheInterceptorRefresh<K,V> implements ICacheInterceptor<K, V> {

    private static final Log log = LogFactory.getLog(CacheInterceptorRefresh.class) ;

    @Override
    public void before(ICacheInterceptorContext<K,V> context) {
        log.debug("Refresh start");
        final ICache<K,V> cache = context.getCache();
        cache.getExpire().refreshExpire(cache.keySet());
    }

    @Override
    public void after(ICacheInterceptorContext<K,V> context) {
    }

}
