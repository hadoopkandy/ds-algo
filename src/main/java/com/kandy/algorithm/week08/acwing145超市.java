package com.kandy.algorithm.week08;

import java.util.Arrays;
import java.util.Scanner;

public class acwing145超市 {
    static int n;
    static int[] fa;
    static int[][] a;

    static int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
            a = new int[n][2];
            fa = new int[10001];
            for (int i = 0; i < n; i++) {
                a[i][0] = input.nextInt(); //第i件商品的利润
                a[i][1] = input.nextInt();//第i件商品的过期时间
            }
            Arrays.sort(a, (x, y) -> { return x[0] - y[0]; });//按照利润升序排列
            for (int i = 0; i <= 10000; i++)
                fa[i] = i;
            int ans = 0;
            //倒序，利润大的先卖
            for (int i = n - 1; i >= 0; i--) {
                int profit = a[i][0];
                int day = a[i][1];
                int lastAvailableDay = find(day);//找day之前的第一个空闲日（根）
                if (lastAvailableDay > 0) {
                    ans += profit;
                    fa[lastAvailableDay] = lastAvailableDay - 1;
                }
            }

            System.out.println(ans);
        }
    }
}
