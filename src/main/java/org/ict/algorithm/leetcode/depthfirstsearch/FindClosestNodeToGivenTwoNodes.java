package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
 *
 * The graph is represented with a given 0-indexed array edges of size n,
 * indicating that there is a directed edge from node i to node edges[i].
 * If there is no outgoing edge from i, then edges[i] == -1.
 *
 * You are also given two integers node1 and node2.
 *
 * Return the index of the node that can be reached from both node1 and node2,
 * such that the maximum between the distance from node1 to that node, and from node2 to that node is minimized.
 * If there are multiple answers,
 * return the node with the smallest index,
 * and if no possible answer exists, return -1.
 *
 * Note that edges may contain cycles.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
 * Output: 2
 * Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
 * The maximum of those two distances is 1.
 * It can be proven that we cannot get a node with a smaller maximum distance than 1, so we return node 2.
 * Example 2:
 *
 *
 * Input: edges = [1,2,-1], node1 = 0, node2 = 2
 * Output: 2
 * Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
 * The maximum of those two distances is 2.
 * It can be proven that we cannot get a node with a smaller maximum distance than 2, so we return node 2.
 *
 *
 * Constraints:
 *
 * n == edges.length
 * 2 <= n <= 10^5
 * -1 <= edges[i] < n
 * edges[i] != i
 * 0 <= node1, node2 < n
 * @author sniper
 * @date 25 Jan, 2023
 * LC2359, Medium
 */
public class FindClosestNodeToGivenTwoNodes {

    public int closestMeetingNodeV1(int[] edges, int node1, int node2) {
        return 0;
    }

    /**
     * e.g. edges = [1,2,-1], node1 = 0, node2 = 2
     * 0--->1--->2
     * dist1:[0, MAX, MAX]
     * dist2:[MAX, MAX, 0]
     *
     *
     * Each node has at most one outgoing edge.
     * So we can use Depth-First-Search to calculate length of path to node1 and node2.
     * Note that edges may contain cycles, so we need an array to mark the nodes have been visited.
     * @param edges
     * @param node1
     * @param node2
     * @return
     */
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        /**
         * dist1[i] means the shortest distance to node-i from node1
         * dist2[i] means the shortest distance to node-i from node2
         */
        int[] dist1 = new int[n];
        int[] dist2 = new int[n];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);
        /**
         * distance to node1 from node1 is zero, similar to node2
         */
        dist1[node1] = 0;
        dist2[node2] = 0;
        /**
         * A visited array to mark node-i visited or not,
         * At here, we need to visit graph two times, so we use two arrays.
         */
        boolean[] visited1 = new boolean[n];
        boolean[] visited2 = new boolean[n];
        dfs(node1, dist1, visited1, edges);
        dfs(node2, dist2, visited2, edges);

        int res = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (Math.max(dist1[i], dist2[i]) < min) {
                min = Math.max(dist1[i], dist2[i]);
                res = i;
            }
        }
        return res;
    }

    private void dfs(int u, int[] dist, boolean[] visited, int[] edges) {
        /**
         * edge: from u to v
         * u --> v
         */
        visited[u] = true;
        int v = edges[u];
        if (v != -1 && !visited[v]) {
            /**
             * Distance to v from u add one simply.
             */
            dist[v] = dist[u] + 1;
            dfs(v, dist, visited, edges);
        }
    }
}
