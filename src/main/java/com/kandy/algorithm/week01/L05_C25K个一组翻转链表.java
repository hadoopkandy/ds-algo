package com.kandy.algorithm.week01;

import com.kandy.algorithm.leetcode.ListNode;

/**
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/description/
 * 25. K个一组翻转链表
 * 代码模块化的思路：
 *  大框架：链表遍历找"组"，组与组之间关系的处理
 *  小框架：组内反转链表
 */
public class L05_C25K个一组翻转链表 {
    public ListNode reverseKGroup(ListNode head, int k) {
        //protect是一个访问入口，链表的保护节点（提供入口，防止null异常）
        ListNode protect = new ListNode(0, head);
        ListNode last = protect;//
        //head这里是指向每个分组的head
        while (head != null) {
            //1.分组（往后走k-1步，找到一组）
            //一组的开头head 结尾end
            ListNode tail = getEnd(head, k);
            if (tail == null) break;
            ListNode nextHead = tail.next;

            //当前组：head->...->tail
            //下一组：nextHead->...

            //2.一组内部(head到end之间)要反转(调用反转链表)
            reverseList(head, nextHead);
            //3.更新每组跟前一组last、后一组nextHead之间的边
            //1->4,第一次protect->next =2
            //当前组：变成了tail->...->head
            last.next = tail;
            //3->5
            head.next = nextHead;
            last = head;
            head = nextHead;
        }
        return protect.next;
    }

    // 返回k个一组，组的尾部，null表示这组不够k个
    ListNode getEnd(ListNode head, int k) {
        while (head != null) {
            k--;
            //走k-1步
            if (k == 0) return head;
            head = head.next;
        }
        return null;
    }

    // 组的内部反转
    private void reverseList(ListNode head, ListNode stop) {
        //当前组的head先不考虑
        ListNode now = head.next;
        ListNode last = head;
        // 遍历
        while (now != stop) {
            // 备份now.next
            ListNode next = now.next;
            // 操作：now指向last
            now.next = last;
            // 往后移动
            last = now;
            now = next;
        }
    }
}
