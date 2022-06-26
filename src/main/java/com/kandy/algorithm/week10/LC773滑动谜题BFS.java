package com.kandy.algorithm.week10;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LC773滑动谜题BFS {
    public int slidingPuzzle(int[][] board) {
        q = new LinkedList<>();
        depth = new HashMap<>();
        String start = zip(board);
        String target = zip(new int[][]{{1, 2, 3}, {4, 5, 0}});
        q.add(start);
        depth.put(start, 0);
        while (!q.isEmpty()) {
            String s = q.poll();
            int pos = findZeroIndex(s);
            // 012345 下标
            // 0 1 2
            // 3 4 5
            if (pos >= 3) expand(s, pos, pos - 3);
            if (pos <= 2) expand(s, pos, pos + 3);
            if (pos % 3 != 0) expand(s, pos, pos - 1);
            if (pos % 3 != 2) expand(s, pos, pos + 1);
            if (depth.containsKey(target)) return depth.get(target);
        }
        return -1;
    }

    void expand(String s, int pos, int other) {
        if (pos > other) {
            int temp = pos;
            pos = other;
            other = temp;
        }
        String ns = s.substring(0, pos) + s.charAt(other) + s.substring(pos + 1, other) + s.charAt(pos) + s.substring(other + 1);
        if (depth.containsKey(ns)) return;
        depth.put(ns, depth.get(s) + 1);
        q.add(ns);
    }

    String zip(int[][] board) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                res.append((char) ('0' + board[i][j]));
        return res.toString();
    }

    int findZeroIndex(String s) {
        for (int i = 0; i < 6; i++) if (s.charAt(i) == '0') return i;
        return -1;
    }

    Queue<String> q;
    HashMap<String, Integer> depth;
}
