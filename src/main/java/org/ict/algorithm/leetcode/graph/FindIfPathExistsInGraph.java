package org.ict.algorithm.leetcode.graph;

import java.util.Arrays;

/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 * The edges in the graph are represented as a 2D integer array edges,
 * where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
 * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 *
 * Given edges and the integers n, source, and destination,
 * return true if there is a valid path from source to destination, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * Example 2:
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 10^5
 * 0 <= edges.length <= 2 * 10^5
 * edges[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 * @author sniper
 * @date 19 Dec, 2022
 * LC1971
 */
public class FindIfPathExistsInGraph {

    public static void main(String[] args) {
        int n = 3;
        int[][] edges = {{0,1},{1,2},{2,0}};
        int source = 0;
        int destination = 2;
        FindIfPathExistsInGraph instance = new FindIfPathExistsInGraph();
        boolean result = instance.validPath(n, edges, source, destination);
        System.out.println(result);
    }

    public boolean validPathV2(int n, int[][] edges, int source, int destination) {
        return false;
    }

    public boolean validPathV1(int n, int[][] edges, int source, int destination) {
        return false;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int rows = edges.length;
        UnionFind uf = new UnionFind(n);
        System.out.println(Arrays.toString(uf.parent));
        for (int i = 0; i < rows; i++) {
            int[] arr = edges[i];
            int p = arr[0];
            int q = arr[1];
            if (uf.find(p) == uf.find(q)) {
                continue;
            }
            uf.union(p, q);
        }
        System.out.println(Arrays.toString(uf.parent));
        return uf.connected(source, destination);
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
