package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 * 206. 反转链表
 */
public class LeetCode206 {
    //遍历链表，让每个结点指向前一个
    public ListNode reverseList(ListNode head) {
        ListNode now = head;
        ListNode last = null;
        // 遍历
        while (now != null) {
            // 备份now.next
            ListNode next = now.next;
            // 操作：now指向last
            now.next = last;
            // 往后移动
            last = now;
            now = next;
        }
        return last;
    }
}
