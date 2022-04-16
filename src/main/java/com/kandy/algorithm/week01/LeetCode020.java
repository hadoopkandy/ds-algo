package com.kandy.algorithm.week01;

import java.util.Stack;

/**
 * 20. 有效的括号
 */
public class LeetCode020 {
    public boolean isValid(String s) {
        Stack<Character> a = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') a.push(ch);
            else {
                if (a.empty()) return false;
                //取出栈顶的左括号，并判断它们是否是相同的类型 peek方法只返回头元素，不remove
                if (ch == ')' && a.peek() != '(') return false;
                if (ch == ']' && a.peek() != '[') return false;
                if (ch == '}' && a.peek() != '{') return false;
                a.pop();
            }
        }
        return a.empty();
    }

    public static void main(String[] args) {
        LeetCode020 sol = new LeetCode020();
        System.out.println(sol.isValid("()"));
        System.out.println(sol.isValid("()[]{}"));
        System.out.println(sol.isValid("(]"));
        System.out.println(sol.isValid("([)]"));
        System.out.println(sol.isValid("{[]}"));
    }
}
