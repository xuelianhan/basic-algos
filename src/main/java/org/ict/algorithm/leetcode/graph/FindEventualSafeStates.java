package org.ict.algorithm.leetcode.graph;


import java.util.*;

/**
 * There is a directed graph of n nodes with each node labeled from 0 to n - 1.
 * The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i,
 * meaning there is an edge from node i to each node in graph[i].
 *
 * A node is a terminal node if there are no outgoing edges.
 * A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
 * Return an array containing all the safe nodes of the graph.
 * The answer should be sorted in ascending order.
 *
 * Example 1:
 * Illustration of graph
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: The given graph is shown above.
 * Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
 * Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
 *
 * Example 2:
 * Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * Output: [4]
 * Explanation:
 * Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
 *
 *
 * Constraints:
 * n == graph.length
 * 1 <= n <= 10^4
 * 0 <= graph[i].length <= n
 * 0 <= graph[i][j] <= n - 1
 * graph[i] is sorted in a strictly increasing order.
 * The graph may contain self-loops.
 * The number of edges in the graph will be in the range [1, 4 * 10^4].
 * @author sniper
 * @date 12 Jul 2023
 * LC802, Medium
 */
public class FindEventualSafeStates {

    /**
     * Depth-First-Search Solution
     * Similar with eventualSafeNodesV1 but easier to understand.
     * Time Cost 6ms
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodesV2(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if (graph == null || graph.length == 0) {
            return res;
        }

        int n = graph.length;
        State[] states = new State[n];
        for (int node = 0; node < n; node++) {
            if (isSafe(node, graph, states)) {
                res.add(node);
            }
        }
        return res;
    }

    private boolean isSafe(int node, int[][] graph, State[] states) {
        if (states[node] != null) {
            /**
             * If we are already visiting this node, then there is a cycle.
             * If this is a visited node, then there is no cycle and can mark this as a safe node
             */
            return states[node] == State.SAFE;
        }
        states[node] = State.UNSAFE;
        for (int next : graph[node]) {
            if (!isSafe(next, graph, states)) {
                return false;
            }
        }
        states[node] = State.SAFE;
        return true;
    }

    private enum State {
        SAFE,
        UNSAFE
    }

    /**
     * Depth-First-Search Solution
     * Time Cost 5ms
     * @author kevincongcc
     * @see <a href="https://leetcode.com/problems/find-eventual-safe-states/solutions/119871/straightforward-java-solution-easy-to-understand/"></a>
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodesV1(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if (graph == null || graph.length == 0) {
            return res;
        }
        int n = graph.length;
        /**
         * 0: have not been visited.
         * 1: safe node
         * 2: unsafe node
         */
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (dfs(graph, i, color)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean dfs(int[][] graph, int start, int[] color) {
        if (color[start] != 0) {
            return color[start] == 1;
        }
        color[start] = 2;
        for (int v : graph[start]) {
            /**
             * When we encounter a node which results in a cycle,we return false
             */
            if (!dfs(graph, v, color)) {
                return false;
            }
        }
        color[start] = 1;
        return true;
    }

    /**
     * Topological Sort Solution
     * Time Cost 53ms
     * @author kiranpalsingh1806
     * @see <a href="https://leetcode.com/problems/find-eventual-safe-states/solutions/2600013/c-topological-sort-diagram/"></a>
     * @param graph
     * @return
     *
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int n = graph.length;
        boolean[] safe = new boolean[n];
        int[] outDegree = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        Map<Integer, TreeSet<Integer>> reverseGraph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int v : graph[i]) {
                reverseGraph.computeIfAbsent(v, k -> new TreeSet<>()).add(i);
            }
            outDegree[i] = graph[i].length;
            if (outDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            safe[u] = true;
            for (int v : reverseGraph.getOrDefault(u, new TreeSet<>())) {
                outDegree[v]--;
                if (outDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (safe[i]) {
                res.add(i);
            }
        }

        return res;
    }
}
