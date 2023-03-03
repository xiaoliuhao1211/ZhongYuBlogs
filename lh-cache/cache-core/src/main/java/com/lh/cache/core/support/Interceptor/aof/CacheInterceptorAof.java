package com.lh.cache.core.support.Interceptor.aof;

import com.alibaba.fastjson.JSON;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.lh.cache.core.model.PersistAofEntry;
import com.lh.cache.core.support.persist.CachePersistAof;
import com.lh.cahe.api.ICache;
import com.lh.cahe.api.ICacheInterceptor;
import com.lh.cahe.api.ICacheInterceptorContext;
import com.lh.cahe.api.ICachePersist;

public class CacheInterceptorAof<K,V> implements ICacheInterceptor<K,V> {

    private static final Log log = LogFactory.getLog(CacheInterceptorAof.class) ;

    @Override
    public void before(ICacheInterceptorContext<K, V> context) {

    }

    @Override
    public void after(ICacheInterceptorContext<K, V> context) {
        ICache<K,V> cache = context.getCache() ;
        ICachePersist<K,V> persist = cache.getPersist() ;

        if(persist instanceof CachePersistAof){
            CachePersistAof<K,V> cachePersistAof = (CachePersistAof<K, V>) persist;
            String methodName = context.getMethod().getName();
            PersistAofEntry aofEntry = PersistAofEntry.newInstance() ;
            aofEntry.setMethodName(methodName);
            aofEntry.setParams(context.getParams());

            String json = JSON.toJSONString(aofEntry) ;

            // 直接持久化
            log.debug("AOF 开始追加文件内容：{}", json);
            cachePersistAof.append(json);
            log.debug("AOF 完成追加文件内容：{}", json);

        }


    }
}
