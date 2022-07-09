package com.kandy.algorithm.week10;

public class LC50_pow {
    //java 跑不过
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) return 1.0 / myPow(x, -n);
        double temp = x;
        double ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) ans = ans * temp;
            temp = temp * temp;
            n = n >> 1;
        }
        return ans;
    }
}
