package com.kandy.algorithm.campus.vip;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用三个线程控制循环输出10次ABC
 * 题目：有A,B,C三个线程, A线程输出A, B线程输出B, C线程输出C，要求, 同时启动三个线程, 按顺序输出ABC, 循环10次
 * 解题思路：要按顺序输出ABC, 循环10次，就要控制三个线程同步工作，也就是说要让三个线程轮流输出，直到10个ABC全部输出则结束线程。这里用一个Lock对象来控制三个线程的同步。用一个int型变量state标识由那个线程输出。
 *
 * @author Kandy
 */
public class ABC {
    private static int state = 0;

    public static void main(String[] args) {
        final Lock l = new ReentrantLock();

        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                while (state <= 30) {
                    l.lock();
                    if (state % 3 == 0) {
                        System.out.println("A");
                        state++;
                    }
                    l.unlock();
                }
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                while (state <= 30) {
                    l.lock();
                    if (state % 3 == 1) {
                        System.out.println("B");
                        state++;
                    }
                    l.unlock();
                }
            }
        });
        Thread C = new Thread(new Runnable() {
            @Override
            public void run() {
                while (state <= 30) {
                    l.lock();
                    if (state % 3 == 2) {
                        System.out.println("C");
                        state++;
                    }
                    l.unlock();
                }
            }
        });
        A.start();
        B.start();
        C.start();
    }

}