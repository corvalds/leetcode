package com.corvalds.leetcode;

/**
 * 问题简述：BST中第k小的元素<br/>
 * 问题思路：用中序遍历BST，使用一个外部变量记录当前节点为第几位，当符合条件时直接返回<br/>
 * 问题链接：<a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst/">LT-230</a>
 */
public class LT230 {

    private int count = 0;

    private TreeNode res;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return this.res.val;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        traverse(root.left, k);
        count++;
        if (count == k) {
            res = root;
            return;
        }
        traverse(root.right, k);
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
