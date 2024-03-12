package com.corvalds.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：构建BST树并输出所有构建的BST树<br/>
 * 问题思路：定义一个入参为根节点值、本BST树合法取值范围[min, max]的方法，该方法中会递归地构建出所有潜在的左右子树，然后再将左右子树组合形成所有可能的BST树<br/>
 * 问题链接：<a href="https://leetcode.com/problems/unique-binary-search-trees-ii/">LT-95</a>
 */
public class LT95 {

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            res.addAll(this.traverse(i, 1, n));
        }
        return res;
    }

    // 返回以n为根节点的BST，该BST数字范围限定在[min, max]中，同时n >= min且n <= max
    private List<TreeNode> traverse(int n, int min, int max) {
        List<TreeNode> rootList = new LinkedList<>();
        if (min == max) {
            rootList.add(new TreeNode(min, null, null));
            return rootList;
        }

        List<TreeNode> leftTreeNodeCandidateList = new LinkedList<>();
        int leftIdx = min;
        while (leftIdx < n) {
            leftTreeNodeCandidateList.addAll(this.traverse(leftIdx, min, n - 1));
            leftIdx++;
        }

        List<TreeNode> rightTreeNodeCandidateList = new LinkedList<>();
        int rightIdx = n + 1;
        while (rightIdx <= max) {
            rightTreeNodeCandidateList.addAll(this.traverse(rightIdx, n + 1, max));
            rightIdx++;
        }

        if (!leftTreeNodeCandidateList.isEmpty() && rightTreeNodeCandidateList.isEmpty()) {
            for (TreeNode leftTree : leftTreeNodeCandidateList) {
                rootList.add(new TreeNode(n, leftTree, null));
            }
        } else if (leftTreeNodeCandidateList.isEmpty() && !rightTreeNodeCandidateList.isEmpty()) {
            for (TreeNode rightTree : rightTreeNodeCandidateList) {
                rootList.add(new TreeNode(n, null, rightTree));
            }
        } else {
            for (TreeNode leftTree : leftTreeNodeCandidateList) {
                for (TreeNode rightTree : rightTreeNodeCandidateList) {
                    rootList.add(new TreeNode(n, leftTree, rightTree));
                }
            }
        }

        return rootList;
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
