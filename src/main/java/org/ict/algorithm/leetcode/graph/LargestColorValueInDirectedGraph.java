package org.ict.algorithm.leetcode.graph;


import java.util.*;

/**
 * There is a directed graph of n colored nodes and m edges.
 * The nodes are numbered from 0 to n - 1.
 * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed).
 * You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk,
 * such that there is a directed edge from xi to xi+1 for every 1 <= i < k.
 * The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
 * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
 *
 *
 *
 * Example 1:
 * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
 *
 * Example 2:
 * Input: colors = "a", edges = [[0,0]]
 * Output: -1
 * Explanation: There is a cycle from 0 to 0.
 *
 *
 * Constraints:
 * n == colors.length
 * m == edges.length
 * 1 <= n <= 10^5
 * 0 <= m <= 10^5
 * colors consists of lowercase English letters.
 * 0 <= aj, bj < n
 * @author sniper
 * @date 09 Apr, 2023
 * LC1857, Hard
 */
public class LargestColorValueInDirectedGraph {

    public static void main(String[] args) {
        String colors = "abaca";
        int[][] edges = {{0,1},{0,2},{2,3},{3,4}};
        LargestColorValueInDirectedGraph instance = new LargestColorValueInDirectedGraph();
        instance.largestPathValue(colors, edges);
    }

    /**
     * Post-order DFS + Memoization Solution
     * @param colors
     * @param edges
     * @return
     */
    public int largestPathValueV2(String colors, int[][] edges) {
        //todo
        return 0;
    }


    public int largestPathValueV1(String colors, int[][] edges) {
        //todo
        return 0;
    }


    /**
     * Breadth-First-Search Solution(Kahn Algorithm)
     * Time complexity: O(n)
     * Space complexity: O(n*26)
     *
     * Time Cost 77ms
     * Topological Sort:
     * 1.Put all the vertices with 0 in-degree into a queue.
     * 2.Get a vertex cur from queue, and decrement the in-degree of all its neighbors.
     * 3.If a neighbor has 0 in-degree, add it into the queue.
     * 4.Keep repeating until queue is exhausted.
     * 5.If the number of visited vertices equals the total number of vertices, it's DAG;
     * otherwise, there must be a circle in the graph.
     *
     * e.g. colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
     * Topological-Order-Sequence: 0-->1-->2-->3-->4
     *                             a-->b-->a-->c-->a
     * Longest Path: 0-->2-->3-->4
     *               a-->a-->c-->a
     * Notice here, we need to count the character frequency of the path, not the frequency of topological-order sequence.
     * freq[cur][c]: Max freq of color c after visiting node cur.
     * After having visited node-4, the freq array likes the following:
     * freq:[[1,0,0,0...], <-- path:0(sub-colors:"a"), max:1
     *       [1,1,0,0...], <-- path:0,1(sub-colors:"ab"), max:1
     *       [2,0,0,0...], <-- path:0,2(sub-colors:"aa"), max:2
     *       [2,0,1,0...], <-- path:0,2,3(sub-colors:"aac"), max:2
     *       [3,0,1,0...]] <-- path:0,2,3,4(sub-colors:"aaca"), max:3
     *
     *
     * @author votrubac
     * @see <a href="https://leetcode.com/problems/largest-color-value-in-a-directed-graph/solutions/1200575/topological-sort-vs-dfs-vs-dijkstra"></a>
     * @param colors
     * @param edges
     * @return
     */
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        /**
         * edge: u-->v
         */
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            inDegree[v]++;
        }
        /**
         * Identify all nodes with no incoming edges and add these nodes to queue.
         * Because topological-order start from these nodes without incoming edges.
         */
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processedCnt = 0;
        int res = 0;
        /**
         * Notice here, we need to count the character frequency of the path, not the frequency of topological-order sequence.
         * freq[cur][c]:
         * Max freq of color c after visiting node cur.
         */
        int[][] freq = new int[n][26];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            processedCnt++;
            res = Math.max(res, ++freq[cur][colors.charAt(cur) - 'a']);
            for (int x : graph[cur]) {
                for (int i = 0; i < 26; i++) {
                    freq[x][i]= Math.max(freq[x][i], freq[cur][i]);
                }
                if (--inDegree[x] == 0) {
                    queue.offer(x);
                }
            }

        }
        return processedCnt == n ? res : -1;
    }
}
