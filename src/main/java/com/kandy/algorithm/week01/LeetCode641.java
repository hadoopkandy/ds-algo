package com.kandy.algorithm.week01;

/**
 * 641. 设计循环双端队列
 */
public class LeetCode641 {
    class MyCircularDeque {
        private int capacity;
        private int[] elements;
        private int head;// 始终指向队列首元素
        private int tail;// 始终指向队列尾元素的下一个，如果越界则循环

        /**
         * 构造函数,双端队列最大为 k
         */
        public MyCircularDeque(int k) {
            capacity = k + 1;
            elements = new int[capacity];
            head = 0;
            tail = 0;
        }

        /**
         * 将一个元素添加到双端队列头部。 如果操作成功返回true，否则返回 false
         */
        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            head = (head - 1 + capacity) % capacity;
            elements[head] = value;
            return true;
        }

        /**
         * 将一个元素添加到双端队列尾部。如果操作成功返回true ，否则返回 false
         */
        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            elements[tail] = value;
            tail = (tail + 1) % capacity;
            return true;
        }

        /**
         * 从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false
         */
        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            head = (head + 1) % capacity;
            return true;
        }

        /**
         * 从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false
         */
        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            tail = (tail - 1 + capacity) % capacity;
            return true;
        }

        /**
         * 从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
         */
        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return elements[head];
        }

        /**
         * 获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
         */
        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return elements[(tail - 1 + capacity) % capacity];
        }

        /**
         * 若双端队列为空，则返回 true ，否则返回 false。
         */
        public boolean isEmpty() {
            return head == tail;
        }

        /**
         * 若双端队列满了，则返回 true ，否则返回 false
         */
        public boolean isFull() {
            return (tail + 1) % capacity == head;
        }
    }
}
