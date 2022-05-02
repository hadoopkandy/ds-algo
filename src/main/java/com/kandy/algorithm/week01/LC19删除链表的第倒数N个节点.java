package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 * 双指针
 */
public class LC19删除链表的第倒数N个节点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode protect = new ListNode(0, head);
        ListNode fast = head;
        ListNode slow = protect;
        // fast首先走n + 1步 ，为什么是n+1呢，因为只有这样同时移动的时候slow才能指向删除节点的上一个节点（方便做删除操作）
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return protect.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        LC19删除链表的第倒数N个节点 lc = new LC19删除链表的第倒数N个节点();
        System.out.println(lc.removeNthFromEnd(head,2));
    }
}
