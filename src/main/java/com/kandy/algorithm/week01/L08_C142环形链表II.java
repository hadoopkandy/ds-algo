package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 * https://leetcode.cn/problems/linked-list-cycle-ii/description/
 * 142. 环形链表 II
 * 让慢指针与head同时移动,必定在环的起始点相遇
 */
public class L08_C142环形链表II {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                //让慢指针与head同时移动,必定在环的起始点相遇
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
