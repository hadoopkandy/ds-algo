package com.kandy.algorithm.week10;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LC218天际线问题 {
    public class Event {
        int pos;
        int height;
        int type;
        int index;

        Event(int pos, int height, int type, int index) {
            this.pos = pos;
            this.height = height;
            this.type = type;
            this.index = index;
        }
    }

    ;

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // <height, index> 大根堆
        PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>((x, y) -> Integer.compare(y.getKey(), x.getKey()));
        // 删除标记
        boolean[] removed = new boolean[buildings.length];
        Arrays.fill(removed, false);

        // 关键事件
        Event[] events = new Event[2 * buildings.length];
        for (int i = 0; i < buildings.length; i++) {
            int left = buildings[i][0];
            int right = buildings[i][1];
            int height = buildings[i][2];
            events[i * 2] = new Event(left, height, 1, i);
            events[i * 2 + 1] = new Event(right, height, -1, i);
        }
        Arrays.sort(events, (x, y) -> x.pos - y.pos);

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < events.length; i++) {
            if (events[i].type == 1) {
                q.add(new Pair<Integer, Integer>(events[i].height, events[i].index));
            } else {
                removed[events[i].index] = true;
            }
            // 在events[i].pos处可能发生多个事件，全部处理完后再考虑答案
            if (i == events.length - 1 || events[i].pos != events[i + 1].pos) {
                while (!q.isEmpty() && removed[q.peek().getValue()]) q.poll();
                int height = q.isEmpty() ? 0 : q.peek().getKey();
                if (ans.isEmpty() || height != ans.get(ans.size() - 1).get(1))
                    ans.add(Arrays.asList(new Integer[]{events[i].pos, height}));
            }
        }
        return ans;
    }
}
