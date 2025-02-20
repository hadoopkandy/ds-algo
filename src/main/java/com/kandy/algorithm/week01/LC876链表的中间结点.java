package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 *
 */
public class LC876链表的中间结点 {
    /**
     * 方法一：数组
     * 链表的缺点在于不能通过下标访问对应的元素。因此我们可以考虑对链表进行遍历，同时将遍历到的元素依次放入数组A中。
     * 如果我们遍历到了N个元素，那么链表以及数组的长度也为N，对应的中间节点即为A[N/2]
     */
    public ListNode middleNode1(ListNode head) {
        ListNode[] A = new ListNode[100];
        int t = 0;
        while (head != null) {
            A[t++] = head;
            head = head.next;
        }
        return A[t / 2];
    }
    /**
     * 方法二：单指针法
     * 我们可以对方法一进行空间优化，省去数组A。
     * 我们可以对链表进行两次遍历。第一次遍历时，我们统计链表中的元素个数N；
     * 第二次遍历时，我们遍历到第N/2个元素（链表的首节点为第0个元素）时，将该元素返回即可。
     */
    public ListNode middleNode2(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            ++n;
            cur = cur.next;
        }
        int k = 0;
        cur = head;
        while (k < n / 2) {
            ++k;
            cur = cur.next;
        }
        return cur;
    }
    /**
     * 方法三：快慢指针法
     * 我们可以继续优化方法二，用两个指针slow与fast一起遍历链表。
     * slow一次走一步，fast一次走两步。那么当fast到达链表的末尾时，slow必然位于中间。
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
