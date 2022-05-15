package com.kandy.algorithm.week05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 将一个基因序列 AA 变化至另一个基因序列 BB，需要满足以下条件：
 * 序列 AA 与 序列 BB 之间只有一个字符不同;
 * 变化字符只能从A C G T中进行选择;
 * 变换后的序列 BB 一定要在字符串数组 bank 中
 * <p>
 * 广搜求层数
 * 点：基因序列  变化一次产生一条边
 */
public class LC433最小基因变化 {
    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> hashBank = new HashSet<>();
        //记录每个点的层数
        HashMap<String, Integer> depth = new HashMap<>();
        depth.put(start, 0);//start是0层

        //将基因库转为HashSet便于判断是否存在
        for (String seq : bank) hashBank.add(seq);
        if (!hashBank.contains(end)) return -1;

        Queue<String> q = new LinkedList<>();
        q.add(start);
        char[] gene = new char[]{'A', 'C', 'G', 'T'};
        while (!q.isEmpty()) {
            String s = q.poll();
            for (int i = 0; i < 8; i++) //8个位置
                for (int j = 0; j < 4; j++) //每个位置4种取值
                    if (s.charAt(i) != gene[j]) {

                        //将i 这个位置变成gene[j]
                        //String ns = s.substring(0, i) + gene[j] + s.substring(i + 1);
                        StringBuffer sb = new StringBuffer(s);
                        sb.setCharAt(i, gene[j]);
                        String ns = sb.toString();

                        // ns not in hashBank,continue
                        if (!hashBank.contains(ns)) continue;
                        // ns in depth,每个点只需要访问一次，第一次就是最少层数
                        if (depth.containsKey(ns)) continue;
                        depth.put(ns, depth.get(s) + 1);
                        q.add(ns);
                        if (ns.equals(end)) {
                            return depth.get(ns);
                        }
                    }
        }
        return -1;
    }
}
