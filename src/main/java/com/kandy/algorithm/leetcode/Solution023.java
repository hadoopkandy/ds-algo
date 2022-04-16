package com.kandy.algorithm.leetcode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 */
public class Solution023 {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.val, o2.val));
        for (ListNode l : lists) {
            if (l != null) {
                pq.add(l);
            }
        }
        while (!pq.isEmpty()) {
            final ListNode curr = pq.poll();
            head.next = curr;
            head = head.next;
            if (curr.next != null) {
                pq.add(curr.next);
            }
        }
        return dummy.next;

        //分治合并
//        return merge(lists,0,lists.length-1);
    }

    public static void main(String[] args) {
        Solution023 sol = new Solution023();
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);

        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);

        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);
        System.out.println(sol.mergeKLists(lists));
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

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

}
