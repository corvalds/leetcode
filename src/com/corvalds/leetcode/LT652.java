package com.corvalds.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 问题简述：寻找二叉树重复子树<br/>
 * 问题思路：使用一个外部Map存储完整后序序列的映射，然后在递归的后序位置检查外部Map是否存在重复二叉树<br/>
 * 问题链接：<a href="https://leetcode.com/problems/find-duplicate-subtrees/description">LT-652</a>
 */
public class LT652 {

    public final static String NULL = "#";

    public final static String DELIMETER = ",";

    private List<TreeNode> duplicateTreeNodeList = new LinkedList<>();
    private Map<String, Integer> treeMap = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        this.traverse(root);
        return this.duplicateTreeNodeList;
    }

    private String traverse(TreeNode root) {
        if (root == null) {
            return NULL;
        }

        String leftTreeString = this.traverse(root.left);
        String rightTreeString = this.traverse(root.right);
        String presentTreeString = leftTreeString + DELIMETER + rightTreeString + DELIMETER + root.val;

        Integer presentTreeDuplicateCount = treeMap.getOrDefault(presentTreeString, 0);
        if (presentTreeDuplicateCount == 1) {
            this.duplicateTreeNodeList.add(root);
        }
        treeMap.put(presentTreeString, presentTreeDuplicateCount + 1);
        return presentTreeString;
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
