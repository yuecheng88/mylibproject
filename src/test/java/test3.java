import java.util.Arrays;

/**
 *
 * 我部门组织到惠州团建，可以自驾收费小型快艇登岛游玩。导游介绍到：每艘快艇收费一样，其最大承重为limit，
 * 最多可同时载2个人。为了节省开支，需要你设计一个算法，计算所有人上岛所需的最小快艇数量。（给定数组people，people[i]表示第i个人的体重）
 * 示例1：输入：people = [1,2], limit = 3			输出：1		解释：1艘船载 (1, 2)
 * 示例2：输入：people = [3,2,2,1], limit = 3		输出：3		解释：3艘船分别载 (1, 2), (2)和(3)
 * 示例 3：输入：people = [3,5,3,4], limit = 5		输出：4		解释：4 艘船分别载 (3), (3), (4), (5)
 * 接口定义：public int numRescueBoats(int[] people, int limit)
 *
 *
 *
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * 示例 1：		输入：nums = [3,0,1]	输出：2        0 1 3  = num[1]=1<   2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：		输入：nums = [0,1]		输出：2   0 1
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 */
// 1 2 3
public class test3 {
    public static void main(String[] args) {
        int a[] = new int[]{3,0,1};
        int b[] = new int[]{0};
        System.out.println(findNotIn(a));
        System.out.println(findNotIn(b));
    }
    public static  int findNotIn(int[] nums){
        Arrays.sort(nums);
        int i = 0,j = nums.length - 1;
        while(i <= j){
            int m = (i + j)/2;
            if(nums[m] == m)
                i = m + 1;
            else
                j = m - 1;
        }
        return i;
    }
}
