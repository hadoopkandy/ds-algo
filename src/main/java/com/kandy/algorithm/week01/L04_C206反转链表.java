package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 * https://leetcode.cn/problems/reverse-linked-list/description/
 * 206. 反转链表
 * 思路：
 * 需要遍历整个链表--写出链表遍历的模板
 * 链表遍历过程中需要做什么？"把箭头指向上一个" --用合适的变量维护上一个"last"
 */
public class L04_C206反转链表 {
    //遍历链表，让每个结点指向前一个
    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        // 遍历
        while (head != null) {
            // 备份head.next
            ListNode nextHead = head.next;
            // 操作：head指向last
            head.next = last;
            // 往后移动
            last = head;
            head = nextHead;
        }
        return last;
    }
}
