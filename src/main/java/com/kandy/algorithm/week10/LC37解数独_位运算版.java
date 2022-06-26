package com.kandy.algorithm.week10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC37解数独_位运算版 {
    public void solveSudoku(char[][] board) {
        row = new int[9];
        col = new int[9];
        box = new int[9];
        powers = new HashMap<>();
        for (int i = 1; i <= 9; i++) powers.put(1 << i, i);
        for (int i = 0; i < 9; i++)
            row[i] = col[i] = box[i] = ((1 << 9) - 1) << 1;
        // 1000000000
        //  111111111
        // 1111111110
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int digit = board[i][j] - '0';
                if ((row[i] >> digit & 1) == 1) row[i] = row[i] ^ (1 << digit);
                if ((col[j] >> digit & 1) == 1) col[j] = col[j] ^ (1 << digit);
                int k = boxIndex(i, j);
                if ((box[k] >> digit & 1) == 1) box[k] = box[k] ^ (1 << digit);
            }
        dfs(board);
    }

    boolean dfs(char[][] board) {
        // 找最确定的（可能性最小的）格子
        int[] pos = findMinimumProbability(board);
        int x = pos[0];
        int y = pos[1];
        if (x == -1) return true;
        List<Integer> availableDigits = getAvailableDigits(x, y);
        for (int digit : availableDigits) {
            board[x][y] = (char) ('0' + digit);
            row[x] ^= (1 << digit);
            col[y] ^= (1 << digit);
            box[boxIndex(x, y)] ^= (1 << digit);
            if (dfs(board)) return true;
            box[boxIndex(x, y)] |= (1 << digit);
            col[y] |= (1 << digit);
            row[x] |= (1 << digit);
            board[x][y] = '.';
        }
        return false;
    }

    int[] findMinimumProbability(char[][] board) {
        int minValue = 10;
        int[] pos = new int[]{-1, -1};
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') continue;
                List<Integer> availableDigits = getAvailableDigits(i, j);
                if (availableDigits.size() < minValue) {
                    minValue = availableDigits.size();
                    pos = new int[]{i, j};
                }
            }
        return pos;
    }

    List<Integer> getAvailableDigits(int i, int j) {
        List<Integer> availableDigits = new ArrayList<>();
        int state = row[i] & col[j] & box[boxIndex(i, j)];
        while (state > 0) {
            int lowbit = state & -state;
            availableDigits.add(powers.get(lowbit));
            state -= lowbit;
        }
        return availableDigits;
    }

    int boxIndex(int i, int j) {
        return (i / 3) * 3 + (j / 3);
    }

    int[] row;
    int[] col;
    int[] box;
    HashMap<Integer, Integer> powers;
}
