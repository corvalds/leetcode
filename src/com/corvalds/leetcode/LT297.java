package com.corvalds.leetcode;

/**
 * 问题简述：二叉树的序列化和反序列化<br/>
 * 问题思路：从二叉树单独的的完整前序序列和完整后序序列都可以构建出二叉树，所谓“完整的”是指当碰到NULL时也要记录下来，所以此处的序列化、反序列化方案可以基于二叉树的完整前序序列和完整后序序列<br/>
 * 问题链接：<a href="https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description">LT-297</a>
 */
public class LT297 {

    public String serialize(TreeNode root) {
        return null;
    }

    public TreeNode deserialize(String data) {
        return null;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}