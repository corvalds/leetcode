package com.corvalds.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 问题简述：二叉树最小深度<br/>
 * 问题思路：使用BFS算法遍历二叉树，每遍历一个层级的时候计数加一，当遍历到某一层级存在叶子节点时，返回计数结果<br/>
 * 问题链接：<a href="https://leetcode.com/problems/minimum-depth-of-binary-tree">LT-111</a>
 */
public class LT111 {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 使用BFS算法遍历二叉树，每遍历一个层级的时候计数加一，当遍历到某一层级存在叶子节点时，返回计数结果
        int minDepth = 0;
        Deque<TreeNodeWithDepth> queue = new ArrayDeque<>();
        queue.add(new TreeNodeWithDepth(root, 1));
        while (!queue.isEmpty()) {
            TreeNodeWithDepth currentNode = queue.poll();
            if (currentNode.left == null && currentNode.right == null) {
                minDepth = currentNode.depth;
                break;
            }
            if (currentNode.left != null) {
                queue.add(new TreeNodeWithDepth(currentNode.left, currentNode.depth + 1));
            }
            if (currentNode.right != null) {
                queue.add(new TreeNodeWithDepth(currentNode.right, currentNode.depth + 1));
            }
        }
        return minDepth;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
    }

    public static class TreeNodeWithDepth {
        public TreeNode node;
        public TreeNode left;
        public TreeNode right;
        public int depth;
        public TreeNodeWithDepth(TreeNode node, int depth) {
            this.node = node;
            this.left = node.left;
            this.right = node.right;
            this.depth = depth;
        }
    }
}
