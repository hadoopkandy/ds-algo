package com.kandy.algorithm.campus.baidu;

import java.util.Scanner;

/**
 * 商队运输费
 */
public class TransportationFee {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] d = new int[11][11];
        int q, p, l;
        while (true) {
            for (int i = 1; i < n; i++) {
                p = scanner.nextInt();
                q = scanner.nextInt();
                l = scanner.nextInt();
                d[p][q] = -1 * l;
                d[q][p] = -1 * l;
            }
            break;
        }


        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (d[i][k] < 0 && d[i][j] == 0 && i != j && d[k][j] < 0 && (d[i][j] > d[i][k] + d[k][j])) {
                        d[i][j] = d[i][k] + d[k][j];

                    }
                }
            }
        }
        int min = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {


                if (d[i][j] < min) {
                    min = d[i][j];
                }

            }

        }

        min = -1 * min;

        int s = 0;
        for (int i = 1; i <= min; i++) {
            s = s + 10 + i;
        }
        System.out.println(s);

    }
}