package com.xiao.algorithm;

/**
 * 双向链表
 * https://www.cnblogs.com/sang-bit/p/11614232.html
 * https://www.zhihu.com/question/34814570(常见面试算法）
 */
public class DoubleLinkList<T> {

    Node<T> head ;
    Node<T> end;
    int size;

    private void init() {
        end = new Node<T>(null,null,null) ;
        head = new Node<T>(null,null,null) ;
        end.prev = head ;
        end.next = head;
        size = 0;
    }

    public int length() {
        return size;
    }


    private class Node<T>{
        T data;
        Node<T> prev;
        Node<T> next ;
        public Node(T data ,Node<T> prev,Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}


