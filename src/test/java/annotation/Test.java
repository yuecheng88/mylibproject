package annotation;

public class Test {


    //1:数组第K个最大元素（时间复查度，思路）、、 使用归并排序 时间复杂度O(NlogN)
    // 2. stringtoint (不能用java现成的方法）
    //3.输入字符串，打印所有序列


    public static void main(String[] args) throws Exception {
        permutation("abc".toCharArray(),0) ;
        permutation("abcde".toCharArray(),0);
        System.out.println(StringConvertToInt("111"));
        System.out.println(StringConvertToInt("111"));
        System.out.println(StringConvertToInt("   00"));
    }

    /**
     * 题目3：输入字符串，打印所有序列
     * @param str 字符串
     * @param i 子串下标
     */
    public static void permutation(char[] str, int i) {
        if (i >= str.length)
            return;
        if (i == str.length - 1) {
            System.out.println(String.valueOf(str));
        } else {
            for (int j = i; j < str.length; j++) {
                char temp = str[j];
                str[j] = str[i];
                str[i] = temp;

                permutation(str, i + 1);

                temp = str[j];
                str[j] = str[i];
                str[i] = temp;
            }
        }
    }
    /**
     * 题目 2 ：字符串转Int
     * @param num
     * @return
     * @throws Exception
     */
    public static int StringConvertToInt(String num) throws Exception {
        char[] ch = num.toCharArray();
        int i = 0, value = 0;
        while (i < ch.length)
        {
            if (ch[i] == ' ') {
                i++; continue;
            }
            if (ch[i] < '0' || ch[i] > '9')
                throw new Exception("字符含义非数字");
            value = value * 10 + (ch[i] - '0');
            i++;
        }
        return value;
    }

}
