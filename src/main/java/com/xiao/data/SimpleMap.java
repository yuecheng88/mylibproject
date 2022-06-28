package com.xiao.data;

/**
 * map 简单实现
 *
 */
public class SimpleMap {

    Entry []array ;
    int size ;

    public SimpleMap(){
        this(10);
    }
    public SimpleMap(int capacity) {
        this.size = capacity;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    public Entry[] expandLength(Entry[] array){
        Entry[] newArray = new Entry[array.length * 2 + 1];
        for(int i = 0 ;i < size;i++){
            newArray[i] = array[i] ;
        }
        return newArray;
    }

    public boolean put(Object key,Object value){
        if (size == array.length) {
            array = expandLength(array) ;
        }
        for(int i=0;i<size;i++){
            if(array[i].key.equals(key)){
                array[i].value = value;
                return true;
            }
        }
        array[size] = new Entry(key,value) ;
        size ++;
        return true;
    }

    public Object get(Object key){
        if (size != 0){
            for (int i= 0 ;i<size; i++){
                if (array[i].key.equals(key)) {
                    return array[i].value;
                }
            }
        }
        return null;
    }

    public boolean containsKey(Object key){
        for(int i=0;i<size;i++){
            if(array[i].key.equals(key)){
                return true;
            }
        }
        return false;
    }
    public void clear(){
        array = new Entry[0];
        size = 0 ;
    }

    class Entry {
        Object key;
        Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
