package com.kandy.algorithm.campus.jd;

import java.util.Scanner;

/**
 * 5->3121
 */
public class DivideApples {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int total = 0;
        if (n > 1 && n < 9) {
            total = (int) Math.pow(n, n) - n + 1;
        }
        System.out.println(total);
        scanner.close();
    }

}
