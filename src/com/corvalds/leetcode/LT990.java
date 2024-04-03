package com.corvalds.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：判断表达式集合是否有效<br/>
 * 问题思路：基于并查集解题，将等式视为连通两个元素，将不等式视为两个元素不连通，先基于所有等式构建并查集，然后基于所有不等式检查并查集中不等式涉及的两个变量是否连通，如果存在连通的情况，则意味着该表达式集合不有效<br/>
 * 问题链接：<a href="https://leetcode.com/problems/satisfiability-of-equality-equations/description/">LT-990</a>
 */
public class LT990 {

    public boolean equationsPossible(String[] equations) {
        // 构建一个并查集，利用Union-Find算法解题
        DisjointSet disjointSet = new DisjointSet(26);
        List<String> equationList = new LinkedList<>();
        List<String> notEquationList = new LinkedList<>();
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                equationList.add(equation);
            } else {
                notEquationList.add(equation);
            }
        }
        // 将等式两边的变量视为进行Union操作
        for (String equation : equationList) {
            char c1 = equation.charAt(0);
            char c2 = equation.charAt(3);
            disjointSet.union(c1 - 'a', c2 - 'a');
        }
        // 当出现不等式的时候，使用Find操作检查两个变量是否连通，如果存在一个不等式中两个变量已经处于连通状态，则意味着不存在整数能满足所有表达式
        for (String notEquation : notEquationList) {
            char c1 = notEquation.charAt(0);
            char c2 = notEquation.charAt(3);
            if (disjointSet.find(c1 - 'a', c2 - 'a')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 构建并查集
     */
    public static class DisjointSet {
        // 当前节点所属隶属的上级节点
        private int[] parent;

        public DisjointSet(int volumn) {
            this.parent = new int[volumn];
            for (int i = 0; i < volumn; i++) {
                this.parent[i] = i;
            }
        }

        public void union(int a, int b) {
            int aRoot = this.findRoot(a);
            int bRoot = this.findRoot(b);
            if (aRoot == bRoot) {
                return;
            }

            this.parent[aRoot] = bRoot;
        }

        public boolean find(int a, int b) {
            return this.findRoot(a) == this.findRoot(b);
        }

        private int findRoot(int a) {
            // 查找的时候顺带压缩树
            if (this.parent[a] != a) {
                this.parent[a] = this.findRoot(this.parent[a]);
            }
            return this.parent[a];
        }
    }
}
