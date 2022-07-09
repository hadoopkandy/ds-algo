package com.kandy.algorithm.week10;

public class LC231_2的幂 {
    public boolean isPowerOfTwo(int n) {
        //lowbit等于n本身，lowbit针对n >0才有
        return n <= 0 ? false : n == (n & -n);
    }
}
