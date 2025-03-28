package com.kandy.algorithm.week05;

import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/find-median-from-data-stream/description/
 */
public class 数据流中位数MedianFinder {
    PriorityQueue<Integer> queMin;//小于等于中位数的数,大顶堆,存储动态数据集合中前半部分的数据
    PriorityQueue<Integer> queMax;//大于中位数的数,小顶堆,存储动态数据集合的后半部分数据

    public 数据流中位数MedianFinder() {
        queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
        queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
    }

    public void addNum(int num) {
        //num 小于等于中位数，我们需要将该数添加到 queMin中
        //当累计添加的数的数量为 0 时我们将num添加到 queMin
        if (queMin.isEmpty() || num <= queMin.peek()) {
            queMin.offer(num);
            //queMin保持比queMax最多多一个
            if (queMax.size() + 1 < queMin.size()) {
                queMax.offer(queMin.poll());
            }
        } else {
            queMax.offer(num);
            if (queMax.size() > queMin.size()) {
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        }
        return (queMin.peek() + queMax.peek()) / 2.0;
    }
}
