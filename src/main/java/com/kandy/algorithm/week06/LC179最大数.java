package com.kandy.algorithm.week06;

import java.util.Arrays;

public class LC179最大数 {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) ss[i] = "" + nums[i];

        //[3,30,34,5,9] 排序后：[9, 5, 34, 3, 30] 注意3排在30前面
        Arrays.sort(ss, (a, b) -> {
            String sa = a + b, sb = b + a;
            return sb.compareTo(sa);
        });
        System.out.println(Arrays.toString(ss));

        StringBuilder sb = new StringBuilder();
        for (String s : ss) sb.append(s);
        int len = sb.length();
        int k = 0;//截断无效的0
        while (k < len - 1 && sb.charAt(k) == '0') k++;
        return sb.substring(k);
    }

}
