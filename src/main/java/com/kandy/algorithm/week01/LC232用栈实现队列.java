package com.kandy.algorithm.week01;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 232.用栈实现队列
 */
public class LC232用栈实现队列 {
    class MyQueue {
        Deque<Integer> inStack; //输入栈，用于压入 push传入的数据
        Deque<Integer> outStack; //输出栈用于pop和peek操作

        public MyQueue() {
            inStack = new ArrayDeque<Integer>();
            outStack = new ArrayDeque<Integer>();
        }

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            if (outStack.isEmpty()) {
                in2out();
            }
            return outStack.pop();
        }

        public int peek() {
            if (outStack.isEmpty()) {
                in2out();
            }
            return outStack.peek();
        }
        //两个栈都可能存数据
        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }
        private void in2out() {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
