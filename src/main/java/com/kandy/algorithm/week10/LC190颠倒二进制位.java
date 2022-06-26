package com.kandy.algorithm.week10;

public class LC190颠倒二进制位 {
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++)
            // ans.push_back(n >> i & 1)
            ans = (ans << 1) | (n >> i & 1);
        return ans;
    }
}
