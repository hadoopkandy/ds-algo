package com.kandy.algorithm.week04.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC911在线选举 {
   public static class TopVotedCandidate {
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

    public static void main(String[] args) {
        int[] persons = new int[]{0, 1, 1, 0, 0, 1, 0};
        int[] times = new int[]{0, 5, 10, 15, 20, 25, 30};
        TopVotedCandidate candidate = new TopVotedCandidate(persons,times);
        System.out.println(candidate.q(3));// 返回 0 ，在时刻 3 ，票数分布为 [0] ，编号为 0 的候选人领先。
        System.out.println(candidate.q(12));// 返回 1 ，在时刻 12 ，票数分布为 [0,1,1] ，编号为 1 的候选人领先。
        System.out.println(candidate.q(25));// 返回 1 ，在时刻 25 ，票数分布为 [0,1,1,0,0,1] ，编号为 1 的候选人领先。（在平局的情况下，1 是最近获得投票的候选人）。
        System.out.println(candidate.q(15));// 返回 0
        System.out.println(candidate.q(24));// 返回 0
        System.out.println(candidate.q(8));// 返回 1
    }

}
