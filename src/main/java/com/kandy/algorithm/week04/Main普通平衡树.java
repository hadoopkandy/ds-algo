package com.kandy.algorithm.week04;

import java.util.Random;
import java.util.Scanner;

public class Main普通平衡树 {
    public static final int RANGE = 10000010;

    public static int rand() {
        return new Random().nextInt(2 * RANGE);
    }

    public static class Node {
        Node left; //左孩子
        Node right; //右孩子
        int key, val;//节点关键码（原始数据）、随机权值
        int cnt, size; //副本数、子树大小

        Node(int data) {
            key = data;
            val = rand();
            cnt = 1;
            size = 1;
            left = null;
            right = null;
        }
    }

    public static class Treap {
        Node root;

        public Treap() {
            //建立一棵空Treap,包含两个保护结点
            root = new Node((int) -1e9);
            root.right = new Node((int) 1e9);
            update(root);
        }

        //插入
        public void Insert(int data) {
            root = Insert(root, data);
        }

        //删除
        public void Remove(int data) {
            root = Remove(root, data);
        }

        // 查询target的排名
        public int GetRankByVal(int target) {
            //有保护结点，所以减1
            return GetRankByVal(root, target) - 1;
        }

        //查询排第rank名的元素
        public int GetValByRank(int rank) {
            //有保护结点，所以加1
            return GetValByRank(root, rank + 1);
        }

        //查询target的前驱（小于target的最大的数）
        public int GetPre(int target) {
            int ans = (int) -1e9;
            Node p = root;
            while (p != null) {
                if (target == p.key) {
                    //检索到了target，且有左子树，应该在左子树种一直向右走
                    if (p.left != null) {
                        p = p.left;
                        while (p.right != null) p = p.right;
                        ans = p.key;
                    }
                    break;
                }
                //若最终检索不到target，答案就在途经的结点中（小于target的最大的数）
                if (p.key < target && p.key > ans) ans = p.key;
                p = target < p.key ? p.left : p.right;
            }
            return ans;
        }

        //查询target的后继（大于target的最小的数）
        public int GetNext(int target) {
            int ans = (int) 1e9;
            Node p = root;
            while (p != null) {
                if (target == p.key) {
                    //检索到了target，且有右子树，应该在右子树种一直向左走
                    if (p.right != null) {
                        p = p.right;
                        while (p.left != null) p = p.left;
                        ans = p.key;
                    }
                    break;
                }
                //若最终检索不到target，答案就在途经的结点中（大于target的最小的数）
                if (p.key > target && p.key < ans) ans = p.key;
                p = target < p.key ? p.left : p.right;
            }
            return ans;
        }

        //在以p为根的子树中查询target的排名
        int GetRankByVal(Node p, int target) {
            int left_size = p.left != null ? p.left.size : 0;
            if (p.key == target) return left_size + 1;
            else if (target < p.key) return GetRankByVal(p.left, target);
            else return left_size + p.cnt + GetRankByVal(p.right, target);
        }

        //在以p为根的子树种查询排第rank名的元素
        int GetValByRank(Node p, int rank) {
            int left_size = p.left != null ? p.left.size : 0;
            if (rank <= left_size) return GetValByRank(p.left, rank);
            else if (rank <= left_size + p.cnt) return p.key;
            else return GetValByRank(p.right, rank - left_size - p.cnt);
        }

        //在以p为根的子树种插入data,返回新的根
        private Node Insert(Node p, int data) {
            if (p == null) {
                return new Node(data);
            }

            if (data == p.key) {
                //检索到重复，无需插入，副本数+1即可
                p.cnt++;
            } else if (data < p.key) {
                p.left = Insert(p.left, data);
                //不满足堆性质，左孩子绕p右旋,左孩子代替p成为新的根
                if (p.val < p.left.val) p = zig(p);
            } else {
                p.right = Insert(p.right, data);
                //不满足堆性质，右孩子绕p左旋,右孩子代替p成为新的根
                if (p.val < p.right.val) p = zag(p);
            }
            //子树结构可能发生了变化，相应的信息需要一并更新
            update(p);
            return p;
        }

        //在以p为根的子树中删除data，返回新的根
        private Node Remove(Node p, int data) {
            if (p == null) return null;
            if (data == p.key) {
                if (p.cnt > 1) p.cnt--;
                else {
                    if (p.left == null && p.right == null) {
                        return null;
                    }
                    if (p.right == null || (p.left != null && p.left.val > p.right.val)) {
                        p = zig(p);
                        p.right = Remove(p.right, data);
                    } else {
                        p = zag(p);
                        p.left = Remove(p.left, data);
                    }
                }
            } else if (data < p.key) {
                p.left = Remove(p.left, data);
            } else {
                p.right = Remove(p.right, data);
            }
            if (p != null) update(p);
            return p;
        }

        //p的左孩子绕p向右旋转
        private Node zig(Node p) {
            Node q = p.left; //找到p的左孩子q
            p.left = q.right; //p的左孩子变成q的右孩子
            q.right = p; //p变成q的右孩子
            //结点上若有附加信息，一并更新
            update(p);
            update(q);
            return q;
        }

        //p的右孩子绕p向左旋转
        private Node zag(Node p) {
            Node q = p.right;
            p.right = q.left;
            q.left = p;
            //结点上若有附加信息，一并更新
            update(p);
            update(q);
            return q;
        }

        //更新结点p的附加信息（本题中是size）
        private void update(Node p) {
            int left_size = p.left != null ? p.left.size : 0;
            int right_size = p.right != null ? p.right.size : 0;
            p.size = left_size + right_size + p.cnt;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Treap treap = new Treap();
        while (n-- > 0) {
            int opt = input.nextInt();
            int x = input.nextInt();
            switch (opt) {
                case 1:
                    treap.Insert(x);
                    break;
                case 2:
                    treap.Remove(x);
                    break;
                case 3:
                    System.out.println(treap.GetRankByVal(x));
                    break;
                case 4:
                    System.out.println(treap.GetValByRank(x));
                    break;
                case 5:
                    System.out.println(treap.GetPre(x));
                    break;
                case 6:
                    System.out.println(treap.GetNext(x));
                    break;
            }
        }
    }
}
