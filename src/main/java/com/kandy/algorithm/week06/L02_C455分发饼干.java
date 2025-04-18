package com.kandy.algorithm.week06;

import java.util.Arrays;

/**
 * 贪心-决策包容性
 * 一块饼干，发给满足里面最大的孩子
 * 一个孩子，吃满足里面最小的饼干
 */
public class L02_C455分发饼干 {
    public int findContentChildren(int[] g, int[] s) {
       /*g[i] <= s[j] 的i都可以满足

       g[i1] <= g[i2] <= s[j] 满足i2更好

       满足i2 答案+1  剩下g[i1] 需要被满足 剩余同样的饼干
       满足i1 答案+1  剩下g[i2] 需要被满足

       ...xxxxxxxx*/

        int ans = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int j = 0;
        for (int child : g) {
            //s[j] < child 会跳过一些无法满足的饼干
            //当s[j] >= child 时退出while，在满足里面取最小的
            while (j < s.length && s[j] < child) j++;
            if (j < s.length) {
                ans++;
                j++;
            }
        }
        return ans;
    }
}
