package com.shyfay.nc.queue;

//利用数组实现一个栈
public class MyStack<E> {
    private Object[] items;
    private int top ;
    private int size;

    public MyStack(){
        this.size = 10;
        items = new Object[this.size];
        top = -1;
    }
    public MyStack(int size){
        this.size = size;
        items = new Object[this.size];
        top = -1;
    }
    public void push(E e) throws InterruptedException {
        if(top == size - 1){
            throw new IndexOutOfBoundsException();
        }
        items[++top] = e;
    }

    public E pop(){
        if (top == 0 ){
            top--;
            return  null;
        }else{
            return (E)items[--top];
        }
    }
    public E peek(){
        return (E)items[top];
    }

    public boolean isEmpty(){
        return top == -1;
    }
}
