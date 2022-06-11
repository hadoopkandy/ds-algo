package com.kandy.algorithm.week08;

import java.util.HashMap;


/**
 * 字典树(Trie树) 是一种由"结点"和"带有字符的边"构成的树形结构
 * 典型应用：用于统计和排序大量的字符串
 * 优点：最大限度地减少无谓的字符串比较，查询效率比哈希表高
 */
public class 字典树模板LC208实现Trie {
    //字符集数组法
    class Trie {
        public class Node {
            int count; // 词频
            Node[] child; //长为26的Node数组

            Node() {
                count = 0;
                child = new Node[26]; //以字符为下标，保存指向的节点
            }
        }

        Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            // 空字典树：只有根一个点
            root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            solve(word, true, false);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return solve(word, false, false);
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return solve(prefix, false, true);
        }

        private boolean solve(String word, boolean isInsert, boolean isPrefix) {
            Node curr = root;
            for (char ch : word.toCharArray()) {
                if (curr.child[ch - 'a'] == null) {
                    if (isInsert) {
                        //不存在就插入
                        curr.child[ch - 'a'] = new Node();
                    } else {
                        //查询不存在返回false
                        return false;
                    }
                }
                curr = curr.child[ch - 'a'];
            }
            if (isInsert) curr.count++; //insert 插入词频+1
            if (isPrefix) return true; //startsWith 前缀查询返回true
            return curr.count > 0; //search
        }
    }

    //字符集映射法
    class Trie2 {
        public class Node {
            int count; // 词频
            HashMap<Character, Node> child; // 出边（字符映射）

            Node() {
                count = 0;
                child = new HashMap<>();
            }
        }

        Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie2() {
            // 空字典树：只有根一个点
            root = new Node();
        }

        private boolean solve(String word, boolean isInsert, boolean isPrefix) {
            Node curr = root;
            for (char ch : word.toCharArray()) {
                if (!curr.child.containsKey(ch)) {
                    if (isInsert) {
                        //不存在就插入
                        curr.child.put(ch, new Node());
                    } else {
                        //查询不存在返回false
                        return false;
                    }
                }
                curr = curr.child.get(ch);
            }
            if (isInsert) curr.count++; //insert 插入词频+1
            if (isPrefix) return true; //startsWith 前缀查询返回true
            return curr.count > 0; //search
        }
    }
}
