package com.kandy.algorithm.leetcode;

/**
 * 8. 字符串转换整数 (atoi)
 */
public class Solution008 {
    public int myAtoi(String s) {
        if(s== null || s.length() == 0){
            return 0;
        }

        int sign = 1;
        int index = 0;
        while(index < s.length() && s.charAt(index) == ' ')
            index++;

        if(index == s.length()){
            return 0;
        }
        if(s.charAt(index) > '9' || s.charAt(index) < '0') {
            if(s.charAt(index) == '+'){
                sign = 1;
                index++;
            }else if(s.charAt(index) == '-'){
                sign = -1;
                index++;
            }else{
                return 0;
            }
        }
        //Integer.MIN_VALUE = -2147483648
        //Integer.MAX_VALUE = 2147483647
        int res = 0;
        while(index < s.length() && s.charAt(index) <= '9' && s.charAt(index) >= '0'){
            if(sign == 1 && (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && s.charAt(index) > '7'))
                    || (sign == -1 && (res * sign < Integer.MIN_VALUE / 10 || (res * sign == Integer.MIN_VALUE / 10 && s.charAt(index) > '8')))){
                return sign == 1 ? Integer.MAX_VALUE :  Integer.MIN_VALUE;
            }
            res *= 10;
            res += s.charAt(index)-'0';
            index++;
        }
        return res * sign;
    }


    public static void main(String[] args) {
        Solution008 sol = new Solution008();
        System.out.println(sol.myAtoi("4193 with words"));
        System.out.println(sol.myAtoi("21474836489 with words"));
    }
}
