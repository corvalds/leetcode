package com.corvalds.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：判断二分图<br/>
 * 问题思路：采用DFS算法遍历图，过程中判断当前遍历节点和邻接节点的颜色，如果存在当前遍历节点和邻接节点颜色不一致则认为不是二分图<br/>
 * 问题链接：<a href="https://leetcode.com/problems/possible-bipartition/description/">LT-886</a>
 */
public class LT886 {

    private boolean[] visited;

    private boolean[] color;

    private boolean isBipartition;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 判断二分图
        this.visited = new boolean[n];
        this.color = new boolean[n];
        this.isBipartition = true;
        // 先构造图，然后使用DFS算法遍历图，遍历的过程中判断邻接节点是否和本节点的颜色相同，存在两个邻接节点是相同的颜色则认为这个不是一个二分图
        List<Integer>[] graph = buildGraph(n, dislikes);
        for (int i = 0; i < n; i++) {
            traverse(graph, i);
        }
        return this.isBipartition;
    }

    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0] - 1].add(edge[1] - 1);
            graph[edge[1] - 1].add(edge[0] - 1);
        }

        return graph;
    }

    private void traverse(List<Integer>[] graph, int currentNode) {
        if (!this.isBipartition || visited[currentNode]) {
            return;
        }

        visited[currentNode] = true;
        for (int nextNode : graph[currentNode]) {
            if (visited[nextNode]) {
                if (color[nextNode] == color[currentNode]) {
                    this.isBipartition = false;
                    return;
                }
            } else {
                color[nextNode] = !color[currentNode];
                traverse(graph, nextNode);
            }
        }
    }
}
