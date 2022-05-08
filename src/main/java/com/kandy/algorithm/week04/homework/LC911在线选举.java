package com.kandy.algorithm.week04.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC911在线选举 {
    class TopVotedCandidate {
        private List<Integer> tops; //预计算
        private int[] times; //二分查找

        public TopVotedCandidate(int[] persons, int[] times) {
            this.tops = new ArrayList<>();
            Map<Integer, Integer> voteCounts = new HashMap<>(); // 记录不同候选人的得票数
            voteCounts.put(-1, 1);
            int top = -1;//当前领先的候选人
            for (int i = 0; i < persons.length; i++) {
                int p = persons[i];
                //当前候选人得票数+1
                voteCounts.put(p, voteCounts.getOrDefault(p, 0) + 1);
                //如果当前候选人的得票数>=领先的候选人，就更新答案
                if (voteCounts.get(p) >= voteCounts.get(top)) {
                    top = p;
                }
                //将领先的候选人加入结果
                tops.add(top);
            }
            this.times = times;
        }

        public int q(int t) {
            //前继型 找到满足 times[right] <= t 的最大的right
            int left = -1; //表示无解
            int right = times.length - 1;
            while (left < right) {
                int mid = (left + right + 1) / 2;
                if (times[mid] <= t) {
                    left = mid; //在<=里面要大的
                } else {
                    right = mid - 1;
                }
            }
            return tops.get(right);
        }
    }

}
