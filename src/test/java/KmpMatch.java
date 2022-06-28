/*

public class KmpMatch {


    void cal_next(char *str, int *next, int len)
    {
        next[0] = -1;
        int k = -1;
        for (int q = 1; q <= len-1; q++)
        {
            while (k > -1 && str[k + 1] != str[q])
            {
                k = next[k];
            }
            if (str[k + 1] == str[q])
            {
                k = k + 1;
            }
            next[q] = k;
        }
    }
    int KMP(char *str, int slen, char *ptr, int plen)
    {
        int *next = new int[plen];
        cal_next(ptr, next, plen);
        int k = -1;
        for (int i = 0; i < slen; i++)
        {
            while (k >-1&& ptr[k + 1] != str[i])
                k = next[k];
            if (ptr[k + 1] == str[i])
                k = k + 1;
            if (k == plen-1)
            {
                //cout << "在位置" << i-plen+1<< endl;
                //k = -1;//重新初始化，寻找下一个
                //i = i - plen + 1;//i定位到该位置，外层for循环i++可以继续找下一个（这里默认存在两个匹配字符串可以部分重叠）
                return i-plen+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String str = "bacbababadababacambabacaddababacasdsd";
        String pstr = "ababaca";
        int a = KMP(str, 36, ptr, 7);
        printf("%d",a);
    }

}
*/
