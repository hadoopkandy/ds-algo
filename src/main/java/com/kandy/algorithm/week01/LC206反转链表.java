package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 * 206. 反转链表
 * 思路：
 * 需要遍历整个链表--写出链表遍历的模板
 * 链表遍历过程中需要做什么？"把箭头指向上一个" --用合适的变量维护上一个"last"
 */
public class LC206反转链表 {
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
