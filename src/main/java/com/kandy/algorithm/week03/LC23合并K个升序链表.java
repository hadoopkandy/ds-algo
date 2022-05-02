package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二叉堆的实现
 * 二叉堆是堆的一种简易实现，本质上是一棵满足堆性质的完全二叉树
 *
 * 假设 第一个元素存储在索引（下标为1）的位置的话
 * 索引为p的结点的左孩子的索引为 p * 2
 * 索引为p的结点的右孩子的索引为 p * 2 + 1
 * 索引为p的结点的父亲的索引为 p / 2 （下取整）
 *
 * 假设 第一个元素存储在索引（下标为0）的位置的话
 * 索引为p的结点的左孩子的索引为 p * 2 + 1
 * 索引为p的结点的右孩子的索引为 p * 2 + 2
 * 索引为p的结点的父亲的索引为 （p -1）/ 2 （下取整）
 *
 * 入堆 插入队尾，自底向上调整
 * 出堆 与堆尾交换，删堆尾，自顶向下与较小者调整
 */
public class LC23合并K个升序链表 {
    // 堆结点（key用于比较的关键码，listNode可以是任意的附带信息）
    public class Node {
        int key;
        ListNode listNode;
        Node(int key, ListNode listNode) {
            this.key = key;
            this.listNode = listNode;
        }
    }

    //小根堆
    class BinaryHeap {
        public BinaryHeap() {
            heap = new ArrayList<Node>();
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }
        /**
         * 插入 insert
         * 新元素一律插入数组尾部
         * 设插入到了索引p的位置
         *
         * 然后向上进行一次调整（Heapify Up）
         * 若已到达根，停止
         * 若满足堆性质 （headp[p] >= heap[(p-1)/2]）,停止
         * 否则交换heap[p]与heap[(p-1)/2],另p = (p-1)/2 ，继续调整
         * @param node
         */
        public void push(Node node) {
            // 插入到尾部
            heap.add(node);
            // 向上调整
            heapifyUp(heap.size() - 1);
        }

        /**
         * 取出堆顶
         * 把堆顶heap[0]与堆尾heap[n-1]交换，删除堆尾（数组最后一个元素）
         *
         * 然后从根向下进行一次调整(Heapify Down)
         * 每次与左右节点中较大的一个比较，检查堆性质不满足交换
         * 注意判断子节点是否存在
         * @return
         */
        public Node pop() {
            Node ans = heap.get(0);
            // 末尾交换到头部，然后删除末尾
            Collections.swap(heap, 0, heap.size() - 1);
            heap.remove(heap.size() - 1);
            // 向下调整
            heapifyDown(0);
            return ans;
        }

        void heapifyUp(int p) {
            while (p > 0) {
                int fa = (p - 1) / 2;
                if (heap.get(p).key < heap.get(fa).key) { // 小根堆
                    Collections.swap(heap, p, fa);
                    p = fa;
                }
                else break;
            }
        }

        void heapifyDown(int p) {
            int child = p * 2 + 1;//要换的那个孩子
            while (child < heap.size()) {  // child未出界，说明p有合法的child，还不是叶子
                int otherChild = p * 2 + 2;//另一个小孩
                // 先比较两个孩子，谁小就继续跟p比较
                // child存较小的孩子
                if (otherChild < heap.size() && heap.get(otherChild).key < heap.get(child).key)
                    child = otherChild;
                // 让child跟p比较
                if (heap.get(child).key < heap.get(p).key) { // 小根堆
                    Collections.swap(heap, p, child);
                    p = child;
                    child = p * 2 + 1;
                }
                else break;
            }
        }

        // 数组存储完全二叉树
        // 从索引0开始存
        List<Node> heap;
    }

    // O(元素个数*logK)
    // O(total*logK)
    public ListNode mergeKLists(ListNode[] lists) {
        BinaryHeap q = new BinaryHeap();
        for (ListNode listNode : lists) {
            if (listNode != null)
                q.push(new Node(listNode.val, listNode));
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while (!q.isEmpty()) {
            // 取出k个指针指向的最小元素
            Node node = q.pop();
            // 在答案链表的末尾插入
            tail.next = node.listNode;
            tail = tail.next;
            // 当最小被取出后，指针向后移动一位，可能需要插入新的元素
            ListNode p = node.listNode.next;
            if (p != null) {
                q.push(new Node(p.val, p));
            }
        }
        return head.next;
    }
}
