package com.shyfay.nc.queue;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class StackTest {
    public static void main(String[] args) throws Exception {
        MyStack<String> myStack = new MyStack<>();
        for(int i=0; i<10; i++){
            new Thread(() -> {
                try {
                    ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
                    myStack.push(String.valueOf(threadLocalRandom.nextInt(1000)));
                } catch (InterruptedException e) {
                    System.out.println("error");
                }
            }).start();
        }
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(myStack);
        while(!myStack.isEmpty()){
            System.out.println(myStack.peek());
            System.out.println(myStack.pop());
            System.out.println("---------------------------------");
        }
    }

}
