package com.xiao.algorithm;

/**
 *https://www.cnblogs.com/wkfvawl/p/9768729.html （KMP算法）
 *https://www.cnblogs.com/-citywall123/p/10034261.html(求一个字符串中子串连续出现的最大次数)
 *
 *
 *
 *
 */
public class KMP {

    public static int[] getNextArray2(char[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        int k = -1;
        for (int q = 1; q < t.length; q++) {

            while (k!=-1 && t[k+1] != t[q]) {
                k = next[k];
            }
            if (t[k+1] == t[q]) {
                k = k+1;
            }
            next[q] = k;
        }
        return next;
    }
    /**
     * 求出一个字符数组的next数组
     * @param t 字符数组
     * @return next数组
     */
    public static int[] getNextArray(char[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        next[1] = 0;
        int k;
        for (int j = 2; j < t.length; j++) {
            k=next[j-1];
            while (k!=-1) {
                if (t[j - 1] == t[k]) {
                    next[j] = k + 1;
                    break;
                }
                else {
                    k = next[k];
                }
                next[j] = 0;  //当k==-1而跳出循环时，next[j] = 0，否则next[j]会在break之前被赋值
            }
        }
        return next;
    }

    /**
     * 对主串s和模式串t进行KMP模式匹配
     * @param s 主串
     * @param t 模式串
     * @return 若匹配成功，返回t在s中的位置（第一个相同字符对应的位置），若匹配失败，返回-1
     */
    public static int kmpMatch(String s, String t){
        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();
        int[] next = getNextArray(t_arr);
        for(int i : next){
            System.out.print(i + "  ");
        }
        System.out.println();
        int[] next2 = getNextArray2(t_arr);
        for(int i : next2){
            System.out.print(i + "  ");
        }
        System.out.println();
        int i = 0, j = 0;
        while (i<s_arr.length && j<t_arr.length){
            if(j == -1 || s_arr[i]==t_arr[j]){
                i++;
                j++;
            }
            else
                j = next[j];
        }
        if(j == t_arr.length)
            return i-j;
        else
            return -1;
    }

    public static void main(String[] args) {
        System.out.println(kmpMatch("abcabaabaabcacb", "abcdabd"));
    }

}
