package org.ict.algorithm.leetcode.graph;

import java.util.*;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
 * find all possible paths from node 0 to node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
 * (i.e., there is a directed edge from node i to node graph[i][j]).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Example 2:
 *
 *
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 *
 *
 * Constraints:
 *
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i (i.e., there will be no self-loops).
 * All the elements of graph[i] are unique.
 * The input graph is guaranteed to be a DAG.
 * @author sniper
 * @date 31 Dec, 2022
 * LC797
 */
public class AllPathsFromSourceToTarget {


    /**
     * Time Cost 1ms
     * Understanding the following solution.
     *
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTargetV2(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        /**
         * Add the source node-0 to the path.
         */
        path.add(0);
        backtrackV2(graph, result, path, 0);
        return result;
    }

    private void backtrackV2(int[][] graph, List<List<Integer>> result, List<Integer> path, int cur) {
        /**
         * If current node is the n-1, it means we have arrived at the destination.
         */
        if (cur == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int neighbor : graph[cur]) {
            path.add(neighbor);
            backtrackV2(graph, result, path, neighbor);
            path.remove(path.size() - 1);
        }
    }


    /**
     * Time Cost 1ms
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTargetV1(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        backtrackV1(graph, result, path, 0);
        return result;
    }

    private void backtrackV1(int[][] graph, List<List<Integer>> result, List<Integer> path, int cur) {
        if (cur == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }
        /**
         * The following checking is not necessary.
         */
        if (graph[cur].length == 0) {
            return;
        }
        for (int neighbor : graph[cur]) {
            path.add(neighbor);
            backtrackV1(graph, result, path, neighbor);
            path.remove(path.size() - 1);
        }
    }


    /**
     * Time Cost 1ms
     * The idea is to do Depth First Traversal of a given directed graph.
     * Start the DFS traversal from the source.
     * Keep storing the visited vertices in an array or HashMap say ‘path[]’.
     * If the destination vertex is reached, collect the contents of path[].
     * The important thing is to mark current vertices in the path[] as visited also so that the traversal doesn’t go in a cycle.
     *
     * e.g.graph = [[1,2],[3],[3],[]]
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        path.add(0);
        backtrack(graph, result, path, 0, visited);
        return result;
    }

    private void backtrack(int[][] graph, List<List<Integer>> result, List<Integer> path, int cur, boolean[] visited) {
        if (cur == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }
        visited[cur] = true;
        for (int neighbor : graph[cur]) {
            if (visited[neighbor]) {
                continue;
            }
            path.add(neighbor);
            backtrack(graph, result, path, neighbor, visited);
            path.remove(path.size() - 1);
        }
        visited[cur] = false;
    }

    /**
     * Breadth-First-Search Solution
     * Time Cost 7ms
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTargetV3(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> queue = new ArrayDeque<>();
        /**
         * Add the source node-0 to the queue.
         */
        queue.add(Arrays.asList(0));
        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int cur = path.get(path.size() - 1);
            if (cur == graph.length - 1) {
                result.add(new ArrayList<>(path));
            }

            for (int neighbor : graph[cur]) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(neighbor);
                queue.offer(newPath);
            }
        }
        return result;
    }

}
