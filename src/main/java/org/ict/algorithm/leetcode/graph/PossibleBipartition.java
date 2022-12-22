package org.ict.algorithm.leetcode.graph;

import java.util.*;

/**
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi]
 * indicates that the person labeled ai does not like the person labeled bi,
 * return true if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4] and group2 [2,3].
 * Example 2:
 *
 * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 10^4
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * All the pairs of dislikes are unique.
 * @author sniper
 * @date 21 Dec, 2022
 * LC886
 */
public class PossibleBipartition {

    /**
     * This problem is similar with LC785 {@link IsGraphBipartite}
     * @param args
     */
    public static void main(String[] args) {
        int n = 3;
        int[][] dislikes = {{1, 2}, {1, 3}, {2, 3}};
        PossibleBipartition instance = new PossibleBipartition();
        boolean result = instance.possibleBipartition(n, dislikes);
        System.out.println(result);
    }

    /**
     * Breadth-First-Search Solution
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartitionV3(int n, int[][] dislikes) {
        //todo
        return false;
    }

    /**
     * Time Cost 65ms
     * Union-Find Solution
     * e.g.n = 3, dislikes = [[1,2],[1,3],[2,3]]
     * 1----2, 3
     * 2----1, 3
     * 3----1, 2
     * -------------------------------------------
     * parent:
     * 0 1 2 3
     * 0 1 2 3
     * -------------------------------------------
     * i:1, graph.get(i) = [2, 3]
     * x = find(1) = 1
     * y = find(2) = 2
     * x != y, j = 1, p = graph.get(i).get(j) = 3
     * x != p, parent[3] = 2
     * parent:
     * 0 1 2 3
     * 0 1 2 2
     *
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartitionV2(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = createGraph(dislikes);
        UnionFind uf = new UnionFind(1, n + 1);
        for (int i = 1; i < n + 1; i++) {
            /**
             * Skip the node-i if it has no neighbors
             */
            if (graph.get(i) == null || graph.get(i).size() == 0) {
                continue;
            }
            /**
             * check node-i with its first neighbor
             * if they have the same parent value, the graph cannot be bipartite.
             */
            int x = uf.find(i);
            int y = uf.find(graph.get(i).get(0));
            if (x == y) {
                return false;
            }
            /**
             * check node-i's other neighbors, and union these neighbors parent
             * to node-i's first neighbor.
             */
            for (int j = 1; j < graph.get(i).size(); j++) {
                int p = uf.find(graph.get(i).get(j));
                if (x == p) {
                    return false;
                }
                uf.parent[p] = y;
            }
        }
        return true;
    }

    private Map<Integer, List<Integer>> createGraph(int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : dislikes) {
            if (graph.get(edge[0]) == null) {
                graph.put(edge[0],  new ArrayList<>());
            }
            graph.get(edge[0]).add(edge[1]);
            if (graph.get(edge[1]) == null) {
                graph.put(edge[1],  new ArrayList<>());
            }
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
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
     * Depth-First-Search Solution with Adjacent-Lists Graph
     * Time Cost 11ms
     *  0:not colored
     *  1:color red
     * -1:color black
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartitionV1(int n, int[][] dislikes) {
        /**
         * graph with adjacent-list storage.
         */
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : dislikes) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        /**
         * 1 <= dislikes[i][j] <= n,
         * so we use [1,n], and leave colors[0] not used,
         * this can assure the index consistent with [1, n]
         */
        int[] colors = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (colors[i] == 0 && !dfsV1(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfsV1(List<Integer>[] graph, int[] colors, int color, int node) {
        colors[node] = color;
        /**
         * painted all neighbors with reversed color
         * Notice graph stored as adjacent-list structure.
         */
        for (int neighbor : graph[node]) {
            if (colors[neighbor] == color) {
                return false;
            }
            if (colors[neighbor] == 0 && !dfsV1(graph, colors, -color, neighbor)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Depth-First-Search Solution with Adjacent-Matrix Graph
     * Time Cost 86ms
     *
     *  0:not colored
     *  1:color red
     * -1:color black
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[][] graph = new int[n + 1][n + 1];
        for (int[] edge : dislikes) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }
        /**
         * 1 <= dislikes[i][j] <= n,
         * so we use [1,n], and leave colors[0] not used,
         * this can assure the index consistent with [1, n]
         */
        int[] colors = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (colors[i] == 0 && !dfs(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int color, int node) {
        /**
         * if node has been colored, check its color is same with the color to be painted.
         * This line is not necessary.
         */
        /*if (colors[node] != 0) {
            return colors[node] == color;
        }*/
        /**
         * if node has not been colored, painted it with the assigned color.
         */
        colors[node] = color;
        /**
         * painted all neighbors with reversed color
         * Notice graph stored as adjacent-matrix structure.
         */
        for (int j = 1; j < graph.length; j++) {
            /**
             * Only access the valid element(graph[i][j] == 1) in the graph.
             */
            if (graph[node][j] == 1) {
                if (colors[j] == color) {
                    return false;
                }
                if (colors[j] == 0 && !dfs(graph, colors, -color, j)) {
                    return false;
                }
            }
        }
        return true;
    }



}
