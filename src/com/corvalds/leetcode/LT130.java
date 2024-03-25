package com.corvalds.leetcode;

/**
 * 问题简述：保留符合条件的节点<br/>
 * 问题思路：使用DFS算法遍历四个边上的节点，遍历到的节点更新为#号，然后再遍历整个矩阵，将O更新为X，将#更新为O<br/>
 * 问题链接：<a href="https://leetcode.com/problems/surrounded-regions/description/">LT-130</a>
 */
public class LT130 {

    public void solve(char[][] board) {
        int rowNum = board.length;
        int columnNum = board[0].length;
        // 遍历第一行
        int rowNo = 0;
        int columnNo = 0;
        while (columnNo < columnNum) {
            traverseChangeChar(board, rowNum, rowNo, columnNum, columnNo);
            columnNo++;
        }
        // 遍历最后一行
        rowNo = rowNum - 1;
        columnNo = 0;
        while (columnNo < columnNum) {
            traverseChangeChar(board, rowNum, rowNo, columnNum, columnNo);
            columnNo++;
        }
        // 遍历第一列（仅需要从第二行开始，到倒数第二行结束）
        rowNo = 1;
        columnNo = 0;
        while (rowNo < rowNum) {
            traverseChangeChar(board, rowNum, rowNo, columnNum, columnNo);
            rowNo++;
        }
        // 遍历最后一列（仅需要从第二行开始，到倒数第二行结束）
        rowNo = 1;
        columnNo = columnNum - 1;
        while (rowNo < rowNum) {
            traverseChangeChar(board, rowNum, rowNo, columnNum, columnNo);
            rowNo++;
        }

        rowNo = 0;
        while (rowNo < rowNum) {
            int innerColumnNo = 0;
            while (innerColumnNo < columnNum) {
                if (board[rowNo][innerColumnNo] == 'O') {
                    board[rowNo][innerColumnNo] = 'X';
                }
                if (board[rowNo][innerColumnNo] == '#') {
                    board[rowNo][innerColumnNo] = 'O';
                }
                innerColumnNo++;
            }
            rowNo++;
        }
    }

    private void traverseChangeChar(char[][] board, int rowNum, int rowNo, int columnNum, int columnNo) {
        if (board[rowNo][columnNo] != 'O') {
            return;
        }
        board[rowNo][columnNo] = '#';
        if (rowNo - 1 >= 0) {
            traverseChangeChar(board, rowNum, rowNo - 1, columnNum, columnNo);
        }
        if (rowNo + 1 < rowNum) {
            traverseChangeChar(board, rowNum, rowNo + 1, columnNum, columnNo);
        }
        if (columnNo - 1 >= 0) {
            traverseChangeChar(board, rowNum, rowNo, columnNum, columnNo - 1);
        }
        if (columnNo + 1 < columnNum) {
            traverseChangeChar(board, rowNum, rowNo, columnNum, columnNo + 1);
        }
    }
}
