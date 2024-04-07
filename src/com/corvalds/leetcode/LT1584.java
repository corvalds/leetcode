package com.corvalds.leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：最小生成树<br/>
 * 问题思路：Kruskal算法或Prim算法<br/>
 * 问题链接：<a href="https://leetcode.com/problems/min-cost-to-connect-all-points/description/">LT-1584</a>
 */
public class LT1584 {

    public int minCostConnectPoints(int[][] points) {
        // 构造并查集然后使用Kruskal算法计算最小生成树
        // 先生成所有可能的边，每个元素代表一个边，其中{起始点、结束点、边权重}
        List<int[]> edgeList = new LinkedList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                edgeList.add(new int[]{i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])});
            }
        }
        // 按权重排序所有边
        edgeList.sort(Comparator.comparingInt((int[] a) -> a[2]));
        // 构造并查集
        DisjointSet ds = new DisjointSet(points.length);

        // 将最小权重的边累加
        int sum = 0;
        for (int[] edge : edgeList) {
            if (ds.find(edge[0], edge[1])) {
                continue;
            }

            sum += edge[2];
            ds.union(edge[0], edge[1]);
        }
        return sum;
    }

    public static class DisjointSet {
        private int[] parent;

        public DisjointSet(int volumn) {
            parent = new int[volumn];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public void union(int a, int b) {
            int aRoot = findRoot(a);
            int bRoot = findRoot(b);

            if (aRoot == bRoot) {
                return;
            }

            parent[aRoot] = bRoot;
        }

        public boolean find(int a, int b) {
            int aRoot = findRoot(a);
            int bRoot = findRoot(b);

            return aRoot == bRoot;
        }

        private int findRoot(int a) {
            if (parent[a] != a) {
                parent[a] = findRoot(parent[a]);
            }
            return parent[a];
        }
    }
}
