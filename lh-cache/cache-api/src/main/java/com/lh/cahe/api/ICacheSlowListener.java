package com.lh.cahe.api;

public interface ICacheSlowListener {
    /**
     * 监听
     * @param context 上下文
     */
    void listen(final ICacheSlowListenerContext context);

    /**
     * 慢日志的阈值
     * @return 慢日志的阈值
     */
    long slowerThanMills();
}
