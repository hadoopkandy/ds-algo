package com.kandy.algorithm.week02.homework;

import java.util.HashMap;
import java.util.Map;

/**
 * 697. 数组的度
 */
public class LC697数组的度 {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> numMap = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (numMap.containsKey(nums[i])) {
                //之前出现过，次数+1
                numMap.get(nums[i])[0]++;
                //更新最后一次出现的位置
                numMap.get(nums[i])[2] = i;
            } else {
                //次数、第一次出现的位置、最后一次出现的位置
                numMap.put(nums[i], new int[]{1, i, i});
            }
        }
        //数组的度、最短子数组的长度
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : numMap.entrySet()) {
            int[] array = entry.getValue();
            //当前度较大
            if (maxNum < array[0]) {
                maxNum = array[0];
                minLen = array[2] - array[1] + 1;
            } else if (maxNum == array[0]) { //如果两个元素出现次数相同，则比较谁的长度更短
                int curLen = array[2] - array[1] + 1;
                if (minLen > curLen) {
                    minLen = curLen;
                }
            }
        }
        return minLen;
    }
}
