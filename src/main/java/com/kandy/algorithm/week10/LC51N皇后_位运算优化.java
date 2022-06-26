package com.kandy.algorithm.week10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC51N皇后_位运算优化 {
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        powers = new HashMap<>();
        for (int i = 0; i < n; i++) powers.put(1 << i, i);
        p = new ArrayList<Integer>();
        usedCol = usedDia1 = usedDia2 = 0;
        ans = new ArrayList<List<Integer>>();
        dfs(0);
        List<List<String>> result = new ArrayList<List<String>>();
        for (List<Integer> p : ans) {
            List<String> pattern = new ArrayList<String>();
            for (int row = 0; row < n; row++) {
                StringBuilder str = new StringBuilder(repeat(".", n));
                str.setCharAt(p.get(row), 'Q');
                pattern.add(str.toString());
            }
            result.add(pattern);
        }
        return result;
    }


    void dfs(int row) {
        if (row == n) {
            ans.add(new ArrayList<Integer>(p));
            return;
        }
        int state = (1 << n) - 1 - (usedCol | usedDia1 | usedDia2);
        int backupUsedDia1 = usedDia1;
        int backupUsedDia2 = usedDia2;
        // state的所有是1的位可以放（对应原始usedCol usedDia1 usedDia2这一位都是0）
        while (state > 0) {
            int lowbit = state & -state;
            int col = powers.get(lowbit);
            state -= lowbit;
            p.add(col);
            usedCol |= 1 << col;
            usedDia1 |= 1 << col;
            usedDia2 |= 1 << col;
            usedDia1 >>= 1;
            usedDia2 = (usedDia2 << 1) & ((1 << n) - 1);
            dfs(row + 1);
            usedCol ^= 1 << col;
            usedDia1 = backupUsedDia1;
            usedDia2 = backupUsedDia2;
            p.remove(p.size() - 1);
        }
    }

    private String repeat(String str, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    int n;
    List<Integer> p;
    int usedCol;
    int usedDia1;
    int usedDia2;
    List<List<Integer>> ans;
    HashMap<Integer, Integer> powers;
}
