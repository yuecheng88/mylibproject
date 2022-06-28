import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class test2 {
    private static Map<String, Integer> map = new HashMap<String, Integer>() {
        {
            put("0", 0);
            put("1", 1);
            put("2", 2);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("A", 10);
            put("B", 11);
            put("C", 12);
            put("D", 13);
            put("E", 14);
            put("F", 15);
        }
    };
    public static void main(String[] args) {
        int []a = new int[3];
        for (int s : a){
            System.out.println(s);
        }

    }

    public static void  oneProblem(){
        int []w = {1,4,3};
        int []v = {15,30,20};
        int c = 4;
        int []tw = new int[3];
        int []tv = new int[3];
        if(w[0]<= c){
            tw[0] = w[0];
            tv[0]= v[0];
        }

        for (int i=1;i<3;i++){
            int max = 0 ;
            for (int j=0;j<i;j++){
                if((w[i]+ tw[j]) <= c) {

                }
            }
        }
    }
    public static int right(int[]a,int flag,int l,int r){
        int len = r -l +1;
        if (len <=0) {
            return 0;
        }
        if (len == 1){
            if(a[l] < flag){
                return 1;
            }
            return 0;
        }
        int[] tem = new int[len] ;
        if(a[l] < flag){
            tem[0] = 1;
        }else {
            tem[0] = 0;
        }
        for(int i=l+1,t = 0;i<=r;i++,t++){
            if(a[i]>= flag){
                continue;
            }
            for(int j=i-1;j>=l;j--){
                int max = 1;
                if(a[i] < a[j]) {
                    max = tem[j-l]+1;
                }
                if(tem[i-l]< max) {
                    tem[i-l] = max;
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
    public static int left(int[]a,int flag,int l,int r){
        int len = r - l +1;
        if (len <=0) {
            return 0;
        }
        if (len == 1){
            if(a[l] < flag){
                return 1;
            }
            return 0;
        }
        int[] tem = new int[len] ;
        if(a[l] < flag){
            tem[0] = 1;
        }else {
            tem[0] = 0;
        }
        for(int i=l+1;i<=r;i++){
            if(a[i]>= flag){
                continue;
            }
            for(int j=i-1;j>=l;j--){
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
}

class TT implements Comparable<Object>{



    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public int maxNonNegativeSubArray(int[] A) {
        int sum = 0;
        int maxSum = 0;
        int[] a = A;
        for(int i=0;i< a.length;i++){
            if(a[i]>=0 ){
                sum += a[i];
            }else{
                if (sum> maxSum){
                    maxSum = sum;
                }
                sum = 0;
            }
        }
        return maxSum;
    }
}