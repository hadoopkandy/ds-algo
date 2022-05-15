package com.kandy.algorithm.week05;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC1122数组的相对排序 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //利用哈希表对arr2建立数值到索引的映射
        Map<Integer, Integer> arr2orders = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            arr2orders.put(arr2[i], i);
        }
        Integer[] res = new Integer[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = arr1[i];
        }

        //自定义比较函数
        Arrays.sort(res, 0, res.length, (x, y) -> {
            int xPos = arr2orders.containsKey(x) ? arr2orders.get(x) : arr2.length;
            int yPos = arr2orders.containsKey(y) ? arr2orders.get(y) : arr2.length;
            return xPos == yPos ? Integer.compare(x, y) : Integer.compare(xPos, yPos);
        });

        for (int i = 0; i < res.length; i++) {
            arr1[i] = res[i];
        }

        return arr1;
    }
    //计数排序
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int[] ans = new int[arr1.length];
        int n = 0;

        //保证取值范围在[1,1000]
        int[] count = new int[1001];
        for (int i = 0; i < arr1.length; i++)
            count[arr1[i]]++;

        // 按照arr2的顺序来取
        for (int i = 0; i < arr2.length; i++)
            while (count[arr2[i]] > 0) {
                count[arr2[i]]--;
                ans[n] = arr2[i];
                n++;
            }

        // 剩余的没在arr2出现过，按顺序取出
        for (int val = 0; val <= 1000; val++)
            while (count[val] > 0) {
                count[val]--;
                ans[n] = val;
                n++;
            }
        return ans;
    }
}
