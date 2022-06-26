package com.kandy.algorithm.week10;

import java.util.Scanner;
//区间DP https://blog.csdn.net/qq_41661809/article/details/81487613
//http://t.zoukankan.com/2020pengxiyue-p-9338643.html
public class Main铁塔 {
    static int SIZE = 5005;
    static long[] f = new long[SIZE];
    static long[] sum = new long[SIZE];
    static long[] last = new long[SIZE];
    static long[] a = new long[SIZE];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; ++i) {
            a[i] = sc.nextInt();
            sum[i] = sum[i - 1] + a[i];
        }
        f[0] = 0;
        for (int i = 1; i <= n; ++i)
            for (int j = 0; j < i; ++j)
                if (sum[i] - sum[j] >= last[j]) {
                    f[i] = f[j] + 1;
                    last[i] = sum[i] - sum[j];
                }
        System.out.println(n - f[n]);
    }
}
/*
题目
你准备在一座山脚下盖房子定居。盖房子需要钢材，幸运的是，这里有排成一行的n座废弃的铁塔，从左到右编号为1~n，
其中第i座铁塔可以提供h[i]单位的钢材。
你需要把这些铁塔从左至右分成若干组，每组内铁塔编号必须是连续的，并且从左至右每一组铁塔的能提供的钢材总量单调不减。
最后，你可以用每组铁塔所提供的钢材构成一层上面小下面大的城堡。

负责处理钢材和建造城堡的公司对每一座铁塔收取1金币的费用，但会给每一层城堡（即每一组钢材）优惠1金币。这样一来，你要交的费用就是n
组数金币。因此你需要把这些铁塔分成尽量多组。
例如有8座铁塔，高度分别为 1,9,9,4,1,2,2,9，你最多把他们分成5组连续的铁塔：
1、9、9、4+1+2+2、9 每组提供的钢材总量为1、9、9、9、9 单调不减，费用为8-5=3枚金币。

输入
第一行一个整数n。
第二行n个用空格隔开的整数，第i个整数表示h[i]。

输出：
输出一个整数，表示最少付出的金币数，即(n-最多能分成的组数)

样例输入：
8
1 9 9 4 1 2 2 9

样例输出
3

数据范围
0<=n<=5000, 0<h[i]<=2147483647
 */
