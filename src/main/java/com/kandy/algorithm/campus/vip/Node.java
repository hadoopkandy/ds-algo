package com.kandy.algorithm.campus.vip;

public class Node {

    private Node next;
    private String value;

    public Node(String value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Node head = new Node("a");
        Node node1 = new Node("b");
        Node node2 = new Node("c");
        Node node3 = new Node("d");
        //初始化链表
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        System.out.println("打印链表反转前：");

        reverse(head);
        //设置head的下一个元素为null，注意：此时head已经成为链表尾部元素。
        head.next = null;
        while (node3 != null) {
            System.out.print(node3.getValue());
            node3 = node3.getNext();
            if (node3 != null) {
                System.out.print("->");
            }
        }
    }

    /**
     * 利用迭代循环到链表最后一个元素，然后利用nextNode.setNext(head)把最后一个元素变为
     * 第一个元素。
     *
     * @param head
     */
    public static void reverse(Node head) {
        if (head != null) {
            Node nextNode = head.getNext();
            if (nextNode != null) {
                reverse(nextNode);
                nextNode.setNext(head);
            }
        }

    }
}
