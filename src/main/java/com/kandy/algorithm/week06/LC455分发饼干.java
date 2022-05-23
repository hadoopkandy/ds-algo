package com.kandy.algorithm.week06;

import java.util.Arrays;

/**
 * 贪心-决策包容性
 */
public class LC455分发饼干 {
    public int findContentChildren(int[] g, int[] s) {
       /*g[i] <= s[j] 的i都可以满足

       g[i1] <= g[i2] <= s[j] 满足i2更好

       满足i2 答案+1  剩下g[i1] 剩余同样的饼干
       满足i1 答案+1  剩下g[i2]

       ...xxxxxxxx*/

        int ans = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int j = 0;
        for (int child : g) {
            //当s[j] >= child 时退出while，在满足里面取最大的
            while (j < s.length && s[j] < child) j++;
            if (j < s.length) {
                ans++;
                j++;
            }
        }
        return ans;
    }
}
