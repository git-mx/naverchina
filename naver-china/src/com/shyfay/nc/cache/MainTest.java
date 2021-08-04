package com.shyfay.nc.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class MainTest {

    public static void main(String[] args) throws Exception {
        MyCache cache = new MyCache();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        List<Future<Object>> futures = new ArrayList<>(5);
        for(int i=0; i<5; i++){
            String key = threadLocalRandom.nextInt(4) + "";
            futures.add(executorService.submit(new MyTask(cache, key)));
        }
        Future<Object> future;
        Integer i;
        Iterator<Future<Object>> iterator = futures.iterator();
        while(iterator.hasNext()){
            future = iterator.next();
            if(future.isDone() && !future.isCancelled()){
                System.out.println(future.get());
                iterator.remove();
            }else{
                TimeUnit.MILLISECONDS.sleep(1);
            }
        }
    }
}
