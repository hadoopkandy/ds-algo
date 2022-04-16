package com.kandy.algorithm.week01;

import java.util.Arrays;
import java.util.Stack;

/**
 * 85. 最大矩形
 *
 * 单调栈题目代码套路：
 * for 每个元素
 * while(栈顶元素与新元素不满足单调性)  累加宽表、更新答案、出栈
 * 入栈
 */
public class LeetCode085 {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;//行数
        if(rows == 0) return 0;
        int cols = matrix[0].length;//列数
        int[] heights = new int[cols];//计算每行柱状图的高度
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //遇到1 则柱状图高度累加
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            /**
             heights值变化过程：
             第1次： [1, 0, 1, 0, 0]
             第2次： [2, 0, 2, 1, 1]
             第3次： [3, 1, 3, 2, 2]
             第4次:  [4, 0, 0, 3, 0]
             */
            res = Math.max(res, largestRectangleArea(heights));
        }
        return res;
    }
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] heights_with_zero = Arrays.copyOf(heights, n + 1);
        heights_with_zero[n] = 0; // 帮助我们在最后把栈清空
        Stack<Rect> s = new Stack<>();
        int ans = 0;
        //每个柱子入栈各出栈一次,2nO(n)
        //第一步:for每个元素
        for (Integer h : heights_with_zero) {
            int accumulated_width = 0;
            //第二步：while (栈顶不满足高度单调性) 累加宽度，出栈   删栈顶、累加宽度、更新答案
            while (!s.empty() && s.peek().height >= h) {
                accumulated_width += s.peek().width;
                ans = Math.max(ans, accumulated_width * s.peek().height);
                s.pop();
            }
            //第三步：新元素入栈
            Rect rect = new Rect();
            rect.height = h;
            rect.width = accumulated_width + 1;
            s.push(rect);
        }
        return ans;

    }

    private class Rect {
        public int height;
        public int width;
    }
}


