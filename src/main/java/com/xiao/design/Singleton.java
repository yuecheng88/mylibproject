package com.xiao.design;

/**
 * 设计模式--单例
 */
public class Singleton {
    /*私有构造方法  */
    private Singleton(){}

    //饿汉式(线程安全，调用效率高，但是不能延时加载)
    private static Singleton instance = new Singleton();
    public static Singleton getHurInstance(){
        return instance;
    }

    /**
     * 静态内部类实现模式（线程安全，调用效率高，可以延时加载）
     *
     */
    private static class SingletonInner{
        private static final Singleton singleton= new Singleton();
    }

    public static Singleton getInstance(){
        return  SingletonInner.singleton;
    }
}
