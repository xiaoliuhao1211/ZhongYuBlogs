package com.lh.cahe.api;

import java.lang.reflect.Method;

/**
 * 拦截器上下文接口
 *
 * （1）get
 * （2）put
 * （3）remove
 * （4）expire
 * （5）evict
 * @param <K> key
 * @param <V> value
 */

public interface ICacheInterceptorContext<K,V> {
    /**
     * 缓存信息
     * @return 缓存信息
     */
    ICache<K,V> getCache();

    /**
     * 执行的方法信息
     * @return 方法
     */
    Method getMethod();

    /**
     * 执行的参数
     * @return 参数
     */
    Object[] getParams();

    /**
     * 方法执行的结果
     * @return 结果
     */
    Object getResult();

    /**
     * 开始时间
     * @return 时间
     */
    long startMills();

    /**
     * 结束时间
     * @return 时间
     */
    long endMills();
}
