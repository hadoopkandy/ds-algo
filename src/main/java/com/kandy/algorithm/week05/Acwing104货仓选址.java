package com.kandy.algorithm.week05;

import java.util.Arrays;
import java.util.Scanner;

//排序后求中位数
public class Acwing104货仓选址 {
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
