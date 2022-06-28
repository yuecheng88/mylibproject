import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Test {

    /**
     * list 多线程会出现的问题：
     *      1.下标越界    2. size 或数组大小不对
     * HashMap 多线程
     *      1. 死锁  2. 数据丢失   4. size大小不对
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
//        * 500 单线程   调用prepay 75376 ms
//                * 500 5线程             15816 ms
//                * 500 10线程            8219 ms
//                * 500 20线程            4496 ms
//                * 500 50线程            4813 ms
           //int [] a = {0,5,8,10,24,25,26,30,32,33,36,50,68,88,98};
        int [] a = {0,2,5,7,8,11} ;

//        System.out.println(binarySearch(a,6,0,a.length-1));
//        String s = "fsfsfsfsfsf" ;
//        Test test = JSON.parseObject(s,Test.class) ;

        System.out.println(4^9^8^8^15^9^4);

        System.out.println(4&9&8&8&15);
    }
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public int binarySearch(int[] nums, int target) {

        return -1;
    }
    public static int binarySearch(int[] nums, int target,int left,int righ) {
        int center = (left + righ)/2;
        int tem = nums[center];
        if(center == left) {
            if(tem == target)
                return center;
            if(nums[righ] == target)
                return righ;
            return -1;
        }
        if(nums[center] == target) {
            return center;
        }if(nums[center] > target){
            return  binarySearch(nums,target,left,center-1) ;
        }else {
            return binarySearch(nums,target,center+1,righ) ;
        }
    }

}


class AA implements Runnable {
    private  int t ;
    public  AA(int t) {
        this.t = t;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(t);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
