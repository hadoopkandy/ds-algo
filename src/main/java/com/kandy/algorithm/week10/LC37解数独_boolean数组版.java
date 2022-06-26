package com.kandy.algorithm.week10;


import java.util.ArrayList;
import java.util.List;

//蛮力搜索：每次找第一个空位置，枚举填0-9，判定有效，递归
//快速判定数独有效性：
//1.对于每行、每列、每个九宫格，分别用一个9位bool数组保存哪些数字还可以填
//2.对于一个位置，合并它所在行、列、九宫格的3个bool数组，就可以得到能填的数字
//3.当一个位置填上某个数后，更新对应的行、列、九宫格bool数组，回溯时还原现场
public class LC37解数独_boolean数组版 {
    void solveSudoku(char[][] board) {
        row = new boolean[9][10];// 数组全部赋值为true
        col = new boolean[9][10];
        box = new boolean[9][10];
        for (int i = 0; i < 9; i++)
            for (int digit = 1; digit <= 9; digit++) {
                //每行的每个数组、每列的每个数字、每个九宫格的每个数字
                row[i][digit] = col[i][digit] = box[i][digit] = true; //可以填，true
            }

        //预处理数字的可用情况
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int digit = board[i][j] - '0';//数字字符转数字
                row[i][digit] = col[j][digit] = box[boxIndex(i, j)][digit] = false; //不能填，变成false
            }
        dfs(board);
    }

    boolean dfs(char[][] board) {
        //找最确定的(可能性最小的)格子
        int[] pos = findMinimumProbability(board);
        int x = pos[0];
        int y = pos[1];
        if (x == -1) return true; // 填满了，有解
        List<Integer> availableDigits = getAvailableDigits(x, y);
        //遍历能填的所有数字
        for (int digit : availableDigits) {
            //填过 赋值3个false 变成不能填
            row[x][digit] = col[y][digit] = box[boxIndex(x, y)][digit] = false;
            board[x][y] = (char) ('0' + digit);
            if (dfs(board)) return true;
            //还原现场，改成true .
            board[x][y] = '.';
            row[x][digit] = col[y][digit] = box[boxIndex(x, y)][digit] = true;
        }
        return false;
    }

    //找最小可能性的位置
    int[] findMinimumProbability(char[][] board) {
        int minValue = 10;
        int[] pos = new int[]{-1, -1};
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] == '.') {
                    List<Integer> availableDigits = getAvailableDigits(i, j);
                    //更新能填数字最少的格子
                    if (availableDigits.size() < minValue) {
                        minValue = availableDigits.size();
                        pos = new int[]{i, j};
                    }
                }
        return pos;
    }

    //<i,j>格子上可以填的所有数字
    public List<Integer> getAvailableDigits(int i, int j) {
        List<Integer> availableDigits = new ArrayList<>();

        for (int digit = 1; digit <= 9; digit++)
            //行、列、方格都可以填digit
            if (row[i][digit] && col[j][digit] && box[boxIndex(i, j)][digit]) {
                availableDigits.add(digit);
            }
        return availableDigits;
    }

    int boxIndex(int i, int j) {
        return i / 3 * 3 + j / 3;
    }

    boolean[][] row; // [行号][1-9是否可用] 0浪费掉
    boolean[][] col; // [列号][1-9是否可用] 0浪费掉
    boolean[][] box;
}
