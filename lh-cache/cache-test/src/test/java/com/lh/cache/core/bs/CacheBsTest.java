package com.lh.cache.core.bs;


import com.lh.cache.core.listener.MyRemoveListener;
import com.lh.cache.core.listener.MySlowListener;
import com.lh.cache.core.load.MyCacheLoad;
import com.lh.cache.core.support.map.Maps;
import com.lh.cache.core.util.CacheEvictUtil;
import com.lh.cache.core.util.CacheLoadUtil;
import com.lh.cache.core.util.CachePersistUtil;
import com.lh.cahe.api.ICache;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 缓存引导类测试
 * @author binbin.hou
 * @since 0.0.2
 */
public class CacheBsTest {

    /**
     * 大小指定测试
     * @since 0.0.2
     */
    @Test
    public void helloTest() {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .setSize(1)
                .build();

        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.put("5","5");
        cache.put("hello,lsx","hello,lsx");

        Assert.assertEquals(1, cache.size());
        System.out.println(cache.keySet());
    }

    /**
     * 配置指定测试
     * @since 0.0.2
     */
    @Test
    public void configTest() {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .setMap(Maps.<String, String>hashMap())
                .setEvict(CacheEvictUtil.<String, String>fifo())
                .setSize(2)
                .build();

        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");

        Assert.assertEquals(2, cache.size());
        System.out.println(cache.keySet());
    }

    /**
     * 过期测试
     * @since 0.0.3
     */
    @Test
    public void expireTest() throws InterruptedException {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .setSize(3)
                .build();

        cache.put("1", "1");
        cache.put("2", "2");

        cache.expire("1", 40);
        Assert.assertEquals(2, cache.size());

        TimeUnit.MILLISECONDS.sleep(50);
        Assert.assertEquals(1, cache.size());
        System.out.println(cache.keySet());
    }

    /**
     * 缓存删除监听器
     * @since 0.0.6
     */
    @Test
    public void cacheRemoveListenerTest() {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .setSize(1)
                .addRemoveListener(new MyRemoveListener<String, String>())
                .build();

        cache.put("1", "1");
        cache.put("2", "2");
    }

    /**
     * 加载接口测试
     * @since 0.0.7
     */
    @Test
    public void loadTest() {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .setLoad(new MyCacheLoad())
                .build();

        Assert.assertEquals(2, cache.size());
    }

    /**
     * 持久化接口测试
     * @since 0.0.7
     */
    @Test
    public void persistRdbTest() throws InterruptedException {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .setLoad(new MyCacheLoad())
                .setPersist(CachePersistUtil.<String, String>dbJson("1.rdb"))
                .build();

        Assert.assertEquals(2, cache.size());
        TimeUnit.MINUTES.sleep(30);
    }

    /**
     * 加载接口测试
     * @since 0.0.8
     */
    @Test
    public void loadDbJsonTest() {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .setLoad(CacheLoadUtil.<String, String>dbJson("1.rdb"))
                .build();

        Assert.assertEquals(2, cache.size());
    }

    /**
     * 慢日志接口测试
     * @since 0.0.9
     */
    @Test
    public void slowLogTest() {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .addSlowListener(new MySlowListener())
                .build();

        cache.put("1", "2");
        cache.get("1");
    }


    /**
     * 持久化 AOF 接口测试
     * @since 0.0.10
     */
    @Test
    public void persistAofTest() throws InterruptedException {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .setPersist(CachePersistUtil.<String, String>aof("1.aof"))
                .build();

        cache.put("1", "1");
        cache.expire("1", 10);
        cache.remove("2");

        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * 加载 AOF 接口测试
     * @since 0.0.10
     */
    @Test
    public void loadAofTest() throws InterruptedException {
        ICache<String, String> cache = CacheBs.<String, String>newInstance()
                .setLoad(CacheLoadUtil.<String, String>aof("1.aof"))
                .build();

       // Assert.assertEquals(1, cache.size());
        System.out.println(cache.keySet());
    }



}
