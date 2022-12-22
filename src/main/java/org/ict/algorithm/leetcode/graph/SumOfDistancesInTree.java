package org.ict.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: The tree is shown above.
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.
 * Hence, answer[0] = 8, and so on.
 * Example 2:
 *
 *
 * Input: n = 1, edges = []
 * Output: [0]
 * Example 3:
 *
 *
 * Input: n = 2, edges = [[1,0]]
 * Output: [1,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 3 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * The given input represents a valid tree.
 * @author sniper
 * @date 22 Dec, 2022
 * LC834
 */
public class SumOfDistancesInTree {

    /**
     * Solution provided by lee215
     * @see <a href="https://leetcode.com/problems/sum-of-distances-in-tree/solutions/130583/c-java-python-pre-order-and-post-order-dfs-o-n"></a>
     * @author lee215
     * @param n
     * @param edges
     * @return
     */
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] res = new int[n];
        int[] count = new int[n];
        List<HashSet<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tree.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1, tree, count, res);
        dfs2(0, -1, tree, count, res);
        return res;
    }

    public void dfs(int root, int pre, List<HashSet<Integer>> tree, int[] count, int[] res) {
        for (int cur : tree.get(root)) {
            if (cur == pre) {
                continue;
            }
            dfs(cur, root, tree, count, res);
            count[root] += count[cur];
            res[root] += res[cur] + count[cur];
        }
        count[root]++;
    }

    public void dfs2(int root, int pre, List<HashSet<Integer>> tree, int[] count, int[] res) {
        for (int cur : tree.get(root)) {
            if (cur == pre) {
                continue;
            }
            res[cur] = res[root] - count[cur] + count.length - count[cur];
            dfs2(cur, root, tree, count, res);
        }
    }

}
