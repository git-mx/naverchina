package com.shyfay.nc.cache;

import java.util.concurrent.Callable;

public class MyTask implements Callable<Object> {
    private MyCache myCache;
    private String key;
    MyTask(){}
    MyTask(MyCache myCache, String key){
        this.myCache = myCache;
        this.key = key;
    }

    @Override
    public Object call() throws Exception {
        return this.myCache.processData(this.key);
    }
}
