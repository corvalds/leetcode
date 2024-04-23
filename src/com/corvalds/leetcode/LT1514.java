package com.corvalds.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 问题简述：起始点和目标点最可能路径<br/>
 * 问题思路：使用Dijkstra算法计算两个点间最短距离<br/>
 * 问题链接：<a href="https://leetcode.com/problems/path-with-maximum-probability/description/">LT-1514</a>
 */
public class LT1514 {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // 将入参转换成无向加权图
        List<Edge>[] graph = buildGraph(n, edges, succProb);
        // 使用Dijkstra算法计算起始点至目标点的最短距离
        return runDijkstraAlgorithm(graph, start_node, end_node);
    }

    private List<Edge>[] buildGraph(int n, int[][] edges, double[] succProb) {
        List<Edge>[] graph = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];

            graph[from].add(new Edge(from, to, weight));
            graph[to].add(new Edge(to, from, weight));
        }

        return graph;
    }

    private double runDijkstraAlgorithm(List<Edge>[] graph, int startNode, int targetNode) {
        double[] maxProbsArray = new double[graph.length];

        Queue<State> queue = new PriorityQueue<>((e1, e2) -> Double.compare(e2.probToStartNode, e1.probToStartNode));

        maxProbsArray[startNode] = 1;
        queue.add(new State(startNode, 1));
        while (!queue.isEmpty()) {
            State state = queue.poll();
            int currentNode = state.node;
            double currentNodeProbToStartNode = state.probToStartNode;

            if (currentNode == targetNode) {
                return currentNodeProbToStartNode;
            }
            if (currentNodeProbToStartNode < maxProbsArray[currentNode]) {
                continue;
            }
            maxProbsArray[currentNode] = currentNodeProbToStartNode;
            for (Edge edge : graph[currentNode]) {
                double nextNodeProbToStartNode = currentNodeProbToStartNode * edge.weight;
                if (nextNodeProbToStartNode > maxProbsArray[edge.to]) {
                    queue.add(new State(edge.to, nextNodeProbToStartNode));
                }
            }
        }

        return 0.0;
    }

    public static class Edge {
        public int from;
        public int to;
        public double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static class State {
        public int node;
        public double probToStartNode;

        public State(int node, double probToStartNode) {
            this.node = node;
            this.probToStartNode = probToStartNode;
        }
    }
}
