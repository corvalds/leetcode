package com.corvalds.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 问题简述：二叉树的序列化和反序列化<br/>
 * 问题思路：从二叉树单独的的完整前序序列和完整后序序列都可以构建出二叉树，所谓“完整的”是指当碰到NULL时也要记录下来，所以此处的序列化、反序列化方案可以基于二叉树的完整前序序列和完整后序序列<br/>
 * 问题链接：<a href="https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description">LT-297</a>
 */
public class LT297 {

    public static final String NULL = "#";

    public static final String DELIMITER = ",";

    /**
     * 将二叉树以后序序列的形式序列化，空节点使用#替换，节点间用逗号分割
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        return this.serialize(root, new StringBuilder()).toString();
    }

    private StringBuilder serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL);
            return sb;
        }

        this.serialize(root.left, sb);
        sb.append(DELIMITER);
        this.serialize(root.right, sb);
        sb.append(DELIMITER);
        sb.append(root.val);
        return sb;
    }

    /**
     * 利用栈结构从二叉树后序序列中反序列化出二叉树
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        Deque<String> stack = new ArrayDeque<>();
        for (String nodeVal : data.split(DELIMITER)) {
            stack.addFirst(nodeVal);
        }
        return this.deserialize(stack);
    }

    private TreeNode deserialize(Deque<String> stack) {
        if (stack.isEmpty()) {
            return null;
        }

        String nodeVal = stack.removeFirst();
        if (NULL.equals(nodeVal)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(nodeVal));
        root.right = this.deserialize(stack);
        root.left = this.deserialize(stack);
        return root;
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