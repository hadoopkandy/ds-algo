package com.kandy.algorithm.week09;

public class LC11盛最多水的容器 {
    //解题步骤：
    //1.两重循环枚举，找冗余
    //2.发现关键，盛多少水是由短的那一根决定的，短的算完就没用了
    //3.双指针，两个指针从头到尾向中间移动，每次移动短的那根
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
