package com.kandy.algorithm.week02;

/**
 * 11.盛最多水的容器
 */
public class LC11盛最多水的容器 {
    //双指针
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        while (left < right) {
            //两个指针指向的数字中较小值*指针之间的距离
            int area = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(ans, area);
            //移动较短边的指针
            if (height[left] <= height[right]) {
                ++left;
            } else {
                --right;
            }
        }
        return ans;
    }

    //暴力解法超时
    public int maxArea2(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxarea;
    }
}
