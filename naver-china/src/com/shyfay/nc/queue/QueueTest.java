package com.shyfay.nc.queue;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class QueueTest {
    public static void main(String[] args) throws Exception {
        MyQueue<String> myQueue = new MyQueue<>(10);
        for(int i=0; i<15; i++){
            new Thread(() -> {
                try {
                    ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                    myQueue.push(String.valueOf(threadLocalRandom.nextInt(1000)));
                } catch (InterruptedException e) {
                    System.out.println("error");
                }
            }).start();
        }
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(myQueue);
        while(true){
            System.out.println(myQueue.peek());
            System.out.println(myQueue.pop());
            System.out.println("---------------------------------");
        }
    }
}
