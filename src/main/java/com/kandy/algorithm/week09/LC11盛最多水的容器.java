package com.kandy.algorithm.week09;

public class LC11盛最多水的容器 {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int ans = 0;
        while (i < j) {
            //高度较小者* 宽度j-i，更新答案
            ans = Math.max(ans, Math.min(height[i], height[j]) * (j - i));
            //谁小，谁就没用了，就缩谁
            if (height[i] < height[j]) i++; else j--;
        }
        return ans;
    }
}
