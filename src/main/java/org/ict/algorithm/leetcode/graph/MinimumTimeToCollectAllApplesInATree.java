package org.ict.algorithm.leetcode.graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices.
 * You spend 1 second to walk over one edge of the tree.
 * Return the minimum time in seconds you have to spend to collect all apples in the tree,
 * starting at vertex 0 and coming back to this vertex.
 *
 * The edges of the undirected tree are given in the array edges,
 * where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
 * Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple;
 * otherwise, it does not have any apple.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * Output: 8
 * Explanation: The figure above represents the given tree where red vertices have an apple.
 * One optimal path to collect all apples is shown by the green arrows.
 * Example 2:
 *
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * Output: 6
 * Explanation: The figure above represents the given tree where red vertices have an apple.
 * One optimal path to collect all apples is shown by the green arrows.
 * Example 3:
 *
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai < bi <= n - 1
 * fromi < toi
 * hasApple.length == n
 * @author sniper
 * @date 12 Jan, 2023
 * LC1443, Medium
 * Tags:Apple, Facebook, Medium, Salesforce
 */
public class MinimumTimeToCollectAllApplesInATree {

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        List<Boolean> hasApple = Arrays.asList(false, false, true, false, true, true, false);

        MinimumTimeToCollectAllApplesInATree instance = new  MinimumTimeToCollectAllApplesInATree();
        instance.minTime(n, edges, hasApple);
    }

    public int minTimeV1(int n, int[][] edges, List<Boolean> hasApple) {
        int res = 0;
        return res;
    }

    /**
     * Time Cost 78ms
     * This problem is similar with {@link SumOfDistancesInTree}
     * But it requires the sum distance from root to all the nodes that have apples.
     *
     * Time Complexity O(V + E)
     * Space Complexity O(V)
     * @param n
     * @param edges
     * @param hasApple
     * @return
     */
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        boolean[] visited = new boolean[n];
        List<HashSet<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        return dfs(0, 0, tree, visited, hasApple);
    }

    /**
     * e.g.
     * n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
     *      0
     *    /  \
     *   1    2
     *  / \  / \
     * 4  5 3   6
     * dfs(0, 0)
     *   dfs(1, 2)
     *     dfs(4, 2) --> return 2
     *     dfs(5, 2) --> return 2
     *     --> return 4
     *   --> 4 + 2
     *   dfs(2, 2)
     *     dfs(3, 2) --> return 0
     *     dfs(6, 2) --> return 0
     *   --> return 2 + 0
     * --> return 6 + 2
     *----------------------------
     *     dfs(4, 2) --> childrenCost:0
     *     dfs(4, 2) --> totalCost:2
     *     dfs(5, 2) --> childrenCost:0
     *     dfs(5, 2) --> totalCost:2
     *   dfs(1, 2) --> childrenCost:4
     *   dfs(1, 2) --> totalCost:6
     *     dfs(3, 2) --> childrenCost:0
     *     dfs(6, 2) --> childrenCost:0
     *   dfs(2, 2) --> childrenCost:0
     *   dfs(2, 2) --> totalCost:2
     * dfs(0, 0) --> childrenCost:8
     * dfs(0, 0) --> totalCost:8
     * @param root
     * @param cost
     * @param tree
     * @param visited
     * @param hasApple
     * @return
     */
    public int dfs(int root, int cost, List<HashSet<Integer>> tree, boolean[] visited, List<Boolean> hasApple) {
        if (visited[root]) {
            return 0;
        }
        visited[root] = true;
        int childrenCost = 0;
        for (int neighbor : tree.get(root)) {
            /**
             * Recursively checking the apples in the sub-tree
             */
            childrenCost += dfs(neighbor, 2, tree, visited, hasApple);
        }
        //System.out.println("dfs("+root + ", " + cost +") --> childrenCost:" + childrenCost);
        if (childrenCost == 0 && hasApple.get(root) == false) {
            return 0;
        }
        //System.out.println("dfs("+root + ", " + cost +") --> totalCost:" + (childrenCost + cost));
        return (childrenCost + cost);
    }
}
