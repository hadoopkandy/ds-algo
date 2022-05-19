package com.kandy.algorithm.week02;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * 454. 四数相加 II 和此题解法类似 分组 + 哈希表
 */
public class LC1两数之和 {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer,Integer> hasMap = new HashMap<>(len-1);
        hasMap.put(nums[0],0);
        for(int i =1;i<len; i++){
            int another = target -nums[i];
            if(hasMap.containsKey(another)){
                return new int[]{i,hasMap.get(another)};
            }
            hasMap.put(nums[i],i);
        }
        return new int[0];
    }

    public static void main(String[] args) {

        LC1两数之和 sol = new LC1两数之和();

        System.out.println(Arrays.toString(sol.twoSum(new int[]{3,2,4},6)));
    }
}
