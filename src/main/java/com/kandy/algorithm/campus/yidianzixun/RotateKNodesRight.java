package com.kandy.algorithm.campus.yidianzixun;

/**
 * 链表旋转：将链表旋转右移k个节点
 * 样例：
 * 输入： 1->2->3->4->5->NULL, k = 2
 * 输出： 4->5->1->2->3->NULL
 */
public class RotateKNodesRight {
    /**
     * 单链表定义
     */
    public static class Node {
        int value;
        Node next;

        Node(int x) {
            value = x;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String args[]) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        System.out.println(toString(n1));
        System.out.println(lastK(n1, 2).value);
    }

    public static String toString(Node head) {
        if (head == null) {
            return "NULL";
        }
        return head.value + "->" + toString(head.next);
    }

    /**
     * 链表中倒数第k个节点
     * 定义两个指针，第一个指针从链表的头指针开始遍历向前走k-1；第二个指针保持不动，
     * 从第k步开始，第二个指针也开始遍历，两个指针差距k-1个距离，
     * 当第一个指针走到了尾巴节点，第二个指针正好在倒数第k个节点；
     */
    public static Node lastK(Node head, int k) {

        Node p = head;
        Node q = head;

        if (k <= 0)
            return null;

        //两个指针，q先跑k-1个节点，保证p最后指向的是倒数第K个
        while (k - 1 > 0) {
            if (q.next != null)
                q = q.next;
            else
                return null;
            k--;
        }
        while (q.next != null) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

}


