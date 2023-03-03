package com.lh.cache.core.listener;


import com.lh.cahe.api.ICacheRemoveListener;
import com.lh.cahe.api.ICacheRemoveListenerContext;

/**
 * @author binbin.hou
 * @since 0.0.6
 */
public class MyRemoveListener<K,V> implements ICacheRemoveListener<K,V> {

    @Override
    public void listen(ICacheRemoveListenerContext<K, V> context) {
        System.out.println("【删除提示】可恶，我竟然被删除了！" + context.getKey());
    }

}
