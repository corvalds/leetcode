package com.corvalds.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：检测图中是否存在环<br/>
 * 问题思路：使用DFS算法遍历图，使用一个onPath外部变量记录当前遍历路径，每次遍历时，检查当前节点是否已经在路径中，如果在的话，则存在环，如果遍历完整个图都不存在，则认为不存在环<br/>
 * 问题链接：<a href="https://leetcode.com/problems/course-schedule/description//">LT-207</a>
 */
public class LT207 {

    private boolean canFinish = true;

    private boolean[] onPath;

    private boolean[] visited;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        for (int i = 0; i < numCourses; i++) {
            // 尝试以图中的每个节点为起始点进行遍历，避免存在散落的节点
            traverse(graph, i);
        }
        return canFinish;
    }

    private List<Integer>[] buildGraph(int nodeNum, int[][] allEdges) {
        List<Integer>[] graph = new List[nodeNum];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < allEdges.length; i++) {
            graph[allEdges[i][0]].add(allEdges[i][1]);
        }
        return graph;
    }

    private void traverse(List<Integer>[] graph, int currentNode) {
        if (onPath[currentNode]) {
            canFinish = false;
        }
        if (visited[currentNode] || !canFinish) {
            // 如果已经遍历过或者已经确定存在环了，则不需要再往下执行
            // 这里如果去掉visited[currentNode]条件，将会超时，因为visited[currentNode]会有避免重复遍历的作用，对于已经遍历过确定没有环的节点，重复遍历无意义
            return;
        }

        visited[currentNode] = true;
        onPath[currentNode] = true;
        List<Integer> edges = graph[currentNode];
        for (int i = 0; i < edges.size(); i++) {
            traverse(graph, edges.get(i));
        }
        onPath[currentNode] = false;
    }
}
