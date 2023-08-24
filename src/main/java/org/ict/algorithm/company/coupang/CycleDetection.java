package org.ict.algorithm.company.coupang;

/**
 * @author sniper
 * @date 17 Aug 2023
 */
public class CycleDetection {

    /**
     * This code first creates a boolean array visited to keep track of the visited nodes.
     * The code also creates a boolean array dfsVisited to keep track of the nodes that have been visited during the DFS traversal.
     *
     * The code then iterates over all the nodes in the graph.
     * For each node, the code checks if the node has been visited.
     * If it has not been visited, then the code calls the dfs() function to recursively traverse the graph starting from that node.
     *
     * The dfs() function takes three arguments: the current node, the visited array,
     * and the dfsVisited array.
     * The dfs() function first marks the current node as visited.
     * The function then iterates over all the neighbors of the current node.
     * For each neighbor, the function checks if the neighbor has been visited.
     * If the neighbor has not been visited,
     * then the function calls the dfs() function recursively to traverse the graph starting from that neighbor.
     *
     * The dfs() function returns true if it finds a cycle in the graph.
     * Otherwise, the function returns false.
     * @param graph
     * @return
     */
    public boolean hasCycle(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] dfsVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(graph, i, visited, dfsVisited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int[][] graph, int i, boolean[] visited, boolean[] dfsVisited) {
        visited[i] = true;
        dfsVisited[i] = true;

        for (int j = 0; j < graph[i].length; j++) {
            if (!visited[j]) {
                if (dfs(graph, j, visited, dfsVisited)) {
                    return true;
                }
            } else if (dfsVisited[j]) {
                return true;
            }
        }

        dfsVisited[i] = false;
        return false;
    }
}
