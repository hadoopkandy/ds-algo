package com.kandy.algorithm.week04.homework;

public class LC875爱吃香蕉的珂珂 {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;//下界每小时1根
        int max_pipe = 0;
        for (int pipe : piles) {
            max_pipe = Math.max(max_pipe, pipe);
        }
        int right = max_pipe + 1; //上界最大堆+1
        while (left < right) {
            int mid = (left + right) / 2;
            if (possible(piles, h, mid)) {
                right = mid; //满足条件较小
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    //速度为每小时speed根，是否能在h小时内吃完
    boolean possible(int[] piles, int h, int speed) {
        int need = 0;
        for (int pipe : piles) {
            need += (pipe - 1) / speed + 1; // pile除speed向上取整
        }
        return need <= h;
    }
}
