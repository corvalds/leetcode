package com.corvalds.leetcode;

import java.util.*;

/**
 * 问题简述：网络延迟时间<br/>
 * 问题思路：典型的最短路径问题，使用Dijkstra算法计算出起始点到所有点的最短路径后，取最大的一个最短路径<br/>
 * 问题链接：<a href="https://leetcode.com/problems/network-delay-time/description/">LT-743</a>
 */
public class LT743 {

    public int networkDelayTime(int[][] times, int n, int k) {
        List<Edge>[] graph = buildGraph(times, n);
        int[] minTimeFromStartNode = runDijkstraAlgorithm(graph, k - 1);
        int maxTime = 0;
        for (int i : minTimeFromStartNode) {
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            maxTime = Math.max(maxTime, i);
        }
        return maxTime;
    }

    private List<Edge>[] buildGraph(int[][] times, int n) {
        List<Edge>[] graph = new List[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : times) {
            int from = edge[0] - 1;
            int to = edge[1] - 1;
            graph[from].add(new Edge(from, to, edge[2]));
        }

        return graph;
    }

    private int[] runDijkstraAlgorithm(List<Edge>[] graph, int startNode) {
        int[] minTimeFromStartNode = new int[graph.length];
        Arrays.fill(minTimeFromStartNode, Integer.MAX_VALUE);

        Queue<State> queue = new PriorityQueue<>(Comparator.comparing(el -> el.timePassedFromStartNode));
        queue.add(new State(startNode, 0));

        while(!queue.isEmpty()) {
            State state = queue.poll();
            int currentNode = state.node;
            int currentTimeCost = state.timePassedFromStartNode;

            if (currentTimeCost > minTimeFromStartNode[currentNode]) {
                continue;
            }
            minTimeFromStartNode[currentNode] = currentTimeCost;
            for (Edge edge : graph[currentNode]) {
                int nextNode = edge.to;
                int addtionalTimeCost = edge.time;
                if (minTimeFromStartNode[currentNode] + addtionalTimeCost < minTimeFromStartNode[nextNode]) {
                    queue.add(new State(nextNode, minTimeFromStartNode[currentNode] + addtionalTimeCost));
                }
            }
        }

        return minTimeFromStartNode;
    }

    public static class Edge {
        public int from;
        public int to;
        public int time;
        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    public static class State {
        public int node;
        public int timePassedFromStartNode;
        public State(int node, int timePassedFromStartNode) {
            this.node = node;
            this.timePassedFromStartNode = timePassedFromStartNode;
        }
    }
}
