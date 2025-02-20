package com.kandy.algorithm.week01;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/
 * 后缀表达式求值
 * 150. 逆波兰表达式求值
 * 建一个用于存数的的栈,逐一扫描后缀表达式中的元素
 * 如果遇到一个数,则把该数入栈
 * 如果遇到运算符,就取出栈顶的两个数进行计算,然后把结果入栈
 * 扫描完成后,栈中恰好剩下一个数,就是该后缀表达式的值
 * 时间复杂度O(N)
 */
public class L11_C150后缀表达式求值 {
    public int evalRPN(String[] tokens) {
        Stack<Long> s = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                long y = s.peek();
                s.pop();
                long x = s.peek();
                s.pop();
                long z = calc(x, y, token);
                s.push(z);
            } else {
                s.push(Long.parseLong(token)); // 字符串转化为整数
            }
        }
        return s.peek().intValue();
    }

    long calc(long x, long y, String op) {
        if (op.equals("+")) return x + y;
        if (op.equals("-")) return x - y;
        if (op.equals("*")) return x * y;
        if (op.equals("/")) return x / y;
        return 0;
    }

    public static void main(String[] args) {
        L11_C150后缀表达式求值 code = new L11_C150后缀表达式求值();
        String[] tokens = new String[]{"2","1","+","3","*"};
        System.out.println(code.evalRPN(tokens));
    }

}
