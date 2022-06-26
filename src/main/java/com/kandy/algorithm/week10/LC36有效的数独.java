package com.kandy.algorithm.week10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LC36有效的数独 {
    public boolean isValidSudoku(char[][] board) {
        //每行的hashset
        List<HashSet<Character>> row = new ArrayList<>();
        //每列的的hashset
        List<HashSet<Character>> col = new ArrayList<>();
        //每个方块的hashset
        List<HashSet<Character>> box = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            row.add(new HashSet<>()); //行
            col.add(new HashSet<>());//列
            box.add(new HashSet<>());//方块
        }
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                char digit = board[i][j];
                if (digit == '.') continue;//空白格用 '.' 表示,还没填数字
                //行方向已经填过就不是数独
                if (row.get(i).contains(digit)) return false;
                row.get(i).add(digit);

                //列方向已经填过就不是数独
                if (col.get(j).contains(digit)) return false;
                col.get(j).add(digit);

                //把i j先变成二维方块坐标，再变成一维方块编号
                int k = boxIndex(i, j);
                //第k个方块已经填过就不是数独
                if (box.get(k).contains(digit)) return false;
                box.get(k).add(digit);
            /*
                (0,0)  (0,1) (0,2)
                (1,0)  (1,1) (1,2)
                (2,0)  (2,1) (2,2)

                (x,y) => x*3+y
            */
            }
        return true;
    }

    int boxIndex(int i, int j) {
        return i / 3 * 3 + j / 3;
    }
}
