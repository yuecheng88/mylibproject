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

    /**
     * jdk 源码 字符串转int
     * @param s
     * @param radix
     * @return
     * @throws NumberFormatException
     */

    public static int parseInt(String s, int radix)
            throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " less than Character.MIN_RADIX");
        }

        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix +
                    " greater than Character.MAX_RADIX");
        }

        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;
        int li2 = Integer.MIN_VALUE;
        int multmin;
        int digit;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+')
                    throw new NumberFormatException(s);

                if (len == 1) // Cannot have lone "+" or "-"
                    throw new NumberFormatException(s);
                i++;
            }
            multmin = limit / radix;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++),radix);
                if (digit < 0) {
                    throw new NumberFormatException(s);
                }
                if (result < multmin) {
                    throw new NumberFormatException(s);
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException(s);
                }
                result -= digit;
            }
        } else {
            throw new NumberFormatException(s);
        }
        return negative ? result : -result;
    }


    /**
     * 回文判读（中间对称）
     * @param s
     * @return
     */
    public static boolean isSymmetry(String s){
        int len = s.length();
        if (len< 2){
            return true;
        }
        for(int i =0;i< len/2;i++){
            if (s.charAt(i)!= s.charAt(len -i-1)){
                return false;
            }
        }
        return true;
    }
    public static int getA(char a){

        if (a >='A' && a <='Z') {
            return a;
        }
        if (a >='a' && a <='z') {
            return a-('a'-'A');
        }
        return 0;
    }

    /**
     * 最长递增子序列（LIS）
     * https://blog.csdn.net/qq_41765114/article/details/88415541
     * @param a
     * @return
     */
    public static int longestIncreasingSubsequences(int[]a){
        if (a.length == 1){
            return 1;
        }
        int[] tem = new int[a.length] ;
        tem[1] = 1;
        for(int i=1;i<a.length;i++){
            for(int j=i-1;j>=0;j--){
                int max = 1;
                if(a[i] > a[j]) {
                    max = tem[j]+1;
                }
                if(tem[i]< max) {
                    tem[i] = max;
                }
            }
        }
        int max = 1;
        for(int t: tem) {
            if(t > max){
                max = t;
            }
        }
        return max;
    }
    public static void bubbleSort(char []a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                int it = getA(a[j]),jt = getA(a[j+1]);
                if (it==0 || jt ==0){
                    continue;
                }
                if(it> jt){
                    char tem = a[j+1];
                    a[j+1]= a[j];
                    a[j]= tem;
                }
            }
        }
    }
    public static void sort(char[] a){
        for(int i =0;i<a.length-1;i++){
            for(int j=i+1;j<a.length;j++){
                int it = getA(a[i]),jt = getA(a[j]);
                if (it==0 || jt ==0){
                    continue;
                }
                if(it> jt){
                    char tem = a[i];
                    a[i]= a[j];
                    a[j]= tem;
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(parseInt("-2147483648",10));

        System.out.println(isSymmetry("aabaa"));
        System.out.println(isSymmetry("ccsd"));
        char []a = "A Famous Saying: Much Ado About Nothing (2012/8).".toCharArray() ;
        bubbleSort(a);
        System.out.println(String.valueOf(a));
        System.out.println();
        int a1 = 'A',a2 = 'Z',a3 = 'a' -('a'-'A'),a4 = 'z'-('a'-'A');
        System.out.println(a1 + "  "+ a2 + "  "+ a3 + "  "+ a4 + "  "+ "");


 //A aFmosu aginSy: chMu Ado Abotu ghiNnot (2012/8).
    }
}
