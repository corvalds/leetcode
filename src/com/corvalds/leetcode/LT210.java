package com.corvalds.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 问题简述：选择合适的上课序列<br/>
 * 问题思路：将课程间的依赖抽象成图，使用DFS算法遍历图的时候做两件事情：判断是否有环，同时构建课程序列，以何种方式遍历图取决于构建图时的定义<br/>
 * 问题链接：<a href="https://leetcode.com/problems/course-schedule-ii/description/">LT-210</a>
 */
public class LT210 {

    private boolean[] visited;

    private boolean[] onPath;

    private List<Integer> order = new LinkedList<>();

    private boolean canFinish = true;

    // DFS版本实现
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        // 构建图
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 使用DFS算法递归地对图进行后序遍历，同时检查其中是否存在环
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }

        int[] resultOrder = new int[0];
        if (canFinish) {
            Collections.reverse(order);
            resultOrder = order.stream().mapToInt(el -> el).toArray();
        }

        return resultOrder;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] allEdges) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < allEdges.length; i++) {
            // 从被依赖方指向依赖方，此时只能使用后序遍历得到正确的序列
            // 如果从依赖方指向被依赖方，此时可以使用前序遍历得到正确的序列
            graph[allEdges[i][1]].add(allEdges[i][0]);
        }
        return graph;
    }

    private void traverse(List<Integer>[] graph, int currentNode) {
        if (onPath[currentNode]) {
            canFinish = false;
        }
        if (visited[currentNode] || !canFinish) {
            return;
        }

        visited[currentNode] = true;
        onPath[currentNode] = true;
        List<Integer> edges = graph[currentNode];
        for (int i = 0; i < edges.size(); i++) {
            traverse(graph, edges.get(i));
        }
        // 遍历完所有被以来节点后，将当前节点加入到结果序列中
        order.add(currentNode);
        onPath[currentNode] = false;
    }
}
