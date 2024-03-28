package com.corvalds.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 问题简述：是否二分图<br/>
 * 问题思路：使用DFS算法或BFS算法遍历图，遍历时判断当前遍历节点和邻接节点的颜色是否相同<br/>
 * 问题链接：<a href="https://leetcode.com/problems/is-graph-bipartite/description/">LT-785</a>
 */
public class LT785 {

    private boolean[] visited;

    private boolean[] color;

    public boolean isBipartite(int[][] graph) {
        visited = new boolean[graph.length];
        color = new boolean[graph.length];
        boolean isBipartite = true;

        // 使用BFS算法遍历图，遍历时检查邻接节点的颜色是否和当前节点不一致
        // 这里需要注意无向图和有向图实现BFS上的异同点：
        // 相同点：都是基于队列实现，每次遍历当前节点时，都会将当前节点的邻接节点放入队尾中，每次都是从队头取出当前遍历节点
        // 不同点：有向图可以根据图的入度信息进行队列初始化的辅助，而无向图无法根据入度进行队列初始化，所以对于无向图来说，只能使用一个外部循环，将所有未在之前被遍历过的节点都尝试进行BFS算法遍历
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < graph.length; i++) {
            if (!isBipartite) {
                break;
            }
            if (visited[i]) {
                continue;
            }
            if (graph[i].length == 0) {
                // 独立的节点不影响结果
                continue;
            }
            queue.add(i);

            while (!queue.isEmpty()) {
                int currentNode = queue.removeFirst();
                visited[currentNode] = true;
                for (int j = 0; j < graph[currentNode].length; j++) {
                    int adjacentNode = graph[currentNode][j];
                    if (visited[adjacentNode]) {
                        if (color[adjacentNode] == color[currentNode]) {
                            isBipartite = false;
                            break;
                        }
                    } else {
                        color[adjacentNode] = !color[currentNode];
                        queue.add(adjacentNode);
                    }
                }

                if (!isBipartite) {
                    break;
                }
            }
        }

        return isBipartite;
    }
}
