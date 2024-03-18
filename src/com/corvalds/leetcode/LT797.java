package com.corvalds.leetcode;

import java.util.*;

/**
 * 问题简述：所有可能路径<br/>
 * 问题思路：使用DFS算法遍历图，期间使用一个外部变量记录当前遍历的路径，每当到达目的地，则快照一份当前路径作为答案<br/>
 * 问题链接：<a href="https://leetcode.com/problems/all-paths-from-source-to-target/description/">LT-797</a>
 */
public class LT797 {

    private List<Integer> currentPath;

    private List<List<Integer>> resultList;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        currentPath = new LinkedList<>();
        resultList = new LinkedList<>();
        traverse(graph, 0, graph.length - 1);
        return resultList;
    }

    /**
     * 通过DFS算法遍历图
     */
    private void traverse(int[][] graph, int currentNode, int targetNode) {
        currentPath.add(currentNode);
        if (currentNode == targetNode) {
            resultList.add(new ArrayList<>(currentPath));
        }

        int[] nextNodes = graph[currentNode];
        for (int i = 0; i < nextNodes.length; i++) {
            traverse(graph, nextNodes[i], targetNode);
        }

        currentPath.remove(currentPath.size() - 1);
    }
}
