package com.kandy.algorithm.week10;

public class LC231_2的幂 {
    public boolean isPowerOfTwo(int n) {
        return n <= 0 ? false : n == (n & -n);
    }
}
