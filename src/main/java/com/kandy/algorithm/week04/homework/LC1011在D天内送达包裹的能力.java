package com.kandy.algorithm.week04.homework;

//这题与LC410分割数组的最大值思路代码完全一致
public class LC1011在D天内送达包裹的能力 {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0; // 下界：最大的一个nums[i]
        int right = 0; // 上界：所有元素和
        for (int i = 0; i < weights.length; i++) {
            left = Math.max(left, weights[i]);
            right += weights[i];
        }
        while (left < right) {
            int mid = (left + right) / 2;
            // 第一个使得判定问题isValid得到true的位置
            if (isValid(weights, days, mid)) right = mid;
            else left = mid + 1;
        }
        return right;

    }

    // 判定 把nums分成<=m组 连续子数组，每组的和<=T
    private boolean isValid(int[] weights, int days, int T) {
        int accumulated_weight = 0; //当前这一天已经运送的包裹重量之和
        int need = 1;//需要运送的天数
        for (int i = 0; i < weights.length; i++) {
            if (accumulated_weight + weights[i] <= T) {
                accumulated_weight += weights[i]; // 放进当前组，不超
            } else {
                need++; // 超了，新开一组
                if (weights[i] > T) return false; //一天运这个，直接无解
                accumulated_weight = weights[i];
            }
        }
        return need <= days;
    }

}
