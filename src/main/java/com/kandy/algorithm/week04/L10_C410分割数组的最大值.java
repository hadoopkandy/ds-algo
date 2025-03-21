package com.kandy.algorithm.week04;

/**
 * 后继型
 */
public class L10_C410分割数组的最大值 {
    public int splitArray(int[] nums, int m) {
        int left = 0; // 下界：最大的一个nums[i]
        int right = 0; // 上界：所有元素和
        for (int i = 0; i < nums.length; i++) {
            left = Math.max(left, nums[i]);
            right += nums[i];
        }
        //示例数据nums={7,2,5,10,8} left = 10 right =32
        while (left < right) {
            int mid = (left + right) / 2;
            // 第一个使得判定问题isValid得到true的位置
            if (isValid(nums, m, mid)) right = mid;
            else left = mid + 1;
        }
        return right;
    }

    // 判定 把nums分成<=m组 连续子数组，每组的和<=T
    private boolean isValid(int[] nums, int m, int T) {
        int box = 0;
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (box + nums[i] <= T) {
                box += nums[i]; // 放进当前组，不超
            } else {
                count++; // 超了，新开一组
                if (nums[i] > T) return false; //一个盒子放这个元素都放不下，直接无解
                box = nums[i];
            }
        }
        return count <= m;
    }

    public static void main(String[] args) {
        L10_C410分割数组的最大值 lc = new L10_C410分割数组的最大值();
        int[] nums = new int[]{7, 2, 5, 10, 8};
        System.out.println(lc.splitArray(nums, 2));
    }
}
/*
厚
[7],[2,5,10,8]
7      25           max:25

[7,2],[5,10,8]
9       23          max:23

7+2+5  10+8
14     18           max:18

T=20

分成<=100的2组是否可行？可行。
7+2+5
10+8  1

分成<=20的2组是否可行？可行。
7+2+5
10+8


分成<=17的2组是否可行？不可行。
7+2+5
10
8
*/
