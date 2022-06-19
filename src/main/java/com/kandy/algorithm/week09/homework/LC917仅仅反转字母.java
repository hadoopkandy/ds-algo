package com.kandy.algorithm.week09.homework;

public class LC917仅仅反转字母 {
    //双指针
    public String reverseOnlyLetters(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int left = 0, right = n - 1;
        //双指针
        while (left < right) {
            //从左往右，找到字母
            while (left < right && !isLetter(s.charAt(left))) { // 判断左边是否扫描到字母
                left++;
            }
            //从右往左，找到字母
            while (left < right && !isLetter(s.charAt(right))) { // 判断右边是否扫描到字母
                right--;
            }
            if (left >= right) {
                break;
            }
            swap(arr, left, right);
            left++;
            right--;
        }
        return new String(arr);
    }

    public void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    //是否是字母
    boolean isLetter(char ch) {
        return  ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
    }


}
