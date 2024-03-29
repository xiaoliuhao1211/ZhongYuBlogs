package com.lh.cache.core.util;


import com.lh.cache.core.support.persist.CachePersistAof;
import com.lh.cache.core.support.persist.CachePersistDbJson;
import com.lh.cache.core.support.persist.CachePersistNone;
import com.lh.cahe.api.ICachePersist;

/**
 * 缓存持久化工具类
 * @author binbin.hou
 * @since 0.0.8
 */
public final class CachePersistUtil {

    private CachePersistUtil(){}

    /**
     * 无操作
     * @param <K> key
     * @param <V> value
     * @return 结果
     * @since 0.0.8
     */
    public static <K,V> ICachePersist<K,V> none() {
        return new CachePersistNone<>();
    }

    /**
     * DB json 操作
     * @param <K> key
     * @param <V> value
     * @param path 文件路径
     * @return 结果
     * @since 0.0.8
     */
    public static <K,V> ICachePersist<K,V> dbJson(final String path) {
        return new CachePersistDbJson<>(path);
    }

    /**
     * AOF 持久化
     * @param <K> key
     * @param <V> value
     * @param path 文件路径
     * @return 结果
     * @since 0.0.10
     */
    public static <K,V> ICachePersist<K,V> aof(final String path) {
        return new CachePersistAof<>(path);
    }

}
