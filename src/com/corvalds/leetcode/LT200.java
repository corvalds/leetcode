package com.corvalds.leetcode;

/**
 * 问题简述：计算岛屿数量<br/>
 * 问题思路：遍历所有单元格，当碰到陆地单元格时，计数加一，同时使用DFS算法将陆地单元格转换为海洋单元格<br/>
 * 问题链接：<a href="https://leetcode.com/problems/number-of-islands/">LT-200</a>
 */
public class LT200 {

    private int[][] directions = new int[][]{new int[]{-1, 0}, new int[]{0, -1}, new int[]{1, 0}, new int[]{0, 1}};

    public int numIslands(char[][] grid) {
        // 遍历所有单元格，当碰到陆地单元格时，计数加一，同时使用DFS算法将陆地单元格转换为海洋单元格
        int count = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == '0') {
                    continue;
                }
                count++;
                this.traverseTransform(grid, x, y);
            }
        }
        return count;
    }

    public void traverseTransform(char[][] grid, int x, int y) {
        if (grid[x][y] == '0') {
            return;
        }

        grid[x][y] = '0';
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length) {
                this.traverseTransform(grid, nextX, nextY);
            }
        }
    }
}
