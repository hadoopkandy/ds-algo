package com.kandy.algorithm.week02.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 */
public class LC39组合总和 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 先进行排序
        backtracking(res, new ArrayList<>(), candidates, target, 0, 0);
        return res;
    }

    /**
     * @param res        存放结果集
     * @param path       存放符合条件的结
     * @param candidates 题目中的参数
     * @param target     题目中的参数
     * @param sum        统计单一结果path里的总和
     * @param idx        for循环的起始位置
     */
    public void backtracking(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int sum, int idx) {
        // 找到了数字和为 target 的组合
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            // 如果 sum + candidates[i] > target 就终止遍历
            if (sum + candidates[i] > target) break;
            path.add(candidates[i]);
            backtracking(res, path, candidates, target, sum + candidates[i], i); // 关键点:不用i+1了，表示可以重复读取当前的数
            path.remove(path.size() - 1); // 回溯，移除路径 path 最后一个元素
        }
    }

    /**
     * 代码写法2，思路和上面的思路一致，主要是简化backtracking 的参数
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new ArrayList<>();
        chosen = new ArrayList<>();
        Arrays.sort(candidates);
        n = candidates.length;
        sum = 0;
        this.target = target;
        combine(candidates, 0);
        return ans;

    }

    public void combine(int[] candidates, int startIndex) {
        if (sum == target) {
            ans.add(new ArrayList<Integer>(chosen));
            return;
        }
        for (int i = startIndex; i < n; i++) {
            if (sum + candidates[i] > target)
                continue;
            chosen.add(candidates[i]);
            sum += candidates[i];
            combine(candidates, i); //candidates 中的数字可以无限制重复被选取,这里是i 不是i+1，很关键

            chosen.remove(chosen.size() - 1);
            sum -= candidates[i];
        }
    }

    private List<List<Integer>> ans;
    private List<Integer> chosen;
    private int sum;
    int n;
    int target;
}
