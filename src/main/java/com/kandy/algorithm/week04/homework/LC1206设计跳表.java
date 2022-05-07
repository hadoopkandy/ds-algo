package com.kandy.algorithm.week04.homework;

/**
 * 1206.设计跳表
 * 跳表使用空间换时间的设计思路，通过构建多级索引来提高查询的效率，实现了基于链表的“二分查找”。
 * 跳表是一种动态数据结构，支持快速地插入、删除、查找操作，时间复杂度都是 O(logn)
 * Redis 有序集合 Sorted Set
 */
public class LC1206设计跳表 {
    static class Skiplist {
        /**
         * 最大层数
         */
        private static int DEFAULT_MAX_LEVEL = 32;
        /**
         * 随机层数概率，也就是随机出的层数，在 第1层以上(不包括第一层)的概率，层数不超过maxLevel，层数的起始号为1
         */
        private static double DEFAULT_P_FACTOR = 0.25;

        Node head = new Node(null, DEFAULT_MAX_LEVEL); //头节点

        int currentLevel = 1; //表示当前nodes的实际层数，它从1开始


        public Skiplist() {
        }

        public boolean search(int target) {
            Node searchNode = head;
            for (int i = currentLevel - 1; i >= 0; i--) {
                searchNode = findClosest(searchNode, i, target);
                if (searchNode.next[i] != null && searchNode.next[i].value == target) {
                    return true;
                }
            }
            return false;
        }

        /**
         * @param num
         */
        public void add(int num) {
            //获得一个随机层
            int level = randomLevel();
            Node updateNode = head;
            //初始化第一个节点
            Node newNode = new Node(num, level);
            //处理从当前层往下的逻辑
            // 计算出当前num 索引的实际层数，从该层开始添加索引
            for (int i = currentLevel - 1; i >= 0; i--) {
                //找到当前层离插入节点最近的结点（前置结点）
                updateNode = findClosest(updateNode, i, num);
                if (i < level) {
                    //如果当前层比随机出来的level小
                    //将结点插入到当前的前置updateNode中
                    //头结点 -> 3 （level=1），插入7 (level=4)
                    if (updateNode.next[i] == null) {
                        updateNode.next[i] = newNode;
                    } else {
                        Node temp = updateNode.next[i];
                        updateNode.next[i] = newNode;
                        newNode.next[i] = temp;
                    }
                }
            }
            //如果随机出来的层数>当前层数，那第i层的head直接指向newNode,不需要有updateNode
            if (level > currentLevel) {
                for (int i = currentLevel; i < level; i++) {
                    head.next[i] = newNode;
                }
                //更新currentLevel
                currentLevel = level;
            }
        }

        public boolean erase(int num) {
            boolean flag = false;
            Node searchNode = head;
            for (int i = currentLevel - 1; i >= 0; i--) {
                //找到当前层从head开始离需要删除结点最近的结点
                searchNode = findClosest(searchNode, i, num);
                //要删除的结点存在
                if (searchNode.next[i] != null && searchNode.next[i].value == num) {
                    //找到该层中该节点 直接删除即可
                    searchNode.next[i] = searchNode.next[i].next[i];
                    flag = true;
                    continue;
                }
            }
            return flag;
        }

        /**
         * 找到在当前level层，从node开始，最后一个小于value的点
         *
         * @param node 从哪个节点开始找
         * @param levelIndex 所在层
         * @param value 要插入的节点值
         * @return
         */
        private Node findClosest(Node node, int levelIndex, int value) {
            while ((node.next[levelIndex]) != null && value > node.next[levelIndex].value) {
                node = node.next[levelIndex];
            }
            return node;
        }


        /**
         * 随机一个层数
         * 这里要记住，随机出来一个层数，保证查询和插入的性能
         * 这里的逻辑可以记忆一下，如果某个结点在第i层有指针，那它在i+1层有指针的概率是p
         * 并且不会超过MAX_LEVEL
         */
        private static int randomLevel() {
            int level = 1;
            while (Math.random() < DEFAULT_P_FACTOR && level < DEFAULT_MAX_LEVEL) {
                level++;
            }
            return level;
        }


        class Node {
            Integer value;//节点值
            Node[] next;// 节点在不同层的下一个节点

            //用size表示当前节点在跳表中索引几层
            public Node(Integer value, int size) {
                this.value = value;
                this.next = new Node[size];
            }
            //重写toString方法
            @Override
            public String toString() {
                return String.valueOf(value);
            }
        }
    }

}
