package com.xiao.algorithm;

/**
 * 面试常见算法
 */
public class Interview {


    /**
     * 斐波那契数列  1 1 2 3 5 8 14  22  36  58
     * https://www.cnblogs.com/JackPn/p/9383450.html
     * f(n) = f(n-1) + f(n-2)
     * 递归调用会出现 栈溢出
     * @param num
     */
    public int fibonnacci(int num){
        int front = 1,after = 1, sum = 1;
        for(int i=3; i <=num;i++) {
            sum = front + after;
            front = after;
            after = sum ;
        }
        return num == 0 ?  0: sum ;
    }


    /**
     * 二分查找算法
     * @param a  查找数组必须有序
     * @param key  查询的值
     * @return  查询到的下标
     */
    public int binarySearch(int a[], int key){
        if(a == null)
            return -1;
        int low = 0;
        int high = a.length -1;
        int middle = 0;
        if(key < a[low] || key > a[high] || low > high)
            return -1;
        while(low <= high) {
            middle = (low + high) /2 ;
            if(a[middle] > key){
                high = middle -1;
            }else if(a[middle] < key){
                low = middle + 1;
            }else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
     *   （备注要求：必须在原数组上操作）
     * @param nums
     */
    public void moveZeroes(int [] nums){
        int k = 0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i] !=  0) {
                if (i != k) {
                    swap(nums, i, k);
                    k++;
                    //伪代码 swap(nums[K==],nums[i]);
                }
                else
                    k++;
            }
        }
    }

    private void swap(int []num ,int k,int l){
        int temp = num[k] ;
        num[k] = num[l] ;
        num[l] = temp ;
    }
}
