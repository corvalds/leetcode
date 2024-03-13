package com.corvalds.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：寻找最大和的BST子树<br/>
 * 问题思路：定义一个外部变量记录结果，同时定义一个方法递归地返回以本节点为根的树的最大值、最小值、累加和、是否BST树标识，在每个递归节点，根据左右子树的最大值、最小值、是否BST树标识得到本节点为根的树是否为BST树，是的话则根据情况刷新答案<br/>
 * 问题链接：<a href="https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/description/">LT-1373</a>
 */
public class LT1373 {

    private int result = 0;

    public int maxSumBST(TreeNode root) {
        traverse(root);
        return result;
    }

    /**
     * 响应数组中：
     * 第一位为该二叉树是否为BST树标识，0为非BST树，1为BST树
     * 第二位为该二叉树最大值
     * 第三位为该二叉树最小值
     * 第三位为该二叉树累加和
     */
    private int[] traverse(TreeNode root) {
        if (root == null) {
            // 基准情况下最大值、最小值需要反着来设置
            return new int[]{1, Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        }

        int[] leftTreeInfo = traverse(root.left);
        int[] rightTreeInfo = traverse(root.right);

        if (leftTreeInfo[0] == 1 && rightTreeInfo[0] == 1 && leftTreeInfo[1] < root.val && rightTreeInfo[2] > root.val) {
            // 本树为BST树的前提是：左右子树均为BST，且本节点值大于左子树最大值，小于右子树最小值
            result = Math.max(result, leftTreeInfo[3] + rightTreeInfo[3] + root.val);
            return new int[]{1, Math.max(root.val, rightTreeInfo[1]), Math.min(root.val, leftTreeInfo[2]), leftTreeInfo[3] + rightTreeInfo[3] + root.val};
        }

        return new int[]{0, Math.max(root.val, Math.max(leftTreeInfo[1], rightTreeInfo[1])), Math.min(root.val, Math.min(leftTreeInfo[2], rightTreeInfo[2])), leftTreeInfo[3] + rightTreeInfo[3] + root.val};
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
