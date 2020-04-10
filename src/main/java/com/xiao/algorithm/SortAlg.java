package com.xiao.algorithm;

/**
 * 排序算法
 *
 */
public class SortAlg {


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

    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int []merge = new int[A.length + B.length] ;
        int aindex = 0 ,bindex = 0,mindex = 0;
        while (aindex < A.length && bindex < B.length) {
            if(A[aindex] > B[bindex]) {
                merge[mindex++] = B[bindex++] ;
            }else{
                merge[mindex++] =A[aindex++] ;
            }
        }
        while (aindex < A.length){
            merge[mindex++] =A[aindex++] ;
        }
        while (bindex < B.length) {
            merge[mindex++] = B[bindex++] ;
        }
        return merge;
    }


}
