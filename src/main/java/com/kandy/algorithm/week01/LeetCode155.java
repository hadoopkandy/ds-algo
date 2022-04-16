package com.kandy.algorithm.week01;

import java.util.Stack;

/**
 * 155. 最小栈
 */
public class LeetCode155 {
    class MinStack {
        private Stack<Integer> s;
        private Stack<Integer> preMin;

        public MinStack() {
            s = new Stack<>();
            preMin = new Stack<>();
        }

        public void push(int val) {
            s.push(val);
            if (preMin.empty()) preMin.push(val);
            else preMin.push(Math.min(preMin.peek(), val));

        }

        public void pop() {
            s.pop();
            preMin.pop();
        }

        public int top() {
            return s.peek();
        }

        public int getMin() {
            return preMin.peek();
        }
    }
}
