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
     * Tree View provided by kklov369
     * # First DFS
     *         [TREE] |      [COUNT]     [RET]
     *            0   |       10          [ ] = (1+0) + (7+10) +(1+0)  = 19
     *          / | \ |      / | \       / | \
     *         1  2  3|     1  7  1     0  10 0
     *           /|\  |       /|\         /|\
     *          4 5 6 |      4 1 1       4 0 0
     *         /|     |     /|          /|
     *        7 8     |    1 2         0 1
     *         /      |     /           /
     *        9       |    1           0
     *
     * # Second DFS
     *       [RET]  |                  |
     *          19  |           19     |       19
     *        / | \ |          / | \   |      / | \
     *       0  10 0| [19-1+10-1] 10 0 |  28 [19-7+10-7] 0
     *         /|\  |           /|\    |       /|\
     *        4 0 0 |          4 0 0   |      4 0 0
     *       /|     |         /|       |     /|
     *      0 1     |        0 1       |    0 1
     *       /      |         /        |     /
     *      0       |        0         |    0
     *
     *  # Ans = [19,27,15,27,17,23,23,25,23,31]
     *  --------------Example--------------------------------------------
     *
     *      Count         Ret         Dfs update ret...
     *        6            8            8              8
     *       / \          / \          /  \           / \
     *      1   4        0   3    [8-1+N-1] 3       12   6
     *         /|\          /|\          /|\            /|\
     *        1 1 1        0 0 0        0 0 0        10 10 [6-1+N-1]   N=6
     *
     * Preword
     * Well, another long solution.
     * what I am always trying is to:
     *
     * let you understand my solution (with my poor explanation)
     * prevent from reading my codes
     *
     * Intuition
     * What if given a tree, with a certain root 0?
     * In O(N) we can find sum of distances in tree from root and all other nodes.
     * Now for all N nodes?
     * Of course, we can do it N times and solve it in O(N^2).
     * C++ and Java may get accepted luckily, but it's not what we want.
     *
     * When we move our root from one node to its connected node,
     * one part of nodes get closer, one the other part get further.
     *
     * If we know exactly how many nodes in both parts, we can solve this problem.
     *
     * With one single traversal in tree, we should get enough information for it and
     * don't need to do it again and again.
     *
     *
     * Explanation
     * Let's solve it with node 0 as root.
     *
     * Initial an array of hashset tree, tree[i] contains all connected nodes to i.
     * Initial an array count, count[i] counts all nodes in the subtree i.
     * Initial an array of res, res[i] counts sum of distance in subtree i.
     *
     * Post order dfs traversal, update count and res:
     * count[root] = sum(count[i]) + 1
     * res[root] = sum(res[i]) + sum(count[i])
     *
     * Pre order dfs traversal, update res:
     * When we move our root from parent to its child i,
     * count[i] points get 1 closer to root, n - count[i] nodes get 1 futhur to root.
     * res[i] = res[root] - count[i] + N - count[i]
     *
     * return res, done.
     * @author lee215
     * @see <a href="https://leetcode.com/problems/sum-of-distances-in-tree/solutions/130583/c-java-python-pre-order-and-post-order-dfs-o-n"></a>
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
        /**
         * Use a pre pointer to prevent duplicated visiting.
         * because this is a tree, it has no circle.
         * So a pre pointer is enough(no need to create a visited array)
         */
        dfs(0, -1, tree, count, res);
        dfs2(0, -1, tree, count, res);
        return res;
    }

    /**
     * Post-Order-DFS-Traversal
     * @param root
     * @param pre
     * @param tree
     * @param count
     * @param res
     */
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

    /**
     * Pre-Order-DFS-Traversal
     * @param root
     * @param pre
     * @param tree
     * @param count
     * @param res
     */
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
