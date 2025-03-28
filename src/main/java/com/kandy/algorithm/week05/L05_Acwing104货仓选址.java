package com.kandy.algorithm.week05;

import java.util.Arrays;
import java.util.Scanner;

//排序后求中位数
public class L05_Acwing104货仓选址 {
    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        Arrays.sort(a);
        int pos = a[n / 2];
        long ans = 0;
        for (int i = 0; i < n; i++) ans += Math.abs(pos - a[i]);
        System.out.println(ans);
    }
}
/*
中位数的概念:
 1 2 6 9    建在 2 或 6 都可以
 1 2 6 9 10 建在 6这个位置
 */
