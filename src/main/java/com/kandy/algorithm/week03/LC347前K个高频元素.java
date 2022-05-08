package com.kandy.algorithm.week03;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 前K个高频元素 小根堆
 * 剑指Offer40.最小的k个数 大根堆
 */
public class LC347前K个高频元素 {
    public int[] topKFrequent(int[] nums, int k) {
        //哈希表记录每个数字出现的次数
        Map<Integer, Integer> numCounts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
        }

        // int[]的第一个元素代表数组的值，第二个元素代表了该值出现的次数 建小根堆 按照次数排序
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        for (Map.Entry<Integer, Integer> entry : numCounts.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                //当前元素的出现次数比堆顶多，删堆顶，新元素入优选队列
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                //不足k个 直接进入优先队列
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }
}
