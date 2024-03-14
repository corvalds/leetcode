package com.corvalds.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：合并两个BST树为一个链表<br/>
 * 问题思路：中序遍历BST树得到两个BST有序列表，再合并两个有序列表<br/>
 * 问题链接：<a href="https://leetcode.com/problems/all-elements-in-two-binary-search-trees/">LT-1305</a>
 */
public class LT1305 {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> tree1List = new LinkedList<>();
        traverse(root1, tree1List);
        List<Integer> tree2List = new LinkedList<>();
        traverse(root2, tree2List);

        List<Integer> resultList = new LinkedList<>();
        int count = tree1List.size() + tree2List.size();
        while (count > 0) {
            if (tree1List.size() == 0) {
                while (tree2List.size() > 0) {
                    resultList.add(tree2List.remove(0));
                    count--;
                }
                continue;
            }
            if (tree2List.size() == 0) {
                while (tree1List.size() > 0) {
                    resultList.add(tree1List.remove(0));
                    count--;
                }
                continue;
            }

            int tree1Value = tree1List.get(0);
            int tree2Value = tree2List.get(0);
            if (tree1Value <= tree2Value) {
                resultList.add(tree1List.remove(0));
            } else {
                resultList.add(tree2List.remove(0));
            }
            count--;
        }

        return resultList;
    }

    private void traverse(TreeNode root, List<Integer> treeList) {
        if (root == null) {
            return;
        }

        traverse(root.left, treeList);
        treeList.add(root.val);
        traverse(root.right, treeList);
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
