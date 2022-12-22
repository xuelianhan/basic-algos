package org.ict.algorithm.leetcode.graph;


import java.util.ArrayDeque;
import java.util.Deque;

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
 *
 * LC785
 */
public class IsGraphBipartite {

    public boolean isBipartiteV4(int[][] graph) {
        return false;
    }

    public boolean isBipartiteV3(int[][] graph) {
        return false;
    }


    /**
     * Union-Find Solution
     * @param graph
     * @return
     */
    public boolean isBipartiteV2(int[][] graph) {
        int n = graph.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            /**
             * Skip the node-i if it has no neighbors
             */
            if (graph[i] == null || graph[i].length == 0) {
                continue;
            }
            /**
             * check node-i with its first neighbor
             * if they have the same parent value, the graph cannot be bipartite.
             */
            int x = uf.find(i);
            int y = uf.find(graph[i][0]);
            if (x == y) {
                return false;
            }
            /**
             * check node-i's other neighbors, and union these neighbors parent
             * to node-i's first neighbor.
             */
            for (int j = 1; j < graph[i].length; j++) {
                int p = uf.find(graph[i][j]);
                if (x == p) {
                    return false;
                }
                uf.parent[p] = y;
            }
        }
        return true;
    }

    class UnionFind {
        int[] parent;
        private int count;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public UnionFind(int start, int end) {
            count = end - start;
            parent = new int[end];
            for (int i = start; i < end; i++) {
                parent[i] = i;
            }
        }

        public int find (int p) {
            while (p != parent[p]) {
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j) {
                return;
            }
            parent[i] = j;
            count--;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }

    /**
     * Breadth-First-Search Solution
     * Time Cost 1ms
     * @param graph
     * @return
     */
    public boolean isBipartiteV1(int[][] graph) {
        int n = graph.length;
        /**
         *  0:not colored
         *  1:color red
         * -1:color black
         */
        int[] colors = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            /**
             * Skip colored node(This means the node has been visited)
             */
            if (colors[i] != 0) {
                continue;
            }
            /**
             * colored current node-i with 1-red(This operation equals marking node-i to visited status)
             */
            colors[i] = 1;
            queue.offer(i);
            while (!queue.isEmpty()) {
                /**
                 * check current node's neighbors color(whether it has been visited)
                 */
                int cur = queue.poll();
                for (int neighbor : graph[cur]) {
                    /**
                     * if neighbor color is same as node-i's, return false
                     */
                    if (colors[neighbor] == colors[cur]) {
                        return false;
                    }
                    /**
                     * if neighbor has not been colored, colored it with the reverse code.
                     */
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = - colors[cur];
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return true;
    }

    /**
     * Time Cost 1ms
     * Depth-First-Search Solution
     *
     * For each node, if it has not been colored(status 0):
     * color its neighbors with a different color,
     * Red(status 1), Black(status -1).
     * Can the vertices of a given graph be assigned one of two colors
     * in such a way that no edge connects vertices with the same color?
     * If we can finish all the nodes coloring in this way, then the graph is bipartite.
     * The following solution combine visited array and colored array into one array colors.
     * colors[i] == 0 indicates the i-th node has not been accessed.
     * colors[i] == -1 indicates the i-th node has been colored with black.
     * colors[i] == 1 indicates the i-th node has been colored with red.
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
