package org.ict.algorithm.leetcode.graph;

import java.util.*;

/**
 * @author sniper
 * @date 10 Apr, 2023
 */
public class TopologicalSort {

    /**
     * Topological-order: 0-->1-->2-->3-->4
     * @param args
     */
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {2, 3}, {1, 3}, {2, 4}, {3, 4}};
        //int n = 3;
        //int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        TopologicalSort instance = new TopologicalSort();
        int[] res1 = instance.sortByKahn(n, edges);
        System.out.println("kahn topological order:" + Arrays.toString(res1));
        int[] res2 = instance.sortByReversePostOrderOfDFS(n, edges);
        System.out.println("reverse post dfs topological order:" + Arrays.toString(res2));
    }

    /**
     * Kahn Algorithm
     *
     * It requires additional space for storing the indegrees of the nodes.
     * Put all the vertices with 0 in-degree in to a queue q.
     * Get a vertex u at a time from q, and decrement the in-degree of all its neighbors.
     * If a neighbor has 0 in-degree, add it to q.
     * Keep repeating until we exhaust q.
     * If the number of visited vertices equals the total number of vertices, it's a DAG;
     * otherwise, there must be a circle in the graph.
     * @param n
     * @param edges
     */
    public int[] sortByKahn(int n, int[][] edges) {
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
         * If there exists a cycle, no nodes have 0-in-degree,
         * so the following queue is empty while being initialized.
         * At last, the order list is empty.
         */
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        /**
         * Collect topological-order into order
         */
        List<Integer> order = new LinkedList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            order.add(cur);
            for (int neighbor : graph[cur]) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (order.size() == n) {
            return order.stream().mapToInt(i -> i).toArray();
        } else {
            /**
             * No topological-order exist.
             */
            return new int[0];
        }
    }

    private static final int un_visited = -1;
    private static final int visiting = 0;
    private static final int visited = 1;
    /**
     * Post-Order-Traversal with Depth-First-Search.
     *
     * A DFS version topological sort must be a Post-order DFS + Memoization.
     * Each vertex has three states:
     * -1:unvisited
     * 0: being visited in the current DFS session. If we visit a node with state 0, it means there is a circle in the graph.
     * 1: has been visited in a previous DFS session and this vertex is not in a circle.
     * It's a post-order DFS -- the node is pushed into the answer after all its subsequent nodes are visited.
     * Don't forget to reverse the ans before returning.
     * @see <a href="https://liuzhenglaichn.gitbook.io/algorithm/graph/topological-sort"></a>
     * @return
     */
    public int[] sortByReversePostOrderOfDFS(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
        }

        /**
         * states array:
         * -1 unvisited, 0 visiting, 1 visited
         */
        int[] states = new int[n];
        Arrays.fill(states, un_visited);
        Deque<Integer> postorder = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!dfs(graph, i, states, postorder)) {
                return new int[0];
            }
        }
        int[] arr = new int[n];
        int i = n - 1;
        while (!postorder.isEmpty()) {
            arr[i--] = postorder.pop();
        }
        return arr;
    }

    private boolean dfs(List<Integer>[] graph, int v, int[] states, Queue<Integer> postorder) {
        if (states[v] != un_visited) {
            return states[v] == visited ? true : false;
        }
        states[v] = visiting;
        for (int w : graph[v]) {
            if (!dfs(graph, w, states, postorder)) {
                return false;
            }
        }
        postorder.add(v);
        states[v] = visited;
        return true;
    }



}
