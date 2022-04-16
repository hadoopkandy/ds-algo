package com.kandy.algorithm.leetcode;

import java.util.*;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素
 */
public class Solution169 {
    private static Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    public static int majority(int[] nums){
        int n = nums.length;
        int i,c,count =1; //c 用来保存主元素,count用来计数
        c = nums[0];    //设置nums[0]为候选主元数
        for(i =1; i<n; i++){ //查找候选主元数
            if(nums[i] == c){
                count++;    //对候选中的主元数计数
            }else{
                if(count >0){ //处理不是候选主元素的情况
                    count--;
                }else{      //更新候选主元素，重新计数
                    c =nums[i];
                    count =1;
                }
            }
        }
        if(count >0){
            for(i =count =0;i<n;i++){  //统计候选主元素的实际出现次数
                if(nums[i] == c){
                    count ++;
                }
            }
        }
        if(count >n/2) {  //确认候选主元素
            return c;
        } else
            return -1;  //不存在主元素
    }

    public static void main(String[] args) {
        int[] nums  = new int[]{3,2,3};
        System.out.println(majorityElement(nums));
        int[] nums1  = new int[]{0,5,5,3,5,7,5,5};
        int[] nums2  = new int[]{0,7,5,3,5,1,5,7};
        System.out.println(majority(nums1));
        System.out.println(majority(nums2));
    }


}
