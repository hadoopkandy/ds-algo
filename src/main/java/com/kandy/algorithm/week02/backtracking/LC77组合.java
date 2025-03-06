package com.kandy.algorithm.week02.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC77组合 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return result;
    }

    /**
     * 在集合[1,2,3,4]取1之后，下一层递归，就要在[2,3,4]中取数了，那么下一层递归如何知道从[2,3,4]中取数呢，靠的就是startIndex
     * @param startIndex 就是防止出现重复的组合
     */
    public void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        //剪枝优化：
        //1.已经选择的元素个数：path.size();
        //2.还需要的元素个数为: k - path.size();
        //3.在集合n中至多要从该起始位置 : n - (k - path.size()) + 1，开始遍历
        //为什么有个+1呢，因为包括起始位置，我们要是一个左闭的集合。
        //举个例子，n = 4，k = 3， 目前已经选取的元素为0（path.size为0），n - (k - 0) + 1 即 4 - ( 3 - 0) + 1 = 2
        //从2开始搜索都是合理的，可以是组合[2, 3, 4]。
        //所以剪枝优化： i <= n - (k - path.size()) + 1
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backtracking(n, k, i + 1);
            path.removeLast();
        }
    }
}
