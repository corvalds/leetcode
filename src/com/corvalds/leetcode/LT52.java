package com.corvalds.leetcode;

/**
 * 问题简述：计算N皇后问题不重复解数量<br/>
 * 问题思路：利用每行只能有一个皇后的特点，结合回溯算法每次回溯时只尝试在当前行找出放置皇后的地方，然后再对下一行进行回溯，直到无法在当前行放置皇后或在所有行都已经放置了皇后为止<br/>
 * 问题链接：<a href="https://leetcode.cn/problems/n-queens-ii/description">LT-52</a>
 */
public class LT52 {
    private boolean[][] board;

    private int result;

    public int totalNQueens(int n) {
        this.board = new boolean[n][n];
        this.result = 0;
        traverse(n, 0);
        return this.result;
    }

    private void traverse(int n, int currentRow) {
        if (currentRow == n) {
            this.result++;
            return;
        }

        for (int column = 0; column < n; column++) {
            if (!canPlaceQueen(n, currentRow, column)) {
                continue;
            }
            this.board[currentRow][column] = true;
            this.traverse(n, currentRow + 1);
            this.board[currentRow][column] = false;
        }
    }

    private boolean canPlaceQueen(int n, int targetRow, int targetColumn) {
        // 检查正上方是否存在皇后
        for (int rowIdx = targetRow - 1; rowIdx >= 0; rowIdx--) {
            if (this.board[rowIdx][targetColumn]) {
                return false;
            }
        }
        // 检查左、右斜上方是否存在皇后
        for (int rowIdx = targetRow - 1, leftColIdx = targetColumn - 1, rightColIdx = targetColumn + 1; rowIdx >= 0; rowIdx--, leftColIdx--, rightColIdx++) {
            if (leftColIdx >= 0 && this.board[rowIdx][leftColIdx]) {
                return false;
            }
            if (rightColIdx < n && this.board[rowIdx][rightColIdx]) {
                return false;
            }
        }
        return true;
    }
}
