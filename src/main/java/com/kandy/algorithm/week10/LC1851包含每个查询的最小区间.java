package com.kandy.algorithm.week10;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LC1851包含每个查询的最小区间 {
    public class Event {
        int pos;
        int len;
        int type;

        public Event(int pos, int len, int type) {
            this.pos = pos;
            this.len = len;
            this.type = type;
        }
    }

    public int[] minInterval(int[][] intervals, int[] queries) {
        // 事件：
        // 1 +4 在1这里发生事件：产生长度为4的区间
        // 2 +3
        // 2 询问事件 答案：3
        // 3 +4
        // 3 询问事件 答案：3
        // 4 +1
        // 4 询问事件 答案：1
        // 4 -4
        // 4 -3
        // 4 -1
        // 5 询问事件 答案：4
        // 6 -4
        List<Event> events = new ArrayList<>();
        for (int[] interval : intervals) {
            int len = interval[1] - interval[0] + 1;
            events.add(new Event(interval[0], len, 1));
            events.add(new Event(interval[1], len, -1));
        }
        for (int i = 0; i < queries.length; i++) {
            events.add(new Event(queries[i], i, 0));
        }
        // 双关键字排序，pos升序，type倒序
        events.sort((a, b) -> (a.pos != b.pos ? a.pos - b.pos : b.type - a.type));

        // <len, count>
        TreeMap<Integer, Integer> f = new TreeMap<>();
        int[] ans = new int[queries.length];
        for (Event event : events) {
            if (event.type == 1) {
                f.put(event.len, f.getOrDefault(event.len, 0) + 1);
            } else if (event.type == -1) {
                f.put(event.len, f.getOrDefault(event.len, 0) - 1);
                if (f.get(event.len) == 0) f.remove(event.len);
            } else {
                // 对于询问，len这一变量记录了原始下标
                ans[event.len] = f.isEmpty() ? -1 : f.firstKey();
            }
        }
        return ans;
    }
}
