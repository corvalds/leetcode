package com.corvalds.leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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

//    Prim算法版本
//    private boolean[] visited;
//
//    private PriorityQueue<int[]> queue;
//
//    public int minCostConnectPoints(int[][] points) {
//        int[][] graph = new int[points.length][points.length];
//        visited = new boolean[points.length];
//        // 使用Prim算法计算最小生成树
//        // 构建图
//        for (int i = 0; i < points.length; i++) {
//            // 当前节点与自身的权重为0
//            graph[i][i] = 0;
//            for (int j = i + 1; j < points.length; j++) {
//                graph[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
//                graph[j][i] = graph[i][j];
//            }
//        }
//
//        queue = new PriorityQueue<>(Comparator.comparing(el -> el[2]));
//        // 切分节点0
//        cut(graph, 0);
//        int sum = 0;
//        while (!queue.isEmpty()) {
//            int[] tuple = queue.poll();
//            int from = tuple[0];
//            int to = tuple[1];
//            int weight = tuple[2];
//
//            if (visited[to]) {
//                continue;
//            }
//
//            sum += weight;
//            cut(graph, to);
//        }
//        return sum;
//    }
//
//    private void cut(int[][] graph, int node) {
//        visited[node] = true;
//        for (int i = 0; i < graph.length; i++) {
//            if (node == i || visited[i]) {
//                continue;
//            }
//            // 将当前节点所有未入队的边都进行入队
//            queue.add(new int[]{node, i, graph[node][i]});
//        }
//    }
}
