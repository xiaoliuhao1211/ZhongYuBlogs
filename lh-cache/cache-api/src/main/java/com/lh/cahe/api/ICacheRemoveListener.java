package com.lh.cahe.api;

public interface ICacheRemoveListener<K,V> {
    /**
     * 监听
     * @param context 上下文
     */
    void listen(final ICacheRemoveListenerContext<K,V> context);
}
