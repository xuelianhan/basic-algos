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

    public static void main(String[] args) {
        int n = 3;
        int[][] dislikes = {{1, 2}, {1, 3}, {2, 3}};
        PossibleBipartition instance = new PossibleBipartition();
        boolean result = instance.possibleBipartition(n, dislikes);
        System.out.println(result);
    }

    public boolean possibleBipartitionV2(int n, int[][] dislikes) {

        return false;
    }

    public boolean possibleBipartitionV1(int n, int[][] dislikes) {

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
    public boolean possibleBipartition(int n, int[][] dislikes) {
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
}
