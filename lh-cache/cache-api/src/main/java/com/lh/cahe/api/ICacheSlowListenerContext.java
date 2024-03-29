package com.lh.cahe.api;

/**
 * 慢日志监听器上下文
 *
 */
public interface ICacheSlowListenerContext {

    /**
     * 方法名称
     * @return 方法名称
     */
    String getMethodName();

    /**
     * 参数信息
     * @return 参数列表
     */
    Object[] getParams();

    /**
     * 方法结果
     * @return 方法结果
     */
    Object getResult();

    /**
     * 开始时间
     * @return 时间
     */
    long startTimeMills();

    /**
     * 结束时间
     * @return 结束时间
     */
    long endTimeMills();

    /**
     * 消耗时间
     * @return 耗时
     */
    long costTimeMills();

}
