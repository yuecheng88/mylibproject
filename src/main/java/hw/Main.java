package hw;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()) {
//            String s = sc.nextLine();
//            int num = sc.nextInt();
//            Deque<Character> st = new ArrayDeque<Character>();
//            StringBuilder sb = new StringBuilder();
//            for (int i=0;i<s.length();i++){
//                char c = s.charAt(i);
//                while (!st.isEmpty()&& c < st.peek() && num > 0){
//                    st.pop();
//                    num--;
//                }
//                st.push(c);
//            }
//            while (num > 0 ){
//                st.pop();
//                num--;
//            }
//            while (!st.isEmpty()){
//                sb.append(st.pollLast()) ;
//            }
//            System.out.println(sb.toString());
//        }
        tt3();
    }

    public static void tt(){
        String s = "2615371";
        int num = 4;
        char[] a = s.toCharArray();
        Arrays.sort(a);
        System.out.println(a);
        ArrayList<Character> list = new ArrayList<Character>();
        for(int i=0;i<a.length-num;i++){
            list.add(a[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i< a.length;i++){
            if(list.contains(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }

        System.out.println(sb.toString());
    }
    public static void  ttt(){
        String s = "2615371";
        int num = 4;
        Deque<Character> st = new ArrayDeque<Character>();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            while (!st.isEmpty()&& c < st.peek() && num > 0){
                st.pop();
                num--;
            }
            st.push(c);
        }
        while (num > 0 ){
            st.pop();
            num--;
        }
        while (!st.isEmpty()){
            sb.append(st.pollLast()) ;
        }
        System.out.println(sb.toString());
    }

    public static void  tt3(){
        String s = "2615371";
        int num = 4;
        BigDecimal bd = new BigDecimal(s);
        String strS = String.valueOf(s);
        int num2 = 0;
        int [] list1 = new int[strS.length() -num] ;
        list1[0] = 1;
        for(int i = 01;i<strS.length()-num;i++) {
            list1[i] = 10 * list1[i-1];
        }
        int index = 0;
        for(int j=list1.length -1;j>=0;j--){
            int c =9;
            for(int a = index;a<strS.length()-j;a++){
                String ss = strS.substring(a,a+1);
                if (c> Integer.parseInt(ss)){
                    c = Integer.parseInt(s);
                    index = a+1;
                }
            }
            num2 = num2 + c + list1[j];
        }
        System.out.println(num2);
    }
}
