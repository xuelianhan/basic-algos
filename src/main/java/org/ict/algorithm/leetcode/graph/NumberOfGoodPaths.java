package org.ict.algorithm.leetcode.graph;

import java.util.*;

/**
 * There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.
 *
 * You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node.
 * You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
 *
 * A good path is a simple path that satisfies the following conditions:
 *
 * The starting node and the ending node have the same value.
 * All nodes between the starting node and the ending node have values less than or equal to the starting node
 * (i.e. the starting node's value should be the maximum value along the path).
 * Return the number of distinct good paths.
 *
 * Note that a path and its reverse are counted as the same path.
 * For example, 0 -> 1 is considered to be the same as 1 -> 0.
 * A single node is also considered as a valid path.
 *
 *
 *
 * Example 1:
 * Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
 * Output: 6
 * Explanation: There are 5 good paths consisting of a single node.
 * There is 1 additional good path: 1 -> 0 -> 2 -> 4.
 * (The reverse path 4 -> 2 -> 0 -> 1 is treated as the same as 1 -> 0 -> 2 -> 4.)
 * Note that 0 -> 2 -> 3 is not a good path because vals[2] > vals[0].
 *
 * Example 2:
 * Input: vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
 * Output: 7
 * Explanation: There are 5 good paths consisting of a single node.
 * There are 2 additional good paths: 0 -> 1 and 2 -> 3.
 *
 * Example 3:
 * Input: vals = [1], edges = []
 * Output: 1
 * Explanation: The tree consists of only one node, so there is one good path.
 *
 * Constraints:
 * n == vals.length
 * 1 <= n <= 3 * 10^4
 * 0 <= vals[i] <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 *
 * @author sniper
 * @date 16 Jan, 2023
 * LC2421, Hard
 */
public class NumberOfGoodPaths {

    public int numberOfGoodPathsV2(int[] vals, int[][] edges) {
        int res = 0;
        //todo
        return res;
    }

    public int numberOfGoodPathsV1(int[] vals, int[][] edges) {
        int res = 0;
        //todo
        return res;
    }

    /**
     * Union-Find Solution
     * Time Cost 129ms
     * todo
     * Analysis here
     * todo
     * @param vals
     * @param edges
     * @return
     */
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        final int n = vals.length;
        int ans = n;
        UnionFind uf = new UnionFind(n);
        List<Integer>[] graph = new List[n];
        Map<Integer, List<Integer>> valToNodes = new TreeMap<>();

        for (int i = 0; i < n; ++i)
            graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];
            if (vals[v] <= vals[u]) {
                graph[u].add(v);
            }

            if (vals[u] <= vals[v]) {
                graph[v].add(u);
            }
        }

        for (int i = 0; i < vals.length; ++i) {
            valToNodes.putIfAbsent(vals[i], new ArrayList<>());
            valToNodes.get(vals[i]).add(i);
        }

        for (Map.Entry<Integer, List<Integer>> entry : valToNodes.entrySet()) {
            List<Integer> nodes = entry.getValue();
            for (final int u : nodes)
                for (final int v : graph[u])
                    uf.unionByRank(u, v);
            Map<Integer, Integer> rootCount = new HashMap<>();
            for (final int u : nodes)
                rootCount.merge(uf.find(u), 1, Integer::sum);
            // For each group, C(count, 2) := count * (count - 1) / 2
            for (final int count : rootCount.values())
                ans += count * (count - 1) / 2;
        }

        return ans;
    }

    class UnionFind {

        public UnionFind(int n) {
            id = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; ++i)
                id[i] = i;
        }

        public void unionByRank(int u, int v) {
            final int i = find(u);
            final int j = find(v);
            if (i == j)
                return;
            if (rank[i] < rank[j]) {
                id[i] = id[j];
            } else if (rank[i] > rank[j]) {
                id[j] = id[i];
            } else {
                id[i] = id[j];
                ++rank[j];
            }
        }

        public int find(int u) {
            return id[u] == u ? u : (id[u] = find(id[u]));
        }

        private int[] id;
        private int[] rank;

    }
}
