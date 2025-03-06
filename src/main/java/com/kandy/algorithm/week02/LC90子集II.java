package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets-ii/description/
 * 为了方便跳过相同元素，在递归前，把 nums 排序（从小到大或者从大到小都可以）。
 * 回溯的做法和 78 题类似。需要注意的是，在不选 nums[i] 时，要跳过后续所有等于 nums[i] 的数。如果不跳过这些数，设 x=nums[i],x'=nums[i+1]
 * 那么「选 x 不选 x′」和「不选 x 选 x′」这两种情况都会加到答案中，这就重复了。
 */
public class LC90子集II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        ans = new ArrayList<List<Integer>>();
        chosen = new ArrayList<Integer>();
        findSubsets(nums, 0);
        return ans;
    }
    private void findSubsets(int[] nums, int i) {
        //递归边界
        if (i == nums.length) {
            ans.add(new ArrayList<Integer>(chosen));
            return;
        }
        //每层相似的逻辑：nums[i]选或不选
        //选
        chosen.add(nums[i]);
        findSubsets(nums, i +1 );
        //还原现场
        chosen.remove(chosen.size() - 1);

        int x = nums[i];
        // 不选 x，跳过所有等于 x 的数
        // 如果不跳过这些数，会导致「选 x 不选 x'」和「不选 x 选 x'」这两种情况都会加到 ans 中，这就重复了
        //跳过当前树层使用过的、相同的元素
        i++;
        while (i < nums.length && nums[i] == x) {
            i++;
        }
        //不选
        findSubsets(nums, i);
    }

    private List<List<Integer>> ans;
    private List<Integer> chosen;

    public static void main(String[] args) {
        LC90子集II code = new LC90子集II();
        System.out.println(code.subsetsWithDup(new int[]{1, 2, 3}));
    }

}
