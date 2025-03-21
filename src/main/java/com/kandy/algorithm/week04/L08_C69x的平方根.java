package com.kandy.algorithm.week04;

/**
 * 满足ans*ans<=x 条件时，最大的ans 前驱型
 */
public class L08_C69x的平方根 {
    //整数二分
    public int mySqrt(int x) {
        // 找最大的ans，满足ans*ans<=x
        // 例如x=8    1 1 0 0 (1*1满足 2*2 满足  3*3 不满足 4*4 不满足) 条件单调
        int left = 0, right = x;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            //mid * mid <= x 可能int越界
            if (mid <= x / mid) {
                left = mid; //条件满足里面要大的1
            } else {
                right = mid - 1;
            }
        }
        return right;
//        return (int) (myRealSqrt(x));
    }

    // 实数二分模板
    // ans = realSqrt(x)
    // 如果要求4位小数，就多算2~4位，到1e-6或1e-8，保证精确
    double myRealSqrt(double x) {
        double left = 0, right = x;
        while (right - left > 1e-7) {
            double mid = (left + right) / 2;
            if (mid * mid <= x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
