package com.kandy.algorithm.week09;

import java.util.Arrays;

public class 差分模板_LC1109航班预订统计 {
/*
1   2  3   4  5
10     -10
    20     -20
    25           -25
10  45 -10 -20 0
10 55 45 25 25
 */

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] delta = new int[n + 2];  // 差分要开0~n+1
        Arrays.fill(delta, 0);
        for (int[] booking : bookings) {
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];
            // 差分模板
            delta[first] += seats;
            delta[last + 1] -= seats;
        }
        //10  45 -10 -20 0

        int[] sum = new int[n + 1]; // 0~n
        sum[0] = 0;
        // 1~n  10 55 45 25 25
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + delta[i];


        // 0~n-1 把下标变成从0开始
        int[] ans = new int[n];
        for (int i = 1; i <= n; i++) ans[i - 1] = sum[i];
        return ans;
    }
/*
一维数组A
前缀和数组S  s[i] = s[i-1] + A[i]
差分数组B   B[1]=A[1] B[i]=A[i] - A[i-1]
B1 + B2 +... + Bn = A[1] + A[2] -A[1] + A[3] -A[2] +....A[n] =A[n]
差分数组B的前缀和数组就是原数组A

原数组A[2]+1  A[3]+1  A[4] +1
B[2] = A[2] - A[1]  +1
B[3] =A[3] - A[2]  不变
B[4] =A[4] - A[3]  不变
B[5] =A[5] - A[4]  -1
把A的第l个数到第r个数加d，B的变化为：B[l]+d  B[r+1]-d

原数组A[3]-2  A[4] -2
B[3] =A[3] - A[2]  -2
B[4] =A[4] - A[3] 不变
B[5] =A[5] - A[4]  +2
把A的第l个数到第r个数减d，B的变化为：B[l]-d  B[r+1]+d

原数组A         [0,1,-1,-1,0]
差分数组        [0,1,-2,0,1]
差分数组的前缀和 [0,1,-1,-1,0] 变成最终的数组A
 */
}
