package com.kandy.algorithm.week06.homework;

import java.util.List;


/**
 * Bottom-up：f(i,j)=min(f(i+1,j),f(i+1,j+1)) + triangle(i,j)  f(i,j) 表示从(i,j)到最后一层的最小路径和 每步只能从下面 或右下来
 */
public class LC三角形最小路径和 {
    //Bottom-up 状态数组：二维数组
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];

        //Bottom-up
        for (int i = n - 1; i >= 0; i--) { //自底向上遍历
            for (int j = 0; j < triangle.get(i).size(); j++) { //同一层
                if (i == n - 1) { //最后一层
                    f[i][j] = triangle.get(i).get(j);
                } else {
                    //状态转移方法  上一层由它下面一层相邻2个点计算出
                    f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
        }
        return f[0][0];
    }

    //Bottom-up 状态数组：一维数组（状态压缩）
    public int minimumTotal2(List<List<Integer>> triangle) {
        List<Integer> bottom = triangle.get(triangle.size() - 1);
        //用最后一层长度来初始化dp数组
        int[] f = new int[bottom.size()];

        //最后一行的最小路径后，就是它自身，用它来初始化dp
        for (int i = 0; i < f.length; i++) {
            f[i] = bottom.get(i);
        }
        //从倒数第二行开始迭代
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                f[j] = Math.min(f[j], f[j + 1]) + triangle.get(i).get(j);
            }
        }
        return f[0];
    }
}
