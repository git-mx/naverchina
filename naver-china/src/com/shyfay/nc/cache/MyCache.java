package com.shyfay.nc.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyCache {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static Map<String, Object> cacheMap = new HashMap<>();
    public Object processData(String key) {
        Object value = null;
        try {
            rwLock.readLock().lock();
            value = cacheMap.get(key);
            if (null == value) {
                rwLock.readLock().unlock();
                try {
                    rwLock.writeLock().lock();
                    if (null == value) {
                        value = key + ":data from database";
                        cacheMap.put(key, value);
                    }
                    return value;
                } finally {
                    rwLock.writeLock().unlock();
                    rwLock.readLock().lock();
                }
            }else{
                return value;
            }
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public int getCacheSize(){
        return this.cacheMap.size();
    }
}
