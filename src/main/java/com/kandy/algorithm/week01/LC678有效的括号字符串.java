package com.kandy.algorithm.week01;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 678.有效的括号字符串
 */
public class LC678有效的括号字符串 {
    public boolean checkValidString(String s) {
        //左括号栈
        Deque<Integer> leftStack = new LinkedList<>();
        //星号栈
        Deque<Integer> asteriskStack = new LinkedList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                //遇到左括号，将下标存入左括号栈
                leftStack.push(i);
            } else if (c == '*') {
                //遇到星号，将下标存入星号栈
                asteriskStack.push(i);
            } else {
                if (!leftStack.isEmpty()) {
                    //右括号优先和左括号匹配 1.如果左括号栈不为空，则从左括号栈弹出栈顶元素
                    leftStack.pop();
                } else if (!asteriskStack.isEmpty()) {
                    //2.如果左括号栈为空且星号栈不为空，则从星号栈弹出栈顶元素
                    asteriskStack.pop();
                } else {
                    //如果左括号栈和星号栈都为空，则没有字符可以和当前的右括号匹配，返回false
                    return false;
                }
            }
        }
        //遍历结束之后，左括号栈和星号栈可能还有元素
        while (!leftStack.isEmpty() && !asteriskStack.isEmpty()) {
            int leftIndex = leftStack.pop();
            int asteriskIndex = asteriskStack.pop();
            //匹配的条件是左括号下标小于星号下标,如果左括号下标大于星号下标则返回false
            if (leftIndex > asteriskIndex) {
                return false;
            }
        }
        //左括号全部匹配完毕，剩下的星号都可以看成空字符串，此时 ss 是有效的括号字符串，返回true
        return leftStack.isEmpty();
    }
}
