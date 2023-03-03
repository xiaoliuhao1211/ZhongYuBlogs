package com.lh.cache.core.listener;


import com.lh.cahe.api.ICacheSlowListener;
import com.lh.cahe.api.ICacheSlowListenerContext;

/**
 * @author binbin.hou
 * @since 0.0.9
 */
public class MySlowListener implements ICacheSlowListener {

    @Override
    public void listen(ICacheSlowListenerContext context) {
        System.out.println("【慢日志】name: " + context.getMethodName());
    }

    @Override
    public long slowerThanMills() {
        return 0;
    }

}
