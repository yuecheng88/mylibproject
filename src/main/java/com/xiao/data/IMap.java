package com.xiao.data;

public interface IMap<K,V> {
    /**
     * 返回map集合大小
     * @return
     */
    int size();

    /**
     * 判断map是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 根据key 获取value
     * @param key
     * @return
     */
    V get(Object key);

    /**
     * 添加元素
     * @param key
     * @param value
     * @return
     */
    V put(K key,V value);

    interface Entry<K,V>{
        /**
         * 获取key
         * @return 建
         */
        K getKey();

        /**
         * 获取值
         * @return 值
         */
        V getValue();
    }
}
