package com.xiao.util;

/**
 * 分布式锁 redis
 * https://segmentfault.com/a/1190000022077372(分布式锁常见问题）
 * https://www.jianshu.com/p/3738d6c702a4（超时处理）
 * https://www.cnblogs.com/wangzaiplus/p/10864135.html（超时处理 --完整代码）
 */
public class DistributedLock {


}

class ExpandLockTime implements Runnable{


    private static final int redis_expire_success = 1;

    private String field ;
    private String key;
    private String value;
    private int lockTime;

    private volatile Boolean signal;

    public ExpandLockTime(String field, String key, String value, int lockTime) {
        this.field = field;
        this.key = key;
        this.value = value;
        this.lockTime = lockTime;
        this.signal =  true;
    }

    void stop(){
        this.signal = false;
    }

    public static void main(String[] args) {
        //在获取锁之前添加如下代码块
        ExpandLockTime lock = new ExpandLockTime(null,null,null,1) ;
        Thread servivalThread = new Thread(lock) ;
        servivalThread.setDaemon(true);
        servivalThread.start();
    }
    @Override
    public void run() {
        int waitTime = lockTime * 1000 *2/3 ;
        while (signal){
            try {
                Thread.sleep(waitTime);
                if(expandLockTime(field,key,value,lockTime)) {
                    System.out.println("");
                    //logger.info("expandLockTime 成功，本次等待{}ms，将重置锁超时时间重置为{}s,其中field为{},key为{}", waitTime, lockTime, field, key);

                }else {
                    //logger.info("expandLockTime 失败，将导致SurvivalClamConsumer中断");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                //logger.info("SurvivalClamProcessor 处理线程被强制中断");
            }
        }
    }

    /**
     * 延长锁超时的脚本语句和释放锁的Lua脚本类似
     * @param field
     * @param key
     * @param value
     * @param lockTime
     * @return
     */
    private boolean expandLockTime(String field,String key,String value,int lockTime) {
        //String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('expire', KEYS[1],ARGV[2]) else return '0' end";
        return true;
    }
}
