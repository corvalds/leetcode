package com.corvalds.leetcode;

/**
 * 问题简述：封闭岛屿数量<br/>
 * 问题思路：主要利用DFS算法思路，先讲边界上的陆地单元格转变为海水单元格，然后进行统计：每当遇到一个陆地单元格，则先将计数+1，然后再用DFS算法将该陆地单元格及关联单元格转变为海水单元格，再进行下一次计数<br/>
 * 问题链接：<a href="https://leetcode.com/problems/number-of-closed-islands/">LT-1254</a>
 */
public class LT1254 {
    private int[][] directions = new int[][]{new int[]{-1, 0}, new int[]{0, -1}, new int[]{1, 0}, new int[]{0, 1}};

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int y = 0; y < n; y++) {
            this.traverseTransform(grid, 0, y);
            this.traverseTransform(grid, m - 1, y);
        }
        for (int x = 0; x < m; x++) {
            this.traverseTransform(grid, x, 0);
            this.traverseTransform(grid, x, n - 1);
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }

                count++;
                this.traverseTransform(grid, i, j);
            }
        }
        return count;
    }

    public void traverseTransform(int[][] grid, int x, int y) {
        if (grid[x][y] == 1) {
            return;
        }

        grid[x][y] = 1;
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length) {
                this.traverseTransform(grid, nextX, nextY);
            }
        }
    }
}
