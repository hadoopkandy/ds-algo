package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations/description/
 * 46. 全排列 （拿一个，只拿一次）
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
 * 思路：从还没选过的数里选一个
 */
public class L08_C46全排列 {
    public List<List<Integer>> permute(int[] nums) {
        n = nums.length;
        num = new int[n];
        for (int i = 0; i < n; i++) num[i] = nums[i];
        used = new boolean[n];
        per = new ArrayList<>();
        ans = new ArrayList<>();
        recur(0);
        return ans;
    }

    private void recur(int depth) {
        if (depth == n) {
            ans.add(new ArrayList<>(per));
            return;
        }
        //依次考虑0,1,...,n-1位置放哪个数
        //“从还没用过的”数中选一个放在当前位置
        for (int i = 0; i < n; i++) {
            //没选过
            if(!used[i]){
                used[i] = true;
                per.add(num[i]);
//                System.out.println("i="+ i + " depth:" + depth + " used: " + Arrays.toString(used) + " per:" +per);
                recur(depth + 1);
                per.remove(per.size() - 1);
                used[i] = false;
            }
        }
    }

    private int n;
    private int[] num;
    private boolean[] used; //每个数是否选过
    private List<Integer> per;
    private List<List<Integer>> ans;

    public static void main(String[] args) {
        L08_C46全排列 sol = new L08_C46全排列();
        System.out.println(sol.permute(new int[]{1, 2, 3}));
    }
}
