package com.corvalds.leetcode;

/**
 * 问题简述：校验BST树<br/>
 * 问题思路：递归验证BST树，每次递归时判断当前节点和左右子树的关系，同时判断当前节点是否位于指定范围内，均满足的话，递归判断左右子树是否为BST树<br/>
 * 问题链接：<a href="https://leetcode.com/problems/validate-binary-search-tree/">LT-98</a>
 */
public class LT98 {

    public boolean isValidBST(TreeNode root) {
        return traverse(root, null, null);
    }

    private boolean traverse(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        // 超出指定范围
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }

        // 和左右子节点进行判断
        if (root.left != null && root.val < root.left.val) {
            return false;
        }
        if (root.right != null && root.val > root.right.val) {
            return false;
        }

        return traverse(root.left, min, root.val) && traverse(root.right, root.val, max);
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
