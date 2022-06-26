package com.kandy.algorithm.week10;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.PriorityQueue;

public class LC773滑动谜题_启发式搜索 {
    public int slidingPuzzle(int[][] board) {
        q = new PriorityQueue<>((x, y) -> Integer.compare(x.getKey(), y.getKey()));
        depth = new HashMap<>();
        String start = zip(board);
        String target = zip(new int[][]{{1, 2, 3}, {4, 5, 0}});
        q.add(new Pair<Integer, String>(evaluate(start), start));
        depth.put(start, 0);
        while (!q.isEmpty()) {
            String s = q.poll().getValue();
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

    int evaluate(String s) {
        int[] now = new int[6];
        for (int i = 0; i < 6; i++)
            if (s.charAt(i) != '0') {
                now[s.charAt(i) - '0'] = i;
            }
        // 123450
        int[] target = new int[]{0, 0, 1, 2, 3, 4};
        int ans = 0;
        for (int digit = 1; digit <= 5; digit++) {
            int nowx = now[digit] / 3;
            int nowy = now[digit] % 3;
            int targetx = target[digit] / 3;
            int targety = target[digit] % 3;
            ans += Math.abs(nowx - targetx) + Math.abs(nowy - targety);
        }
        return ans;
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
        q.add(new Pair<Integer, String>(depth.get(ns) + evaluate(ns), ns));
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

    PriorityQueue<Pair<Integer, String>> q;
    HashMap<String, Integer> depth;
}
