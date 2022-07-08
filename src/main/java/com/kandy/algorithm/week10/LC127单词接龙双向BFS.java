package com.kandy.algorithm.week10;

import java.util.*;

public class LC127单词接龙双向BFS {
    //方法一：普通BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>();
        //层数
        HashMap<String, Integer> depth = new HashMap<>();
        Queue<String> q = new LinkedList<>();

        for (String s : wordList) wordSet.add(s);
        if (!wordSet.contains(endWord)) return 0;

        depth.put(beginWord, 1);
        q.add(beginWord);

        while (!q.isEmpty()) {
            String s = q.poll();
            //改哪个字母，改成什么
            for (int i = 0; i < s.length(); i++)
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (ch == s.charAt(i)) continue;
                    //改i这个位置
                    String ns = s.substring(0, i) + ch + s.substring(i + 1);
                    if (!wordSet.contains(ns)) continue;
                    if (depth.containsKey(ns)) continue;//判重，算过了，就不要了
                    depth.put(ns, depth.get(s) + 1);
                    q.add(ns);
                    if (ns.equals(endWord)) {
                        return depth.get(ns);
                    }
                }
        }
        return 0;
    }

    //方法二：双向BFS
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        wordSet = new HashSet<>();
        for (String s : wordList) wordSet.add(s);
        if (!wordSet.contains(endWord)) return 0;

        depthBegin = new HashMap<>();
        depthEnd = new HashMap<>();
        depthBegin.put(beginWord, 1);
        depthEnd.put(endWord, 1);

        Queue<String> qBegin = new LinkedList<>();
        Queue<String> qEnd = new LinkedList<>();
        qBegin.add(beginWord);
        qEnd.add(endWord);
        int currDepth = 1;
        while (!qBegin.isEmpty() || !qEnd.isEmpty()) {
            // qBegin扩展一层
            while (!qBegin.isEmpty() && depthBegin.get(qBegin.peek()) == currDepth) {
                int res = expand(qBegin, depthBegin, depthEnd);
                if (res != -1) return res;
            }
            // qEnd扩展一层
            while (!qEnd.isEmpty() && depthEnd.get(qEnd.peek()) == currDepth) {
                int res = expand(qEnd, depthEnd, depthBegin);
                if (res != -1) return res;
            }
            currDepth++;
        }
        return 0;
    }

    int expand(Queue<String> q, HashMap<String, Integer> depth, HashMap<String, Integer> depthOther) {
        if (q.isEmpty()) return -1;
        String s = q.poll();
        for (int i = 0; i < s.length(); i++)
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == s.charAt(i)) continue;
                String ns = s.substring(0, i) + ch + s.substring(i + 1);
                if (!wordSet.contains(ns)) continue;
                if (depth.containsKey(ns)) continue;
                if (depthOther.containsKey(ns)) return depth.get(s) + depthOther.get(ns);
                depth.put(ns, depth.get(s) + 1);
                q.add(ns);
            }
        return -1;
    }

    HashSet<String> wordSet;
    HashMap<String, Integer> depthBegin, depthEnd;

/*
最短转换序列:
hbo -> hbw -> qbw -> qbx
              hww    qbq
       hco    hcd
              hcj
       abo    abq
              ado
 */
    public static void main(String[] args) {
        LC127单词接龙双向BFS lc = new LC127单词接龙双向BFS();
        System.out.println(lc.ladderLength2("hbo", "qbx", Arrays.asList(new String[]{"abo", "hco", "hbw", "ado", "abq", "hcd", "hcj", "hww", "qbq", "qby", "qbz", "qbx", "qbw"})));
    }
}
