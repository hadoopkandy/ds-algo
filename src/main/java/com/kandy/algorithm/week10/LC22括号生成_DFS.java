package com.kandy.algorithm.week10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 组合型
 */
public class LC22括号生成_DFS {
    public List<String> generateParenthesis(int n) {
        this.n = n;
        this.s = new ArrayList<>();
        this.ans = new ArrayList<>();
        dfs(0, 0, 0);
        return ans;
    }

    //组合型，2n个位置选n个放左扩号，剩余放右括号，最后验证
    //剪枝:实时维护左右括号数量，不合法时及时停止
    void dfs(int i, int left, int right) {
        //剪枝
        if (left > n || right > n || !isValid(s)) return;
        if (i == 2 * n) {
            ans.add(s.stream()
                    .map(e -> e.toString())
                    .collect(Collectors.joining()));
            return;
        }
        s.add('(');
        dfs(i + 1, left + 1, right);
        s.remove(s.size() - 1);
        s.add(')');
        dfs(i + 1, left, right + 1);
        s.remove(s.size() - 1);
    }

    // ))(不合法   (() 合法
    boolean isValid(List<Character> s) {
        int left = 0; //左括号数量
        for (Character ch : s) {
            if (ch == '(') left++;
            else {
                if (left <= 0) return false;
                left--; //右括号与左括号匹配
            }
        }
        return true;
    }

    int n;
    List<Character> s;
    List<String> ans;
}
