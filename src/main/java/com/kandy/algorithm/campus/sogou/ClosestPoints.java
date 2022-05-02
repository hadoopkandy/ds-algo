package com.kandy.algorithm.campus.sogou;

import java.util.Scanner;

/**
 * 有n个点随机分布在原点周围，找出其中距离最近的两个点，输入时n个点<x,y>坐标数组(算出距离最近的两个点，把这两个点在输入数组中的下标)从小到大输出。
 * 3
 * 1.0 1.0002
 * 3.03 3.023
 * 0.0 -0.001
 */
public class ClosestPoints {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Double[][] points = new Double[N][2];
        while (true) {
            for (int i = 0; i < N; i++) {
                points[i][0] = scanner.nextDouble();
                points[i][1] = scanner.nextDouble();
            }
            break;
        }
        Double min = Math.pow(points[0][0] - points[1][0], 2) + Math.pow(points[0][1] - points[1][1], 2);
        int flag1 = 0;
        int flag2 = 0;
        Double result = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                result = Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2);
                if (min > result) {
                    min = result;
                    flag1 = i;
                    flag2 = j;
                }
            }
        }
        System.out.println("Closest points:" + flag1 + "," + flag2);
    }

}
