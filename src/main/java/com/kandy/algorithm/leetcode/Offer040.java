package com.kandy.algorithm.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 *
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 */
public class Offer040 {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] vec = new int[k];
        if (k == 0) { // 排除 0 的情况
            return vec;
        }
        //Java堆结构PriorityQueue完全解析 https://blog.csdn.net/u013309870/article/details/71189189
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2,o1));
        for (int i = 0; i < k; ++i) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; ++i) {
            //peek方法只返回头元素，不remove
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; ++i) {
            vec[i] = queue.poll();
        }
        return vec;
    }

    public static void main(String[] args) {
        Offer040 offer = new Offer040();
        System.out.println(Arrays.toString(offer.getLeastNumbers(new int[]{9,8,7,6,5,4,3,2,1},4)));
        System.out.println(Arrays.toString(offer.getLeastNumbers(new int[]{0,1,2,1},1)));
    }
}
