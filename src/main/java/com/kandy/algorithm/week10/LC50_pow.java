package com.kandy.algorithm.week10;

public class LC50_pow {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == -(1l << 31)) return 1.0 / (myPow(x, -(n + 1)) * x);
        if (n < 0) return 1.0 / myPow(x, -n);
        double temp = myPow(x, n / 2);
        double ans = temp * temp;
        if (n % 2 == 1) ans *= x;
        return ans;
    }
}
