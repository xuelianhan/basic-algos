package org.ict.algorithm.leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    /**
     * Topological Sort Solution.
     * @param colors
     * @param edges
     * @return
     */
    public int largestPathValueV2(String colors, int[][] edges) {
        return 0;
    }


    public int largestPathValueV1(String colors, int[][] edges) {
        return 0;
    }


    /**
     * Breadth-First-Search Solution
     * Time Cost 77ms
     * e.g. colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
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
        int[] inDegree = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            inDegree[v]++;
        }

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processedCnt = 0;
        int res = 0;
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
