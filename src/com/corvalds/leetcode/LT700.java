package com.corvalds.leetcode;

/**
 * 问题简述：查找BST<br/>
 * 问题思路：二分递归查找BST<br/>
 * 问题链接：<a href="https://leetcode.com/problems/search-in-a-binary-search-tree/description/">LT-700</a>
 */
public class LT700 {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val > val) {
            return searchBST(root.left, val);
        }
        if (root.val < val) {
            return searchBST(root.right, val);
        }

        return root;
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
