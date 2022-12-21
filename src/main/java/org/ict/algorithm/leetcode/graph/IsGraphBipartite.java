package org.ict.algorithm.leetcode.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
 * More formally, for each v in graph[u], there is an undirected edge between node u and node v.
 * The graph has the following properties:
 *
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
 * Example 2:
 *
 *
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 *
 *
 * Constraints:
 *
 * graph.length == n
 * 1 <= n <= 100
 * 0 <= graph[u].length < n
 * 0 <= graph[u][i] <= n - 1
 * graph[u] does not contain u.
 * All the values of graph[u] are unique.
 * If graph[u] contains v, then graph[v] contains u.
 * @author sniper
 * @date 21 Dec, 2022
 * LC785
 */
public class IsGraphBipartite {

    public boolean isBipartiteV4(int[][] graph) {
        return false;
    }

    public boolean isBipartiteV3(int[][] graph) {
        return false;
    }


    public boolean isBipartiteV2(int[][] graph) {
        return false;
    }

    public boolean isBipartiteV1(int[][] graph) {
        return false;
    }

    /**
     * Time Cost 1ms
     * For each node, if it has not been colored(status 0):
     * color its neighbors with a different color,
     * Red(status 1), Black(status -1).
     * Can the vertices of a given graph be assigned one of two colors
     * in such a way that no edge connects vertices with the same color?
     * If we can finish all the nodes coloring in this way, then the graph is bipartite.
     *
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        /**
         *  0:not colored
         *  1:color red
         * -1:color black
         */
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !dfs(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * check graph is bipartite or not
     * @param graph
     * @param colors
     * @param color assigned color for the node
     * @param node
     * @return if graph is bipartite, return true, otherwise return false
     */
    private boolean dfs(int[][] graph, int[] colors, int color, int node) {
        /**
         * if node has been colored, check its color is same with the color to be painted.
         */
        if (colors[node] != 0) {
            return colors[node] == color;
        }
        /**
         * if node has not been colored, painted it with the assigned color.
         */
        colors[node] = color;
        /**
         * painted all neighbors with reversed color
         */
        for (int neighbor: graph[node]) {
            if (!dfs(graph, colors, -color, neighbor)) {
                return false;
            }
        }
        return true;
    }

}
