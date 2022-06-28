
//1 8 6 2 5 4 8 3 7
public class test4 {

    public static void main(String[] args) {
        int x[] = new int[]{1 ,8 ,6 ,2 ,5 ,4, 8 ,3 ,7};
        int max = 0;
        int capbity = 0;
        for(int i=0;i<x.length-1;i++){
            for(int j=i+1;j<x.length;j++){
                capbity = min(x[i],x[j]) * (j-i);
                if (capbity > max){
                    max = capbity;
                }
            }
        }
        System.out.println(max);
        max = 0;
        int left = 0, right = x.length-1;
        while (left<right){
            max = Math.max(max,Math.min(x[left],x[right]) *(right-left));
            if(x[left] < x[right]) {
                left++;
            }else {
                right --;
            }
        }
        System.out.println(max);
    }

    public static int min(int a,int b){
        return a < b ? a:b;
    }
}
