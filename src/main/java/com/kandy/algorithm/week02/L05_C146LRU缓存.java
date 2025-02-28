package com.kandy.algorithm.week02;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/lru-cache/description/
 * 146. LRU 缓存
 * LRU -least recently used，最近最少使用（淘汰最旧数据）
 * 无序哈希表 + 双向链表 LinkedHashMap
 *  双向链表用于按时间顺序保存数据
 *  Hash Table 用于把key映射到链表结点（指针/引用）
 *  O(1) 访问:直接检查哈希表
 *  O(1) 更新:通过哈希表定位到链表结点,删除该结点（若存在）,在表头重新插入
 *  O(1) 删除:总是淘汰链表末尾结点,同时在哈希表中删除
 */
public class L05_C146LRU缓存 {
    class LRUCache {

        public LRUCache(int capacity) {
            this.capacity = capacity;
            keys = new HashMap<>(capacity);
            head = new Node();
            tail = new Node();
            //空的带保护节点的双向链表
            head.next = tail;
            tail.pre = head;
        }
        public int get(int key) {
            //key不存在
            if (!keys.containsKey(key)) {
                return -1;
            }

            Node node = keys.get(key);
            remove(node);//把节点删除
            insert(head,node); //插入到保护点头部的后面
            return node.value;
        }

        public void put(int key, int value) {
            if (!keys.containsKey(key)) {
                Node node = new Node();
                node.key = key;
                node.value = value;
                keys.put(key,node);//插入哈希表
                insert(head,node);

                if (keys.size() > capacity) {
                    //先从map删除，再删链表
                    keys.remove(tail.pre.key);
                    remove(tail.pre);
                }
            } else {
                //与get一样
               Node node =  keys.get(key);
               node.value =value;
               //删除放到开头
               remove(node);
               insert(head,node);
            }

        }

        //双链表删除某个节点
        void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        //双链表插入，在节点p后面插入node
        void insert(Node p, Node node) {
            p.next.pre = node;node.next = p.next;
            p.next = node;node.pre = p;
        }

        class Node {
            int key;
            int value;
            Node pre;
            Node next;
        }

        Node head;
        Node tail;

        HashMap<Integer, Node> keys;

        int capacity;
    }
}
