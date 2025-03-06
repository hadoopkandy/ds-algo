package com.kandy.algorithm.week02.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets-ii/description/
 * 为了方便跳过相同元素，在递归前，把 nums 排序（从小到大或者从大到小都可以）。
 * 回溯的做法和 78 题类似。需要注意的是，在不选 nums[i] 时，要跳过后续所有等于 nums[i] 的数。如果不跳过这些数，设 x=nums[i],x'=nums[i+1]
 * 那么「选 x 不选 x′」和「不选 x 选 x′」这两种情况都会加到答案中，这就重复了。
 */
public class LC90子集II {
    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    boolean[] used;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            result.add(path);
            return result;
        }
        Arrays.sort(nums);
        used = new boolean[nums.length];
        subsetsWithDupHelper(nums, 0);
        return result;
    }

    private void subsetsWithDupHelper(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            subsetsWithDupHelper(nums, i + 1);
            path.removeLast();
            used[i] = false;
        }
    }


}
