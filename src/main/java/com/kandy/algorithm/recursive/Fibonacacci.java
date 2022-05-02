package com.kandy.algorithm.recursive;

/**
 * 斐波那契数列
 * fib(10)，fib被调用的次数：177
 * 基本思想：
 * fib(10):1次
 * bib(9):1次
 * fib(8):被fib(10)、fib(9)调用=1+1=2
 * 同理：fib(7):1+2=3
 * fib(6):fib(8)+f(7)=5
 * fib(5):fib(7)+fib6(6)=8
 * f(4:):fib(6)+fib(5)=13
 * f(3):fib(5)+fib(4)=21
 * f(2):fib(4)+fib(3)=34
 * f(1):fib(3)+fib(2)=55
 * f(0):f(2)=34
 * 总调用次数=55+34+21+13+8++5+3+2+1+1=177
 */
public class Fibonacacci {
    //private static int count=0;
    public static int fib(int n) {
        //count++;
        if (n == 0)
            return 1;
        else if (n == 1)
            return 2;
        else
            return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fib(10));
        // System.out.println("调用次数："+ count);
    }


}
