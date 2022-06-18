package com.kandy.algorithm.week09;

/**
 * 访问数组是否会越界
 * 整数是否会溢出(加法变减法，乘法变除法)
 */
public class 字符串转整数模板_LC8 {
    public int myAtoi(String str) {
        int index = 0;
        if (str.length() == 0) return 0;

        //1. 忽略前导空格
        while (index < str.length() && str.charAt(index) == ' ')
            index++;

        int sign = 1;
        //2. 正负号
        if (index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        int val = 0;
        //3.处理数字直到非数字或结尾
        while (index < str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
            int digit = str.charAt(index) - '0';

            //通过val * 10 + digit > Integer.MAX_VALUE 移项得到
            if (val > (Integer.MAX_VALUE - digit) / 10)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            val = val * 10 + digit;
            index++;
        }
        return val * sign;
    }
}
