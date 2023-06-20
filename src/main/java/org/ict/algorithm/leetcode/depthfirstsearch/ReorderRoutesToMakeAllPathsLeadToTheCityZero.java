package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

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
     * Depth-First-Search Solution
     * @param n
     * @param connections
     * @return
     */
    public int minReorderV1(int n, int[][] connections) {
        return 0;
    }

    /**
     * Understanding the following solution
     *
     * Depth-First-Search Solution
     * -------------------------------
     * Treat the graph as undirected.
     * Start a dfs from the root, if you come across an edge in the forward direction,
     * you need to reverse the edge.
     * -------------------------------
     *
     * -------------------------------
     *
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
         */
        return dfs(graph, 0, -1);
    }

    /**
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
