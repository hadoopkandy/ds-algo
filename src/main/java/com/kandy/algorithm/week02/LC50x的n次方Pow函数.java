package com.kandy.algorithm.week02;

/**
 * 50. Pow(x, n)
 */
public class LC50x的n次方Pow函数 {
    // 分治解法
    public double myPow(double x, int n) {
        //0次方等于1
        if (n == 0) return 1;
        //n是负数且当n = Integer.MIN_VALUE = -(1l << 31)时 -n会溢出  x的-n次方 = 1.0 * x /x的负n+1次方
        if (n == Integer.MIN_VALUE) return 1.0 / (myPow(x, -(n + 1)) * x);
        //n是负数 x的-n次方 = 1.0/x的n次方
        if (n < 0) return 1.0 / myPow(x, -n);
        //分治
        double temp = myPow(x, n / 2);
        double ans = temp * temp;
        //如果是奇数
        if (n % 2 == 1) ans *= x;
        return ans;
    }

    public static void main(String[] args) {
        LC50x的n次方Pow函数 lc  = new LC50x的n次方Pow函数();
        System.out.println(lc.myPow(1.0,-2147483648));
        Math.pow(1.0,-2147483648);
    }
}
