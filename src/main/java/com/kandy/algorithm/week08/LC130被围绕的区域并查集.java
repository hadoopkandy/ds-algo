package com.kandy.algorithm.week08;

public class LC130被围绕的区域并查集 {
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        fa = new int[m * n + 1];//外部无限大区域
        for (int i = 0; i <= m * n; i++) fa[i] = i;

        int[] dx = new int[]{-1, 0, 0, 1};
        int[] dy = new int[]{0, -1, 1, 0};
        int outside = m * n;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') continue;
                //点O
                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    if (ni < 0 || nj < 0 || ni >= m || nj >= n) {
                        //边界点O与外部无限大区域点mn合并
                        unionSet(num(i, j), outside);
                    } else {
                        if (board[ni][nj] == 'O')
                            unionSet(num(i, j), num(ni, nj));
                    }
                }
            }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                //这个点是O且与外部不相连，就将其改成X
                if (board[i][j] == 'O' && find(num(i, j)) != find(outside))
                    board[i][j] = 'X';
    }

    int find(int x) {
        if (x == fa[x]) return x;
        return fa[x] = find(fa[x]);
    }

    void unionSet(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) fa[x] = y;
    }

    //行列坐标变成一维编号
    int num(int i, int j) {
        return i * n + j;
    }

    int m, n;
    int[] fa;
}
