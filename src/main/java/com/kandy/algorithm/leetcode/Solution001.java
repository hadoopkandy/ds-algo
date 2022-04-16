package com.kandy.algorithm.leetcode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 */
public class Solution001 {
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

    /**
     * 题目并没有假定数组是有序的，所以以下双指针的写法也是不对的，不能对数组排序
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        boolean found = false;
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                found = true;
                break;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        if (found) {
            return new int[]{i, j};
        } else {
            return new int[0];
        }
    }


    public static void main(String[] args) {

        Solution001 sol = new Solution001();

        System.out.println(Arrays.toString(sol.twoSum(new int[]{3,2,4},6)));
    }
}
