package com.kandy.algorithm.week02.homework;

import java.util.*;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
public class LC47全排列II {
    public List<List<Integer>> permuteUnique(int[] nums) {
        n = nums.length;
        num = new int[n];
        for (int i = 0; i < n; i++) num[i] = nums[i];
        Arrays.sort(num);
        used = new boolean[n];
        per = new ArrayList<>();
        ans = new ArrayList<>();
        recur(0);
        return ans;
    }

    private void recur(int depth) {
        //填完了n个位置
        if (depth == n) {
            ans.add(new ArrayList<>(per));
            return;
        }
        for (int i = 0; i < n; i++) {
            //used[i]：当前数字是否出现过 去重要保证相同数字的相对位置保持不变，要填后面的节点就一定先填过前面的节点
            //如果当前数字与前一个数字相同（nums[i] == nums[i - 1]）并且前一个数字还没有出现的话（vis[i - 1] == false),那么就不能选择当前数字（continue）
            //如果前面的数字已经出现过（vis[i] == true），则可以选择当前数字
            //假设三个1 分别为 1a 1b 1c 要想填1b 就一定之前填过1a 要想填过1c 就一定填过1b 那么三个1都被填充的顺序就一定为1a 1b 1c
            if (used[i] || (i > 0 && num[i] == num[i - 1] && !used[i - 1])) {
                continue;
            }
            //没选过
            used[i] = true;
            per.add(num[i]);
            recur(depth + 1);
            per.remove(per.size() - 1);
            used[i] = false;
        }
    }

    private int n;
    private int[] num;
    private boolean[] used; //每个数是否选过
    private List<Integer> per;
    private List<List<Integer>> ans;

    public static void main(String[] args) {
        LC47全排列II sol = new LC47全排列II();
        System.out.println(sol.permuteUnique(new int[]{1, 2, 3}));

    }
}
