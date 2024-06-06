package com.corvalds.leetcode;

/**
 * 问题简述：最大岛屿面积<br/>
 * 问题思路：遍历所有单元格，当遇到一个陆地单元格时，使用DFS算法计算出岛屿大小，然后对比当前岛屿大小与最大岛屿大小哪个大，在计算过程中将已计算的陆地单元格更改为海洋单元格<br/>
 * 问题链接：<a href="https://leetcode.com/problems/max-area-of-island/">LT-695</a>
 */
public class LT695 {

    private int[][] directions = new int[][]{new int[]{-1, 0}, new int[]{0, -1}, new int[]{1, 0},  new int[]{0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        int maxIslandArea = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == 0) {
                    continue;
                }

                maxIslandArea = Math.max(maxIslandArea, this.traverseCount(grid, x, y));
            }
        }
        return maxIslandArea;
    }

    private int traverseCount(int[][] grid, int x, int y) {
        if (grid[x][y] == 0) {
            return 0;
        }

        grid[x][y] = 0;
        int sum = 1;
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length) {
                sum += this.traverseCount(grid, nextX, nextY);
            }
        }
        return sum;
    }
}
