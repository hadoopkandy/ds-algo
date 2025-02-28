package com.kandy.algorithm.week02;


import java.util.*;

/**
 * https://leetcode.cn/problems/two-sum/description/
 * 1. 两数之和
 * 454. 四数相加 II 和此题解法类似 分组 + 哈希表
 * 基本思路:枚举一个数x,找它前面有没有target-x
 * 所以建立一个数值到下标的hash map就可以了
 * 对于每个数x,先查询target-x,再插入x
 * 时间复杂度O(n)
 */
public class L01_C1两数之和 {
    //方法一:HashMap
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> hasMap = new HashMap<>(len - 1);
        hasMap.put(nums[0], 0);
        for (int i = 1; i < len; i++) {
            int another = target - nums[i];
            if (hasMap.containsKey(another)) {
                return new int[]{i, hasMap.get(another)};
            }
            hasMap.put(nums[i], i);
        }
        return new int[0];
    }

    //方法二:双指针
    //排序后下标变了，把下标跟着一起排
    public int[] twoSum2(int[] nums, int target) {
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            pairs.add(new int[]{nums[i], i});//<值,原始下标>
        }
        pairs.sort((a, b) -> a[0] - b[0]);

        int j = pairs.size() - 1;
        for (int i = 0; i < pairs.size(); i++) {
            while (i < j && pairs.get(i)[0] + pairs.get(j)[0] > target) j--;
            if (i < j && pairs.get(i)[0] + pairs.get(j)[0] == target) {
                return new int[]{pairs.get(i)[1], pairs.get(j)[1]};
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {

        L01_C1两数之和 sol = new L01_C1两数之和();

        System.out.println(Arrays.toString(sol.twoSum(new int[]{3, 2, 4}, 6)));
    }
}
