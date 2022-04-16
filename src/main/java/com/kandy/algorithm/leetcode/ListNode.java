package com.kandy.algorithm.leetcode;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }



    @Override
    public String toString() {
        if (next != null) {
            return val + " " + next;
        } else {
            return val + " ";
        }
    }
}
