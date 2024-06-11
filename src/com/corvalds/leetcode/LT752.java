package com.corvalds.leetcode;

import java.util.*;

/**
 * 问题简述：开锁<br/>
 * 问题思路：将锁值视为多叉树节点，不同锁值的转换构成一个多叉树，将问题转换为使用BFS搜索最早出现目标节点的层级，对于遍历过程中存在的重复节点问题，使用一个额外的Set集合存储已经遍历过的节点<br/>
 * 问题链接：<a href="https://leetcode.com/problems/open-the-lock/">LT-752</a>
 */
public class LT752 {

    public int openLock(String[] deadends, String target) {
        Set<String> usedLockSet = new HashSet<>(Arrays.asList(deadends));
        if (usedLockSet.contains(target)) {
            return -1;
        }
        if (usedLockSet.contains("0000")) {
            return -1;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(new TreeNode("0000", 0));
        usedLockSet.add("0000");
        while (!queue.isEmpty()) {
            TreeNode currentLockNode = queue.poll();
            if (currentLockNode.lock.equals(target)) {
                return currentLockNode.depth;
            }

            for (String nextLock : this.generateNextLock(currentLockNode.lock)) {
                if (usedLockSet.contains(nextLock)) {
                    continue;
                }

                usedLockSet.add(nextLock);
                queue.add(new TreeNode(nextLock, currentLockNode.depth + 1));
            }
        }

        return -1;
    }

    private List<String> generateNextLock(String currentLock) {
        char[] currentLockCharArray = currentLock.toCharArray();
        char[] fixedCurrentLockCharArray = currentLock.toCharArray();

        List<String> nextLockList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            currentLockCharArray[i] = this.moveForward(fixedCurrentLockCharArray[i]);
            nextLockList.add(String.valueOf(currentLockCharArray));
            currentLockCharArray[i] = this.moveBackward(fixedCurrentLockCharArray[i]);
            nextLockList.add(String.valueOf(currentLockCharArray));
            // 恢复原值
            currentLockCharArray[i] = fixedCurrentLockCharArray[i];
        }

        return nextLockList;
    }

    private char moveForward(char src) {
        if (src == '9') {
            return '0';
        }
        src += 1;
        return src;
    }

    private char moveBackward(char src) {
        if (src == '0') {
            return '9';
        }
        src -= 1;
        return src;
    }

    public static class TreeNode {
        public String lock;
        public int depth;

        public TreeNode(String lock, int depth) {
            this.lock = lock;
            this.depth = depth;
        }
    }
}
