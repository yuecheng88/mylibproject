package hw;
import java.util.*;

public class Main2 {
    public static void main(String[] args){
            int n = 1;
            int m = 10000;
            for (int i = n; i <= m - 2; i++) {
                for (int j = i + 1; j <= m - 1; j++) {
                    double c = Math.sqrt(i * i + j * j);
                    if ((c * 10) % 10 != 0) {
                        continue;
                    }
                    if (c > m) {
                        break;
                    }
                    int cc = (int) c;
                    if (isHuZhi(j, i) == 1 && isHuZhi(cc, i) == 1 && isHuZhi(cc, j) == 1) {
                        System.out.println(i + " " + j + " " + cc);
                    }
                }
            }

    }

    public static int isHuZhi(int a,int b){
        return b == 0 ? a:isHuZhi(b,a%b);
    }

}
