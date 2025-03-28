package com.kandy.algorithm.week05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 皇后们的约束条件:
 * 1.不能同行
 * 2.不能同列
 * 3.不能同斜线 （45度和135度角）
 *
 * 状态：行号row + 放的情况p
 * 每个i j i-j i+j 只能放一次
 * 蛮力搜索：排列型，每行放的皇后的列号是一个排列，最后验证斜线
 * 剪枝：维护两种斜线(行号+列号、行号-列号)的已用值集合，排序造成重复的分支
 */
public class L08_C51N皇后 {
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        p = new ArrayList<>();
        used = new boolean[n];
        usedPlus = new HashSet<>();
        usedMinus = new HashSet<>();
        ans = new ArrayList<>();
        //java 11  ".".repeat(n)
        String s = String.join("", Collections.nCopies(n, "."));
        dfs(0);
        List<List<String>> result = new ArrayList<>();
        for (List<Integer> p : ans) {
            List<String> pattern = new ArrayList<String>();
            for (int row = 0; row < n; row++) {
                StringBuilder str = new StringBuilder(s);
                str.setCharAt(p.get(row), 'Q');
                pattern.add(str.toString());
            }
            result.add(pattern);
        }
        return result;
    }


    void dfs(int row) {
        if (row == n) {
            ans.add(new ArrayList<>(p));
        }
        for (int col = 0; col < n; col++)
            //剪枝
            if (!used[col] && !usedPlus.contains(row + col) && !usedMinus.contains(row - col)) {
                p.add(col);
                used[col] = true;
                usedPlus.add(row + col);
                usedMinus.add(row - col);
                dfs(row + 1);
                usedMinus.remove(row - col);
                usedPlus.remove(row + col);
                used[col] = false;
                p.remove(p.size() - 1);
            }
    }

    int n;
    List<Integer> p;
    boolean[] used;  // we can also use a HashSet
    HashSet<Integer> usedPlus; //  / i+j 不能重复
    HashSet<Integer> usedMinus; // \ i-j 不能重复
    List<List<Integer>> ans;
}
