package com.xiao.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Queue;
import java.util.Set;

/**
 *https://blog.csdn.net/qq_35006660/article/details/105641595
 */
public class MyHshMap<K,V> implements IMap<K,V> {
    /**
     * 默认容量为16
     */
    private static final int DEFAULT_CAPACITY = 1<<4;

    /**
     * 底层存储
     */
    Node<K,V>[] table = new Node[DEFAULT_CAPACITY] ;

    private int size;
    Set<K> keySet;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Accessors(chain = true)
    static class Node<K,V> implements IMap.Entry<K,V>{
        /**
         * hash值
         */
        int hash;
        /**
         * key
         */
        K key;
        /**
         * value
         */
        V value;
        /**
         * 下一个节点指针
         */
        Node<K,V> next;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ;
    }

    @Override
    public V get( Object key) {
        int hashValue = hash(key);
        int i = indexFor(hashValue,table.length) ;
        for (Node<K,V> node = table[i];node != null;node = node.next){
            if (hashValue == node.hash && node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    public int hash(Object key){
        return key.hashCode();
    }

    /**
     * 获取插入的位置
     * @param hashValue
     * @param length
     * @return
     */
    private int indexFor(int hashValue,int length){
        return hashValue %length;
    }

    @Override
    public V put(K key, V value) {
        int hashValue = hash(key);
        int i = indexFor(hashValue,table.length) ;
        for(Node<K,V> node = table[i];node != null;node = node.next){
            if(hashValue == node.hash && node.key.equals(key)){
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }

        addEntry(key,value,hashValue,i);
        return null;
    }
    private void addEntry(K key, V value,int hashValue,int index){

        if(++size == table.length){
            Node[] newTable = new Node[table.length << 1];
            System.arraycopy(table,0,newTable,0,table.length);
            table = newTable;
        }
        Node<K,V> node = table[index] ;
       table[index] = new Node(hashValue,key,value,node) ;

    }


    public static void main(String[] args) {
        IMap<String,String> m = new MyHshMap<String,String>();
        m.put("aa","aaaa");
        m.put("bb","bbbb");


        String s = m.get("aa") ;

        System.out.println(m.get("aa"));
        System.out.println(m.get("bb"));

        
    }
}
