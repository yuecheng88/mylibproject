package com.xiao.algorithm;

import java.util.LinkedList;

/**
 * java 单链表
 * 头指针 ：指向头结点（没有头结点指向首元结点
 * (我觉得不用头结点）
 * https://www.cnblogs.com/sang-bit/p/11609824.html
 *
 */
public class MyLinkList<T> {

    //private Nodes<T> first;
    private Nodes<T> head;
    int size;

    public void init(){
        size = 0;
        //first = new Nodes<T>(null,null);
        head = new Nodes<T>(null,null);
    }

    public static void main(String[] args) {

    }

    public boolean isEmpty(){
        return size == 0 ;
    }

    public T get(int i){
        return getNode(i).data;
    }
    public void add(T data) {
        Nodes<T> node = new Nodes<>(data,null);
        getNode(size ).next = node;
        size++;

    }
    public T remove(int n){
        if(n <=0 || n > size -1)
            throw new IllegalArgumentException(("Illegal index:" + n));
        Nodes<T> pre = getNode(n-1) ;
        Nodes<T> node = pre.next;
        if(n == size ){
            pre.next = null;
        }else{
            pre.next = node.next;
        }
        size --;
        return pre.data;
    }
    public void insert(int i,T data){
        Nodes<T> pre = getNode(i-1) ;
        Nodes<T> node = new Nodes<>(data,pre.next);
        pre.next = node;
        size ++;
    }

    private Nodes<T> getNode(int n){
        Nodes<T> node = head;
        for(int i= 0;i<n; i++){
            node = node.next;
        }
        return node;
    }
}


class Nodes<T>{
    T data ;
    Nodes<T> next;
    public Nodes(T data ,Nodes<T> next){
        this.data = data;
        this.next = next;
    }

}