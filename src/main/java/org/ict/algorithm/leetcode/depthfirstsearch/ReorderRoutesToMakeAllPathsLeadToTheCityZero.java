package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are n cities numbered from 0 to n - 1 and n - 1 roads
 * such that there is only one way to travel between two different cities (this network form a tree).
 * Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 * This year, there will be a big event in the capital (city 0),
 * and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0.
 * Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 *
 *
 *
 * Example 1:
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 *
 * Example 2:
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 *
 * Example 3:
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 *
 * Constraints:
 * 2 <= n <= 5 * 10^4
 * connections.length == n - 1
 * connections[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * @author sniper
 * @date 20 Jun 2023
 * LC1466, Medium, frequency=9
 */
public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    /**
     * Breadth-First-Search Solution
     * @param n
     * @param connections
     * @return
     */
    public int minReorderV2(int n, int[][] connections) {
        return 0;
    }

    /**
     * Understanding the following solution
     * Depth-First-Search Solution
     * ------------------------------
     * class Solution {
     * public:
     *     int minReorder(int n, vector<vector<int>>& connections) {
     *        unordered_map<int, vector<pair<int, bool>>> graph;
     *        for (auto& e: connections) {
     *            int u = e[0];
     *            int v = e[1];
     *            graph[u].push_back({v, true});
     *            graph[v].push_back({u, false});
     *         }
     *         vector<bool> visited(n);
     *         return dfs(0, graph, visited);
     *     }
     *
     * private:
     *     int dfs(int u, unordered_map<int, vector<pair<int, bool>>>& graph, vector<bool>& visited) {
     *         visited[u] = true;
     *         int res = 0;
     *         for (auto& p: graph[u]) {
     *             int v = p.first;
     *             bool toV = p.second;
     *             if (!visited[v]) {
     *                 if (toV) {
     *                     res++;
     *                 }
     *                 res += dfs(v, graph, visited);
     *             }
     *         }
     *         return res;
     *     }
     * };
     * ------------------------------------
     * class Solution:
     *     def minReorder(self, n: int, connections: List[List[int]]) -> int:
     *         def dfs(u):
     *             visited[u] = True
     *             res = 0
     *             for v in graph[u]:
     *                 if not visited[v]:
     *                     if (u, v) in pair_set:
     *                         res += 1
     *                     res += dfs(v)
     *             return res
     *
     *         graph = defaultdict(list)
     *         pair_set = set()
     *         for a, b in connections:
     *             graph[a].append(b)
     *             graph[b].append(a)
     *             pair_set.add((a, b))
     *         visited = [False] * n
     *         return dfs(0)
     * @param n
     * @param connections
     * @return
     */
    public int minReorderV1(int n, int[][] connections) {
        Map<Integer, List<Pair>> graph = new HashMap<>();
        boolean[] visited = new boolean[n];

        for (int[] con : connections) {
            int u = con[0];
            int v = con[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair(v, true));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair(u, false));
        }

        return dfsV1(0, graph, visited);
    }

    private int dfsV1(int u, Map<Integer, List<Pair>> graph, boolean[] visited) {
        visited[u] = true;
        int res = 0;
        for (Pair pair : graph.getOrDefault(u, new ArrayList<>())) {
            int v = pair.cur;
            boolean toV = pair.toCur;
            if (visited[v]) {
                continue;
            }
            if (toV) {
                res++;
            }
            res += dfsV1(v, graph, visited);
        }
        return res;
    }

    static class Pair {
        private Integer cur;
        private Boolean toCur;

        public Pair(Integer cur, Boolean toCur) {
            this.cur = cur;
            this.toCur = toCur;
        }
    }

    /**
     * Understanding the following solution
     *
     * Depth-First-Search Solution
     * Time Complexity O(n). We visit each node once.
     * Space Complexity O(n). We store n nodes in the adjacency list, with n - 1 edges in total.
     * -------------------------------
     * Treat the graph as undirected.
     * Start a dfs from the root, if you come across an edge in the forward direction,
     * you need to reverse the edge.
     * -------------------------------
     * class Solution {
     * public:
     *     int minReorder(int n, vector<vector<int>>& connections) {
     *         vector<vector<int>> graph(n);
     *
     *         for (const vector<int>& con : connections) {
     *             graph[con[0]].push_back(con[1]);
     *             graph[con[1]].push_back(-con[0]);
     *         }
     *
     *         return dfs(graph, 0, -1);
     *     }
     *
     * private:
     *     int dfs(const vector<vector<int>>& graph, int u, int parent) {
     *         int change = 0;
     *         for (const int v : graph[u]) {
     *             if (abs(v) == parent) {
     *                 continue;
     *             }
     *             if (v > 0) {
     *                 change++;
     *             }
     *             change += dfs(graph, abs(v), u);
     *         }
     *         return change;
     *     }
     * };
     * -------------------------------
     * @author votrubac
     * @see <a href="https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/solutions/661672/c-java-track-direction"></a>
     * @param n
     * @param connections
     * @return
     */
    public int minReorder(int n, int[][] connections) {
        List<Integer>[] graph = new List[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] con : connections) {
            graph[con[0]].add(con[1]);
            graph[con[1]].add(-con[0]);
        }
        /**
         * There is only one way to travel between two different cities (this network form a tree)
         * No need for visited array, graph is a tree.
         */
        return dfs(graph, 0, -1);
    }

    /**
     * In the code below, I am using the adjacency list, and the sign indicates the direction.
     * If the index is positive - the direction is from a parent to a child and we need to change it
     * -------------------------------
     *     1--->3<---2
     *     ^
     *     |
     *     0<---4--->5
     * e.g. n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
     * [0]--> [1, -4]
     * [1]--> [3, 0]
     * [2]--> [3]
     * [3]--> [-1, -2]
     * [4]--> [0, 5]
     * [5]--> [-4]
     * -----------
     *
     * dfs(g, 0, -1), parent:-1
     *    v:1, abs(v) != -1, v>0, change++ --> change:1
     *    change += dfs(g, 1, 0), parent:0
     *              v:3, abs(v) != parent:0, v>0, change++ --> change:1
     *              change += dfs(g, 3, 1), parent:1
     *                        v:-1, abs(v) == parent
     *                        v:-2, abs(v) != parent, v < 0
     *                        change += dfs(g, 2, 3)
     *                                  v:3, abs(v) == parent, continue
     *                        return change:0
     *              return change:1
     *              v:0, abs(v) == parent:0, continue
     *    return change:2
     *    v:-4, abs(v) != -1, v<0
     *    change += dfs(g, 4, 0), parent:0
     *              v:0, abs(v) == parent, continue
     *              v:5, abs(5) != parent, v > 0, change++ --> change:1
     *              change += dfs(g, 5, 4), parent:4
     *                        v:-4, abs(-4) == parent, continue
     *              return change:1
     *   return change:3
     *
     */
    private int dfs(List<Integer>[] graph, int u, int parent) {
        int change = 0;
        for (int v : graph[u]) {
            /**
             * Skip the edge has been visited
             */
            if (Math.abs(v) == parent) {
                continue;
            }
            /**
             * Count the edge need to reverse its direction
             */
            if (v > 0) {
                change++;
            }
            change += dfs(graph, Math.abs(v), u);
        }
        return change;
    }


}
