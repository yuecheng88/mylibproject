package com.xiao.data;

import java.util.Queue;

public class MyQueue<E> {



    private transient Entry<E> front;
    private transient Entry<E> rear;
    private transient int size;


    public MyQueue(){
        this.front = this.rear = null;
    }
    public boolean isEmpty(){
        return size == 0? true:false;
    }

    public boolean add(E e){
        Entry<E> newData = new Entry<E>(e,null);
        if(rear == null){
            this.rear = newData;
            this.front = this.rear;
        }else {
            this.rear.next = newData;
            this.rear = newData;
        }
        size++;
        return true;
    }


    public E remove(){
        if(front == null){
            return null;
        }
        Entry<E> data = front;
        if (data.next == null){
            this.front = this.rear = null;
        }else {
            this.front = data.next;
        }
        this.size --;
        return data.element;
    }

    private static class Entry<E>{
        E element;
        Entry<E> next ;
        public Entry(){

        }
        public Entry(E element,Entry<E> next){
            this.element = element;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        MyQueue<String> q =new MyQueue<>();
        System.out.println(q.remove());
        q.add("aaaa");
        q.add("bbb");
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
    }

}
