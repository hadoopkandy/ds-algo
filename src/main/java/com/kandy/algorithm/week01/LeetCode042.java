package com.kandy.algorithm.week01;

import java.util.Stack;

/**
 * 42.接雨水
 */
public class LeetCode042 {
    //横条 单调栈
    public int trap(int[] heights) {
        Stack<Rect> s = new Stack<>();
        int ans = 0;
        for (int height : heights) {
            int accumulatedWidth = 0;
            while (!s.empty() && s.peek().height <= height) {
                int bottom = s.peek().height;//底
                accumulatedWidth += s.peek().width;
                s.pop();

                if (s.empty()) continue; //水从左边流走了
                //以bottom为底的横块水,最高可以到up(左右两侧高度的min)
                int up = Math.min(height, s.peek().height);
                ans += accumulatedWidth * (up - bottom);
            }
            Rect rect = new Rect();
            rect.width = accumulatedWidth + 1;
            rect.height = height;
            s.push(rect);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        LeetCode042 code = new LeetCode042();
        final int trap = code.trap2(heights);
        System.out.println(trap);
    }

    /**
     * 竖条 前后缀最大值
     *
     * @param heights
     * @return
     */
    public int trap2(int[] heights) {
        int n = heights.length;
        int[] preMax = new int[n];
        int[] sufMax = new int[n];
        preMax[0] = heights[0];
        for (int i = 1; i < n; i++) preMax[i] = Math.max(preMax[i - 1], heights[i]);

        sufMax[n - 1] = heights[n - 1];
        for (int i = n - 2; i >= 0; i--) sufMax[i] = Math.max(sufMax[i + 1], heights[i]);

        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            int up = Math.min(preMax[i - 1], sufMax[i + 1]);  //前后缀的较小者
            int bottom = heights[i];
            if (up > bottom) ans += up - bottom;
        }
        return ans;
    }

    private class Rect {
        public int height;
        public int width;
    }
}
