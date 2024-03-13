package com.corvalds.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题简述：输出指定数字范围内可能构建出来的BST树数量<br/>
 * 问题思路：定义一个入参为根节点值、本BST树合法取值范围[min, max]的方法，该方法中会递归地查询出所有潜在的左右子树数量，然后再将潜在左右子树数量相乘得到根节点可能的BST树数量，同时利用相同长度的区间生成的BST树数量相同的特点，利用一个外部变量MAP缓存中间结果，加速整体求解过程<br/>
 * 问题链接：<a href="https://leetcode.com/problems/unique-binary-search-trees/description/">LT-96</a>
 */
public class LT96 {

    /**
     * 结果数量缓存，对于长度相同的区间生成的BST树数量是相同的，使用MAP缓存结果，加速运算过程
     */
    Map<Integer, Integer> resultCacheMap = new HashMap<>();

    public int numTrees(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += this.traverse(i, 1, n);
        }
        return sum;
    }

    // 返回以n为根节点的BST数量，该BST数字范围限定在[min, max]中，同时n >= min且n <= max
    private int traverse(int n, int min, int max) {
        if (min == max) {
            return 1;
        }

        int leftTreeSum = 0;
        int leftDiff = n - min;
        if (resultCacheMap.containsKey(leftDiff)) {
            leftTreeSum = resultCacheMap.get(leftDiff);
        } else {
            int leftIdx = min;
            while (leftIdx < n) {
                leftTreeSum += this.traverse(leftIdx, min, n - 1);
                leftIdx++;
            }
            resultCacheMap.put(leftDiff, leftTreeSum);
        }

        int rightTreeSum = 0;
        int rightDiff = max - n;
        if (resultCacheMap.containsKey(rightDiff)) {
            rightTreeSum = resultCacheMap.get(rightDiff);
        } else {
            int rightIdx = n + 1;
            while (rightIdx <= max) {
                rightTreeSum += this.traverse(rightIdx, n + 1, max);
                rightIdx++;
            }
            resultCacheMap.put(rightDiff, rightTreeSum);
        }

        return (leftTreeSum == 0 ? 1 : leftTreeSum) * (rightTreeSum == 0 ? 1 : rightTreeSum);
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
