package org.ict.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author sniper
 * @date 10 Apr, 2023
 */
public class TopologicalSort {

    /**
     * Kahn Algorithm
     * @param n
     * @param edges
     */
    public int[] sort(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[0] == 0) {
                queue.add(i);
            }
        }
        /**
         * Collect topological-order into order
         */
        List<Integer> order = new LinkedList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            order.add(cur);
            for (int neighbor : graph[cur]) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (order.size() == n) {
            return order.stream().mapToInt(i -> i).toArray();
        } else {
            /**
             * No topological-order exist.
             */
            return new int[0];
        }
    }
}
