package org.ict.algorithm.leetcode.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

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
        /*int n = 3;
        int[][] edges = {{0,1},{1,2},{2,0}};
        int source = 0;
        int destination = 2;*/

        int n = 1;
        int[][] edges = {{}};
        int source = 0;
        int destination = 0;

        FindIfPathExistsInGraph instance = new FindIfPathExistsInGraph();
        boolean result = instance.validPathV1(n, edges, source, destination);
        System.out.println(result);
    }


    /**
     * Union-Find Solution
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @return
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int rows = edges.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < rows; i++) {
            int[] arr = edges[i];
            int p = arr[0];
            int q = arr[1];
            if (uf.find(p) == uf.find(q)) {
                continue;
            }
            uf.union(p, q);
        }
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

    /**
     * Depth-First-Search Solution
     * Time Cost 149ms
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @return
     */
    public boolean validPathV2(int n, int[][] edges, int source, int destination) {
        boolean[] visited = new boolean[n];
        Set<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        if (graph[source].contains(destination)) {
            return true;
        }
        return dfsV2(graph, visited, source, destination);
    }

    private boolean dfsV2(Set<Integer>[] graph, boolean[] visited,  int source, int destination) {
        if (source == destination) {
            return true;
        }
        if (visited[source]) {
            return false;
        }
        visited[source] = true;
        for (int neighbor : graph[source]) {
            /**
             * Notice here, we cannot replace the following codes like this:
             * return dfsV2(graph, visited, neighbor, destination);
             * because we use dfs, one node return false, this cannot indicate the
             * whole result is false, since we have other nodes not visited.
             * however, if one node return true, this indicates there must exist one path from source to destination.
             */
            if (dfsV2(graph, visited, neighbor, destination)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Depth-First-Search Solution
     * Time Cost 247ms
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @return
     */
    public boolean validPathV1(int n, int[][] edges, int source, int destination) {
        boolean[] visited = new boolean[n];
        Set<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        if (graph[source].contains(destination)) {
            return true;
        }

        boolean[] exist = new boolean[1];
        dfsV1(graph, visited, exist, source, destination);
        return exist[0];
    }

    private void dfsV1(Set<Integer>[] graph, boolean[] visited, boolean[] exist, int source, int destination) {
        if (!visited[source] && !exist[0]) {
            if (source == destination) {
                exist[0] = true;
                return;
            }
            visited[source] = true;
            for (int neighbor : graph[source]) {
                dfsV1(graph, visited, exist, neighbor, destination);
            }
        }
    }

    /**
     * Understanding the following Solution
     *
     * Breadth-First-Search Solution
     * Time Cost 285ms
     *
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @return
     */
    public boolean validPathV3(int n, int[][] edges, int source, int destination) {
        boolean[] visited = new boolean[n];
        Set<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        if (graph[source].contains(destination)) {
            return true;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == destination) {
                return true;
            }
            for (int neighbor : graph[cur]) {
                if (visited[neighbor]) {
                    continue;
                }
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }
        return false;
    }
}
