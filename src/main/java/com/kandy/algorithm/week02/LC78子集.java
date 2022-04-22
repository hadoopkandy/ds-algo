package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集 （选或不选，任意个）
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）
 */
public class LC78子集 {
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<List<Integer>>();
        chosen = new ArrayList<Integer>();
        findSubsets(nums, 0);
        return ans;
    }
/*
call findSubsets index:0 chosen:[]
call findSubsets index:1 chosen:[]
call findSubsets index:2 chosen:[]
call findSubsets index:3 chosen:[]
递归边界 [] 加入答案

3 加入chosen
call findSubsets index:3 chosen:[3]
递归边界 [3] 加入答案
3 还原现场

2 加入chosen
call findSubsets index:2 chosen:[2]
call findSubsets index:3 chosen:[2]
[2] 加入答案
3 加入chosen
call findSubsets index:3 chosen:[2, 3]
[2, 3] 加入答案
3 还原现场
2 还原现场

1 加入chosen
call findSubsets index:1 chosen:[1]
call findSubsets index:2 chosen:[1]
call findSubsets index:3 chosen:[1]
[1] 加入答案

3 加入chosen
call findSubsets index:3 chosen:[1, 3]
[1, 3] 加入答案

3 还原现场
2 加入chosen
call findSubsets index:2 chosen:[1, 2]
call findSubsets index:3 chosen:[1, 2]
[1, 2] 加入答案

3 加入chosen
call findSubsets index:3 chosen:[1, 2, 3]
[1, 2, 3] 加入答案
3 还原现场
2 还原现场
1 还原现场
 */
    private void findSubsets(int[] nums, int i) {
//        System.out.println("call findSubsets index:"+index + " chosen:"+chosen );
        //递归边界
        if (i == nums.length) {
//            System.out.println(chosen +" 加入答案");
            ans.add(new ArrayList<Integer>(chosen));
            return;
        }
        //每层相似的逻辑：nums[i]选或不选
        //不选
        findSubsets(nums, i + 1);

        //选
        chosen.add(nums[i]);
//        System.out.println(nums[index] +" 加入chosen");
        findSubsets(nums, i + 1);
//        System.out.println(chosen.get(chosen.size() - 1) +" 还原现场");
        //还原现场
        chosen.remove(chosen.size() - 1);
    }

    private List<List<Integer>> ans;
    private List<Integer> chosen;

    public static void main(String[] args) {
        LC78子集 code = new LC78子集();
        System.out.println(code.subsets(new int[]{1, 2, 3}));
    }

}
