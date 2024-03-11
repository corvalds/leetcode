package com.corvalds.leetcode;

/**
 * 问题简述：二叉搜索树转变为累加树（问题同1038）<br/>
 * 问题思路：<br/>
 * 问题链接：<a href="https://leetcode.com/problems/convert-bst-to-greater-tree/description/">LT-538</a>
 */
public class LT538 {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        this.traverse(root);
        return root;
    }

    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        this.traverse(root.right);
        sum += root.val;
        root.val = sum;
        this.traverse(root.left);
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
