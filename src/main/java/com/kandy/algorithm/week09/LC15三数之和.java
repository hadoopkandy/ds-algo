package com.kandy.algorithm.week09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 */
public class LC15三数之和 {
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3)
            return new ArrayList<>();

        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int i = 0; i < n; i++) {
            //已经排序好，所以后面不可能有三个数加和等于0，直接返回结果
            if (nums[i] > 0) {
                return ans;
            }
            // 对于重复元素：跳过，避免出现重复解
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    ans.add(list);

                    //去除重复解 例如输入：[-2,0,0,2,2]
                    while (left < right && nums[left + 1] == nums[left]) left++;
                    while (left < right && nums[right - 1] == nums[right]) right--;

                    //答案不唯一：-4 -1 -1 0 1 2
                    left++;
                    right--;

                    //若和大于 target，说明 nums[right]太大，right左移
                } else if (sum > target) {
                    right--;
                } else {
                    //若和小于 target，说明 nums[left]太小,left右移
                    left++;
                }
            }
        }
        return ans;
    }

    //枚举第一个数nums[i],在后面找两数之和=-nums[i]
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            List<List<Integer>> jks = twoSum(nums, i + 1, -nums[i]);
            for (List<Integer> jk : jks) {
                ans.add(Arrays.asList(new Integer[]{nums[i], jk.get(0), jk.get(1)}));
            }
        }
        return ans;
    }

    List<List<Integer>> twoSum(int[] nums, int start, int target) {
        //返回全部答案
        List<List<Integer>> ans = new ArrayList<>();
        int j = nums.length - 1;
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;//去重
            while (i < j && nums[i] + nums[j] > target) j--;
            if (i < j && nums[i] + nums[j] == target) {
                ans.add(Arrays.asList(new Integer[]{nums[i], nums[j]}));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }


}
