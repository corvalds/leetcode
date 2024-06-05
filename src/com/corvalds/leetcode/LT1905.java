package com.corvalds.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题简述：子岛屿数量<br/>
 * 问题思路：遍历grid2的元素，当遇到陆地时，使用DFS算法找出整个岛屿涉及到的陆地单元格，然后将检查这些陆地单元格在grid1中是否也为陆地，如果是的话则计数加1，再完成上述判断后，将grid2中所涉及到的陆地单元格全部置为海水<br/>
 * 问题链接：<a href="https://leetcode.com/problems/count-sub-islands/description/">LT-1905</a>
 */
public class LT1905 {
    private int[][] directions = new int[][]{new int[]{-1, 0}, new int[]{0, -1}, new int[]{1, 0}, new int[]{0, 1}};

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[0].length; j++) {
                if (grid2[i][j] == 0) {
                    continue;
                }

                List<int[]> landCellList = new ArrayList<>();
                this.traverseFind(grid2, i, j, landCellList);
                boolean isSubIsland = true;
                for (int[] landCell : landCellList) {
                    if (grid1[landCell[0]][landCell[1]] == 0) {
                        isSubIsland = false;
                    }
                }
                count = count + (isSubIsland ? 1 : 0);
            }
        }
        return count;
    }

    private void traverseFind(int[][] grid, int x, int y, List<int[]> landCellList) {
        if (grid[x][y] == 0) {
            return;
        }

        landCellList.add(new int[]{x, y});
        grid[x][y] = 0;
        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length) {
                this.traverseFind(grid, nextX, nextY, landCellList);
            }
        }
    }
}
