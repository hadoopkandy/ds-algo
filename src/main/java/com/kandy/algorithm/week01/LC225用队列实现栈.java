package com.kandy.algorithm.week01;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈
 */
public class LC225用队列实现栈 {
    class MyStack {
        Queue<Integer> queue1;//用于存储栈内的元素
        Queue<Integer> queue2;//入栈操作的辅助队列

        /** Initialize your data structure here. */
        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        /**
         *
         * @param x
         */
        public void push(int x) {
            queue2.offer(x);//将元素入队到queue2
            while (!queue1.isEmpty()) {
                //将queue1的全部元素依次出队并入队到queue2
                queue2.offer(queue1.poll());
            }
            //此时queue2的前端元素即为栈顶元素，交换queue1 queue2
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue1.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }
}
