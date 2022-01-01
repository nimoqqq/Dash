package com.chuf.springcachedemo.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.ConcurrentStatsCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 设置最大容量大小
                .maximumSize(500)
                // 设置软引用避免因为缓存的引入导致OOM，如果容量预估合理的话推荐不用软引用
                .softValues()
                // 设置缓存移除监听器
                .removalListener((k, v, c) -> LOGGER.info("rights cache remove key:{}, value:{}, cause:{}", k, v, c))
                // 设置缓存统计器
                .recordStats(ConcurrentStatsCounter::new)
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterAccess(600, TimeUnit.SECONDS));
        return cacheManager;
    }
}
