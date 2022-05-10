package com.kandy.algorithm.week05;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC1122数组的相对排序 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> arr2orders = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            arr2orders.put(arr2[i], i);
        }
        Integer[] res = new Integer[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = arr1[i];
        }

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
}
