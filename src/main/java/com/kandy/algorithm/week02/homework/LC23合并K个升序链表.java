package com.kandy.algorithm.week02.homework;

import com.kandy.algorithm.leetcode.ListNode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 */
public class LC23合并K个升序链表 {
    //分治合并
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list1 == null) {
            return list1 != null ? list1 : list2;
        }
        //保护节点，提供访问入口
        ListNode protect = new ListNode(0);
        //定义一个tail节点，初始化指向保护节点
        ListNode tail = protect;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        //合并完list1、list2 最多只有一个还未被合并完，直接指向未合并完的链表即可
        tail.next = (list1 != null ? list1 : list2);
        return protect.next;
    }

    //优先队列实现
    // 1.将lists 中所有节点放入优先队列中
    // 2.迭代优先队列，每次出队的最小元素节点合并到新链表末尾
    // 3.迭代的链表节点不是未节点，取下一个节点继续放入优先队列中
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode protect = new ListNode(0);
        ListNode tail = protect;
        //实现Comparator接口，定义ListNode的大小比较方法
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.val, o2.val));
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            final ListNode curr = queue.poll();
            tail.next = curr;
            tail = tail.next;
            if (curr.next != null) {
                queue.add(curr.next);
            }
        }
        return protect.next;
    }
    public static void main(String[] args) {
        LC23合并K个升序链表 sol = new LC23合并K个升序链表();

        ListNode[] lists = new ListNode[3];
        //1-> 4 ->5
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);

        //1 -> 3 -> 4
        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);

        //2 -> 6
        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);


        System.out.println(sol.mergeKLists(lists));
    }
}
