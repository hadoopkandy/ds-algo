package com.kandy.algorithm.week09.homework;

public class LC541反转字符串II {
    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        //每次i增加2k
        for (int i = 0; i < n; i += 2 * k) {
            //反转2k里的前k个
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    //套用反转字符串模板
    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

}
