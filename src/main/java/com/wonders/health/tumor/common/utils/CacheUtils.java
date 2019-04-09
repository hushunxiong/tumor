/**
 *
 */
package com.wonders.health.tumor.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Cache工具类
 *
 * @version 2013-5-29
 */
public class CacheUtils {

    private static CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);

    public static final String SYS_CACHE = "sysCache";
    public static final String DICT_CACHE = "dictCache";

    private static final String SESSION_CACHE = "sessionsCache";

    public static void putSessionCache(String key, Object value) {
        Subject subject = SecurityUtils.getSubject();
        String sessionId = subject.getSession().getId().toString();
        put(SESSION_CACHE, sessionId + key, value);
    }

    public static Object getSessionCache(String key) {
        Subject subject = SecurityUtils.getSubject();
        String sessionId = subject.getSession().getId().toString();
        return get(SESSION_CACHE, sessionId + key);
    }

    public static void clearSessionCache(String key) {
        Subject subject = SecurityUtils.getSubject();
        String sessionId = subject.getSession().getId().toString();
        remove(SESSION_CACHE, sessionId + key);
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * 写入缓存
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        getCache(cacheName).put(element);
    }

    /**
     * 从缓存中移除
     *
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 移除指定缓存中所有缓存
     * @param cacheName
     */
    public static void removeCache(String cacheName){
        getCache(cacheName).removeAll();
    }

    /**
     * 获得一个Cache，没有则创建一个。
     *
     * @param cacheName
     * @return
     */
    private static Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
            //cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

}
