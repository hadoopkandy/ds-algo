package com.kandy.algorithm.week09.homework;

import java.util.HashMap;

public class LC560和为K的子数组 {
    //枚举法
    public int subarraySum(int[] nums, int k) {
        //记录和为k的子数组个数
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            //累计子数组[j,i]的和
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    //前缀和+哈希表
    // 前缀和定义：pre[i] =pre[i-1] + nums[i]
    // [j,i] 子数组和为k  pre[i] - pre[j-1] = k  ->  pre[j-1] = pre[i] - k
    // 以i结尾的和为k的连续子数组个数时只要需要统计有多少个pre[i] - k的pre[j]即可
    public int subarraySum2(int[] nums, int k) {
        int count = 0, pre = 0;
        //哈希表mp 记录前缀和pre[i]出现次数
        HashMap<Integer, Integer> mp = new HashMap<>();
        //前缀和pre-k=0 出现的次数为1
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            //计算当前pre数组的值
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);

        }
        return count;
    }

    public static void main(String[] args) {
        LC560和为K的子数组 code = new LC560和为K的子数组();
        System.out.println(code.subarraySum2(new int[]{1},0));

    }
}
