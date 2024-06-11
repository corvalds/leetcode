package com.corvalds.leetcode;

import java.util.*;

/**
 * 问题简述：滑动谜题<br/>
 * 问题思路：将棋盘起始状态抽象为多叉树根节点，以此为基础，当数字0移动时，产生的不同的棋盘状态为该根节点的子节点，然后以此形成一棵多叉树，使用BFS算法遍历该多叉树，找到出现[1,2,3,4,5,0]排列的子节点的最小深度<br/>
 * 问题链接：<a href="https://leetcode.com/problems/sliding-puzzle/">LT-773</a>
 */
public class LT773 {
    private int[][] directions = new int[][]{new int[]{-1, 0}, new int[]{0, -1}, new int[]{1, 0}, new int[]{0, 1}};

    public int slidingPuzzle(int[][] board) {
        // 将棋盘起始状态抽象为多叉树根节点，以此为基础，当数字0移动时，产生的不同的棋盘状态为该根节点的子节点，然后以此形成一棵多叉树
        // 使用BFS算法遍历该多叉树，找到出现[1,2,3,4,5,0]排列的子节点的最小深度
        Set<String> usedBoardStateSet = new HashSet<>();

        String initState = this.generateState(board);
        Deque<String> queue = new ArrayDeque<>();
        queue.add(initState);
        usedBoardStateSet.add(initState);
        int depth = 0;
        while (!queue.isEmpty()) {
            int currentDepthStateCount = queue.size();
            for (int i = 0; i < currentDepthStateCount; i++) {
                String currentState = queue.poll();
                if (currentState.equals("123450")) {
                    return depth;
                }

                for (String nextState : this.generateNextBoard(this.generateBoard(currentState))) {
                    if (usedBoardStateSet.contains(nextState)) {
                        continue;
                    }
                    queue.add(nextState);
                    usedBoardStateSet.add(nextState);
                }
            }
            depth++;
        }

        return -1;
    }

    private String generateState(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private int[][] generateBoard(String state) {
        int intState = Integer.valueOf(state);
        int[][] board = new int[2][3];
        for (int i = 1; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                board[i][j] = intState % 10;
                intState = intState / 10;
            }
        }
        return board;
    }

    private List<String> generateNextBoard(int[][] board) {
        List<String> stateList = new ArrayList<>();

        int zeroI = 0, zeroJ = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                    break;
                }
            }
        }

        for (int[] direction : directions) {
            int nextZeroI = zeroI + direction[0];
            int nextZeroJ = zeroJ + direction[1];
            if (nextZeroI >= 0 && nextZeroI < board.length && nextZeroJ >= 0 && nextZeroJ < board[0].length) {
                this.exchange(board, zeroI, zeroJ, nextZeroI, nextZeroJ);
                stateList.add(this.generateState(board));
                this.exchange(board, zeroI, zeroJ, nextZeroI, nextZeroJ);
            }
        }

        return stateList;
    }

    private void exchange(int[][] board, int srcI, int srcJ, int destI, int destJ) {
        int tmp = board[srcI][srcJ];
        board[srcI][srcJ] = board[destI][destJ];
        board[destI][destJ] = tmp;
    }
}
