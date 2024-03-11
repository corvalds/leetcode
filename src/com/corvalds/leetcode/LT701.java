package com.corvalds.leetcode;

/**
 * 问题简述：新增BST节点<br/>
 * 问题思路：根据BST特性，递归查找插入位置，该实现版本只会将新节点插入到叶子节点位置<br/>
 * 问题链接：<a href="https://leetcode.com/problems/insert-into-a-binary-search-tree/">LT-701</a>
 */
public class LT701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        return traverse(root, val);
    }

    private TreeNode traverse(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val >= val) {
            root.left = this.traverse(root.left, val);
            return root;
        } else {
            root.right = this.traverse(root.right, val);
            return root;
        }
    }

    public static class TreeNode {

        public int val;

        public TreeNode left;

        public TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
