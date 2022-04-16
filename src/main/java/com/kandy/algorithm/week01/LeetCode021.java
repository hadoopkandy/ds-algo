package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 * 21. 合并两个有序链表
 */
public class LeetCode021 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        //保护节点，提供访问入口
        ListNode protect = new ListNode(0);
        ListNode head = protect;
        while (list1 != null || list2 != null) {
            //如果list2已经到头 或list1 list2都没有到链表末尾的情况，取较小者
            if (list2 == null || (list1 != null && list1.val <= list2.val)) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        return protect.next;
    }
}
