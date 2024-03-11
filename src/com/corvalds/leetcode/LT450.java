package com.corvalds.leetcode;

/**
 * 问题简述：删除BST节点<br/>
 * 问题思路：根据BST特性，二分递归查找目标节点，当找到目标节点后，如果目标节点存在左右子树（不存在左右子树的场景处理较简单，此处不赘述），则将右节点作为新的根节点，将右节点的左子树嫁接到左子树中最右端<br/>
 * 问题链接：<a href="https://leetcode.com/problems/delete-node-in-a-bst/description/">LT-450</a>
 */
public class LT450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        return traverse(root, key);
    }

    private TreeNode traverse(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val > key) {
            root.left = this.traverse(root.left, key);
            return root;
        }

        if (root.val < key) {
            root.right = this.traverse(root.right, key);
            return root;
        }

        // 需要删除的为当前节点
        if (root.left == null && root.right == null) {
            return null;
        }

        if (root.left == null) {
            return root.right;
        }

        if (root.right == null) {
            return root.left;
        }

        // 需要删除的节点存在左右子树
        TreeNode needMoveTree = root.right.left;
        root.right.left = root.left;
        if (needMoveTree != null) {
            // 右子树的左子树不为空，需要将其嫁接到左子树最右端
            TreeNode targetNode = root.left;
            while (targetNode.right != null) {
                targetNode = targetNode.right;
            }
            targetNode.right = needMoveTree;
        }
        return root.right;
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
