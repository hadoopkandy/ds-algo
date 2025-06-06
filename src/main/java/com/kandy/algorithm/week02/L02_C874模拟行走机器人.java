package com.kandy.algorithm.week02;

import java.util.HashSet;

/**
 * https://leetcode.cn/problems/walking-robot-simulation/description/
 * 874. 模拟行走机器人
 * 可以用set 或者map存储障碍物,从而快速判断一个格子里有没有障碍
 * 可以利用方向数组简化实现（代替if）
 */
public class L02_C874模拟行走机器人 {
    public int robotSim(int[] commands, int[][] obstacles) {
        //将障碍物数组变成集合，便于查找
        HashSet<Long> obs = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obs.add(calcHash(obstacle));
        }
        //模拟机器人行走，坐标、方向
        int x = 0, y = 0;
        int dir = 0; // N=0, E=1, S=2, W=3
        // 网格中行走题目，技巧：方向数组 北、东、南、西(顺时针4个方向)
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int ans = 0;
        for (int command : commands) {
            if (command == -1) {
                dir = (dir + 1) % 4; //右转
            } else if (command == -2) {
                //dir = (dir -1 + 4)%4
                dir = (dir + 3) % 4; //左转
            } else {
                // 一步一步走，根据当前的坐标计算出当前command的坐标
                for (int i = 0; i < command; i++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    // 如果obs.contains(...)，是障碍物
                    if (obs.contains(calcHash(new int[]{nx, ny}))) break;
                    //如果不是障碍物就走一步
                    x = nx;
                    y = ny;
                    ans = Math.max(ans, x * x + y * y);
                }
            }
        }
        return ans;
    }

    /*string calcHash(const vector<int>& obstacle) {
        return to_string(obstacle[0]) + "," + to_string(obstacle[1]);
    }*/
    //行号 * 列数 + 列号
    long calcHash(int[] obstacle) {
        return (obstacle[0] + 30000) * 60001l + (obstacle[1] + 30000);
    }
}
