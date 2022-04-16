package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 * 142. 环形链表 II
 */
public class LeetCode142 {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return head;
            }
        }
        return null;
    }


}
