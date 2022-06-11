package com.kandy.algorithm.week08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC212单词搜索II {
    public class Node {
        String word; // 实际单词的引用
        HashMap<Character, Node> child; // 出边（字符映射）

        Node() {
            word = null;
            child = new HashMap<>();
        }
    }

    Node root; //字典树的根节点

    boolean[][] visited;
    int m; //行数
    int n;//列数

    List<String> ans;

    //方向数组
    int[] dx = new int[]{-1, 0, 0, 1};
    int[] dy = new int[]{0, -1, 1, 0};

    public List<String> findWords(char[][] board, String[] words) {
        //建立字典树，存单词
        root = new Node();
        for (String word : words) {
            insert(word);
        }

        //枚举每个起点，搜索
        m = board.length;
        n = board[0].length;
        ans = new ArrayList<String>();
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                visited[i][j] = true;
                dfs(board, i, j, root);
                visited[i][j] = false;
            }
        return ans;
    }

    private void dfs(char[][] board, int x, int y, Node curr) {
        char ch = board[x][y];
        if (!curr.child.containsKey(ch)) return;

        Node next = curr.child.get(ch);
        //到单词结尾，找到一个单词
        if (next.word != null) {
            ans.add(next.word);
            next.word = null; //删除当前找到的单词，不然[oa,oaa] 这样的场景结果会重复
        }
        //删掉字符边以及next这个点 考虑9宫格 8个a组成的单词场景
        if (next.child.isEmpty()) {
            curr.child.remove(ch);
        }

        //枚举4个分支
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
            if (visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(board, nx, ny, next);
            visited[nx][ny] = false;
        }
    }

    //字典树插入
    private void insert(String word) {
        Node curr = root;
        for (char ch : word.toCharArray()) {
            //不存在就新建
            if (!curr.child.containsKey(ch)) {
                curr.child.put(ch, new Node());
            }
            curr = curr.child.get(ch);
        }
        curr.word = word;
    }
}
