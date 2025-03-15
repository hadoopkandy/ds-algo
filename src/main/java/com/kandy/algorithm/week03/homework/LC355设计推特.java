package com.kandy.algorithm.week03.homework;

import java.util.*;

/**
 * https://leetcode.cn/problems/design-twitter/description/
 * 关键点：出边数组 + K个有序链表排序  ListNode中加入id用于排序
 */
public class LC355设计推特 {
    public static class Twitter {
        int n;
        private List<List<Integer>> to;// 出边数组,用户A关注用户B，则存 A->B的有向边
        Map<Integer, ListNode> userTweets;
        int id; //自增id，用于ListNode排序

        class ListNode {
            public int id;
            public int val;
            public ListNode next;

            public ListNode() {
            }

            public ListNode(int val) {
                this.val = val;
            }

            public ListNode(int val, int id) {
                this.val = val;
                this.id = id;
            }
        }

        public Twitter() {
            n = 500;
            to = new ArrayList<>(n + 1); //浪费0
            //1 <= userId, followerId, followeeId <= 500,浪费一个0
            for (int i = 0; i <= n; i++) {
                this.to.add(new ArrayList<>());
            }
            userTweets = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            ListNode head = userTweets.get(userId);
            if (head == null) {
                head = new ListNode(-1);
                head.next = new ListNode(tweetId, id++);
                userTweets.put(userId, head);
                return;
            }
            //单链表头部插入节点
            ListNode newNode = new ListNode(tweetId, id++);
            ListNode next = head.next;
            head.next = newNode;
            newNode.next = next;
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> followList = new ArrayList<>(to.get(userId));
            //把自己也加到关注列表里
            followList.add(userId);
            int n = followList.size();
            ListNode[] listNodes = new ListNode[n];
            int i = 0;
            for (Integer follow : followList) {
                listNodes[i++] = userTweets.get(follow);
            }
            return mergeKLists(listNodes);
        }

        public List<Integer> mergeKLists(ListNode[] lists) {
            int k = 10;
            List<Integer> ans = new ArrayList<>(k);
            //大根堆
            PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.id, o1.id));
            for (ListNode node : lists) {
                if (node != null) {
                    //跳过保护节点head
                    queue.add(node.next);
                }
            }
            while (!queue.isEmpty()) {
                final ListNode curr = queue.poll();
                ans.add(curr.val);
                if (ans.size() >= k) {
                    return ans;
                }
                if (curr.next != null) {
                    queue.add(curr.next);
                }
            }
            return ans;
        }

        public void follow(int followerId, int followeeId) {
            List<Integer> followList = to.get(followerId);
            //判断重复
            if (followList.contains(Integer.valueOf(followeeId))) {
                return;
            }
            followList.add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            List<Integer> followList = to.get(followerId);
            //判断不存在
            if (!followList.contains(Integer.valueOf(followeeId))) {
                return;
            }
            //注意remove方法要变成对象，否则就变成index
            followList.remove(Integer.valueOf(followeeId));
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));
        twitter.postTweet(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }
}
