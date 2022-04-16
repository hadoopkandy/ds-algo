package com.kandy.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 150. 逆波兰表达式求值
 *
 */
public class Solution150 {
    public  int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public  boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    public static void main(String[] args) {
        Solution150 sol =  new Solution150();
        System.out.println(sol.evalRPN(new String[]{"2","1","+","3","*"}));
    }
}
