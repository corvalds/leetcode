package com.corvalds.leetcode;

/**
 * 问题简述：封闭岛屿数量<br/>
 * 问题思路：使用DFS算法将边界岛屿及关联岛屿改为海面，然后再统计剩余无法转换为海面的岛屿数量<br/>
 * 问题链接：<a href="https://leetcode.com/problems/number-of-enclaves/description/">LT-1020</a>
 */
public class LT1020 {
    private int[][] directions = new int[][]{new int[]{-1, 0}, new int[]{0, -1}, new int[]{1, 0}, new int[]{0, 1}};

    public int numEnclaves(int[][] grid) {
        int m = grid.length;;
        int n = grid[0].length;
        // 通过DFS算法，将边界上的land cell更新为sea cell，再统计剩余的land cell数量
        for (int i = 0; i < n; i++) {
            this.traverseTransform(grid, 0, i);
            this.traverseTransform(grid, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            this.traverseTransform(grid, i, 0);
            this.traverseTransform(grid, i, n - 1);
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private void traverseTransform(int[][] grid, int x, int y) {
        if (grid[x][y] == 0) {
            return;
        }

        grid[x][y] = 0;
        for (int[] direction : this.directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (nextX < grid.length && nextX >= 0 && nextY < grid[0].length && nextY >= 0) {
                this.traverseTransform(grid, nextX, nextY);
            }
        }
    }
}
