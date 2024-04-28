package com.corvalds.leetcode;

import java.util.*;

/**
 * 问题简述：最少付出路径<br/>
 * 问题思路：将每个节点抽象为图节点，节点之差的绝对值作为两个节点间的边权重，每个节点至起始节点的route effort随着节点的增加而增加（或不变），满足使用Dijkstra算法的使用条件，本题目中route effort的定义和典型边权重不一样，在判断入队时需进行一些调整<br/>
 * 问题链接：<a href="https://leetcode.com/problems/path-with-minimum-effort/description/">LT-1631</a>
 */
public class LT1631 {

    public int minimumEffortPath(int[][] heights) {
        // 将入参抽象成有向加权图：每个点都指向其上、下、左、右四个邻接点，加权值为两个点差值的绝对值
        List<Edge>[] graph = buildGraph(heights);
        // 使用Dijkstra算法计算起始点和终点间的最短路径
        return runDijkstraAlgorithm(graph, 0, heights.length * heights[0].length - 1);
    }

    private List<Edge>[] buildGraph(int[][] heights) {
        List<Edge>[] graph = new List[heights.length * heights[0].length];

        int columnNum = heights[0].length;
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                int from = i * columnNum + j;
                graph[from] = new LinkedList<>();

                if (j - 1 >= 0) {
                    graph[from].add(new Edge(from, i * columnNum + (j - 1), Math.abs(heights[i][j] - heights[i][j - 1])));
                }
                if (j + 1 < columnNum) {
                    graph[from].add(new Edge(from, i * columnNum + (j + 1), Math.abs(heights[i][j] - heights[i][j + 1])));
                }
                if (i - 1 >= 0) {
                    graph[from].add(new Edge(from, (i - 1) * columnNum + j, Math.abs(heights[i][j] - heights[i - 1][j])));
                }
                if (i + 1 < heights.length) {
                    graph[from].add(new Edge(from, (i + 1) * columnNum + j, Math.abs(heights[i][j] - heights[i + 1][j])));
                }
            }
        }

        return graph;
    }

    private int runDijkstraAlgorithm(List<Edge>[] graph, int startNode, int targetNode) {
        int[] minRouteEffortFromStartNodeMap = new int[graph.length];
        Arrays.fill(minRouteEffortFromStartNodeMap, Integer.MAX_VALUE);

        Queue<State> queue = new PriorityQueue<>(Comparator.comparing(el -> el.maxRouteEffort));
        queue.add(new State(startNode, 0));

        while (!queue.isEmpty()) {
            State state = queue.poll();
            int currentNode = state.node;
            int currentPathRouteEffort = state.maxRouteEffort;

            if (state.node == targetNode) {
                return currentPathRouteEffort;
            }
            if (currentPathRouteEffort >= minRouteEffortFromStartNodeMap[currentNode]) {
                // 已经找到一条route effort更小的路径，忽略该节点
                continue;
            }
            minRouteEffortFromStartNodeMap[currentNode] = currentPathRouteEffort;

            for (Edge edge : graph[currentNode]) {
                int nextNode = edge.to;
                int currentDiff = edge.weight;
                int nextNodePresentMinRouteEffort = minRouteEffortFromStartNodeMap[nextNode];
                if (nextNodePresentMinRouteEffort > Math.max(currentDiff, minRouteEffortFromStartNodeMap[currentNode])) {
                    queue.add(new State(nextNode, Math.max(currentDiff, minRouteEffortFromStartNodeMap[currentNode])));
                }
            }
        }

        return minRouteEffortFromStartNodeMap[targetNode];
    }

    public static class Edge {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static class State {
        public int node;
        public int maxRouteEffort;

        public State(int node, int maxRouteEffort) {
            this.node = node;
            this.maxRouteEffort = maxRouteEffort;
        }
    }
}
