package com.kandy.algorithm.week10;

import java.util.Arrays;

public class LC338比特位计数 {
    public int[] countBits(int n) {
        int[] cnt = new int[n + 1];
        Arrays.fill(cnt, 0);
        for (int i = 1; i <= n; i++)
            cnt[i] = cnt[i & (i - 1)] + 1;
        return cnt;
    }
}
