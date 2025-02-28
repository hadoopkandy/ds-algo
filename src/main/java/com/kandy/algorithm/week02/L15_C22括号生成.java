package com.kandy.algorithm.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/description/
 * 22.括号生成
 */
public class L15_C22括号生成 {
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }
    public void backtrack(List<String> ans, StringBuilder cur, int left, int right, int n) {
        if (cur.length() == n * 2) {
            ans.add(cur.toString());
            return;
        }
        //如果左括号数量不大于 n，我们可以放一个左括号
        if (left < n) {
            cur.append('(');
            backtrack(ans, cur, left + 1, right, n);
            cur.deleteCharAt(cur.length() - 1);
        }
        //如果右括号数量小于左括号的数量,我们可以放一个右括号
        if (right < left) {
            cur.append(')');
            backtrack(ans, cur, left, right + 1, n);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
    // 分治解法
    public List<String> generateParenthesis(int n) {
        if (store == null) store = new HashMap<>();
        if (n == 0) return Arrays.asList("");
        if (store.containsKey(n)) return store.get(n); // 记忆化
        List<String> ans = new ArrayList<>();
        for (int k = 1; k <= n; k++) { // 加法原理
            //k-1子问题
            List<String> A = generateParenthesis(k - 1);
            //n-k子问题
            List<String> B = generateParenthesis(n - k);
            // S=(A)B
            // 乘法原理
            for (String a : A)
                for (String b : B)
                    ans.add("(" + a + ")" + b);
        }
        store.put(n, ans);
        return ans;
    }

    HashMap<Integer, List<String>> store;

/*
  _ _ | _ _ _ _   ()(())  ()()()
  _ _ _ _ | _ _   (())()  ()()()

  S (A)B
  n k-1  n-k

  k=1 (A) A=0对括号 ()   B=n-k=2对括号 ()()  (())
  结果：()()()   ()(())

  k=2 (A) A=1对括号 (()) B=n-k=1对括号 ()
  结果：(())()

  k=3 (A) A=2对括号 （()()）  （(())）  B=n-k=0对括号
  结果：（()()） （(())）
 */

    public static void main(String[] args) {
        L15_C22括号生成 code = new L15_C22括号生成();
        System.out.println(code.generateParenthesis(3));
    }
}
