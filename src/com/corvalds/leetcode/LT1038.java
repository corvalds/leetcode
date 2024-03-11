package com.corvalds.leetcode;

/**
 * 问题简述：二叉搜索树转变为累加树<br/>
 * 问题思路：利用一个外部变量记录当前累加值，然后按特殊中序（先右子树，再根节点，最后左子树）遍历BST，遍历到有效节点将当前值加上累加值<br/>
 * 问题链接：<a href="https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/description/">LT-1038</a>
 */
public class LT1038 {
    private int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
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