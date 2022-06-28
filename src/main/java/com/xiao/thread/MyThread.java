package com.xiao.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多线程
 */
public class MyThread extends  Thread{

    public static void main(String[] args) {

        System.out.println(1620 + 15000 + 600 +  1755+702);
        System.out.println(50000 * 0.05);
        //方式一集成Thread
        MyThread th1 = new MyThread();
        th1.start();

        //方式二 实现runable
        Thread th2 = new Thread(new MyRunable()) ;
        th2.start();

        //线程池实现
        //创建固定线程池，规定最大线程数量，超过最大后来进入的任务放入等待队列
        ExecutorService es = Executors.newFixedThreadPool(2) ;
        for(int i=0;i<5;i++) {
            es.execute(new MyRunable());
        }

    }
    @Override
    public void run() {
        //..........
        System.out.println("mythread run");
    }
}

class  MyRunable implements  Runnable {

    @Override
    public void run() {
        //..........
        System.out.println("MyRunable run");
    }
}

class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }
}