package com.kandy.algorithm.week07.homework;

public class LC55跳跃游戏 {
    //动态规划
    //f[i]表示能否到达位置i，对每个位置i判断能否通过前面的位置跳跃过来
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] f = new boolean[n];//默认false
        f[0] = true;//第一个位置能到达
        for (int i = 1; i < nums.length; i++) {
            //注意j倒序循环 比正序循环要快一些
            for (int j = i-1; j >=0; j--) {
                //当前位置j能达到，并且当前位置j加上能跳跃的长度超过i，则i这个位置可以到达
                f[i] = f[j] && nums[j] + j >= i;
                if (f[i]) break; //i这个位置可以到达，则可以退出本层循环
            }
        }
        return f[n - 1];
    }
}
