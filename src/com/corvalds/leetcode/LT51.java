package com.corvalds.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题简述：N皇后问题<br/>
 * 问题思路：采用回溯算法，本解法的选项集需要遍历整个棋盘，同时对于不对选项集所在行进行限制，导致整体效率和代码简洁度不够，可以利用皇后所在行不能再有其他皇后这一个特点实现一个更简洁的版本<br/>
 * 问题链接：<a href="https://leetcode.com/problems/n-queens/description/">LT-51</a>
 */
public class LT51 {

    private List<List<String>> result;

    private int[][] board;

    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();
        board = new int[n][n];
        traverse(0, 0);
        return result;
    }

    private void traverse(int placedQueenCount, int baseRow) {
        if (placedQueenCount == board.length) {
            result.add(generateBoardString());
            return;
        }
        if (baseRow >= board.length) {
            return;
        }

        for (int i = baseRow; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    continue;
                }

                placeQueen(i, j);
                placedQueenCount++;
                traverse(placedQueenCount, i + 1);
                placedQueenCount--;
                removeQueen(i, j);
            }
        }
    }

    private void placeQueen(int x, int y) {
        // 将当前行、列、对角线设置为已访问
        for (int j = 0; j < board[x].length; j++) {
            board[x][j] += 1;
        }
        for (int i = 0; i < board.length; i++) {
            board[i][y] += 1;
        }
        for (int i = x + 1, j = y + 1; i < board.length && j < board.length; i++, j++) {
            board[i][j] += 1;
        }
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            board[i][j] += 1;
        }
        for (int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--) {
            board[i][j] += 1;
        }
        for (int i = x - 1, j = y + 1; i >= 0 && j < board.length; i--, j++) {
            board[i][j] += 1;
        }

        board[x][y] = -1;
    }

    private void removeQueen(int x, int y) {
        // 将当前行、列、对角线设置为未访问
        for (int j = 0; j < board[x].length; j++) {
            board[x][j] -= 1;
        }
        for (int i = 0; i < board.length; i++) {
            board[i][y] -= 1;
        }
        for (int i = x + 1, j = y + 1; i < board.length && j < board.length; i++, j++) {
            board[i][j] -= 1;
        }
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            board[i][j] -= 1;
        }
        for (int i = x + 1, j = y - 1; i < board.length && j >= 0; i++, j--) {
            board[i][j] -= 1;
        }
        for (int i = x - 1, j = y + 1; i >= 0 && j < board.length; i--, j++) {
            board[i][j] -= 1;
        }

        board[x][y] = 0;
    }

    private List<String> generateBoardString() {
        List<String> boardStringList = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            boardStringList.add(generateRowString(i));
        }
        return boardStringList;
    }

    private String generateRowString(int rowIdx) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < board[rowIdx].length; j++) {
            if (board[rowIdx][j] == -1) {
                sb.append("Q");
                continue;
            }
            sb.append(".");
        }
        return sb.toString();
    }
}
