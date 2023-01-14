package org.ict.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1.
 * The tree is represented by a 0-indexed array parent of size n,
 * where parent[i] is the parent of node i.
 * Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 *
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3.
 * The length of this path is 3, so 3 is returned.
 * It can be proven that there is no longer path that satisfies the conditions.
 * Example 2:
 *
 *
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3.
 * The length of this path is 3, so 3 is returned.
 *
 *
 * Constraints:
 *
 * n == parent.length == s.length
 * 1 <= n <= 10^5
 * 0 <= parent[i] <= n - 1 for all i >= 1
 * parent[0] == -1
 * parent represents a valid tree.
 * s consists of only lowercase English letters.
 * @author sniper
 * @date 13 Jan, 2023
 * LC2246, Hard
 */
public class LongestPathWithDifferentAdjacentCharacters {

    public int longestPathV2(int[] parent, String s) {
        return 0;
    }

    public int longestPathV1(int[] parent, String s) {
        return 0;
    }

    /**
     * This problem is similar to Diameter of tree problem {@link org.ict.algorithm.leetcode.tree.DiameterOfBinaryTree}
     * Time Complexity O(N)
     * Space Complexity O(N)
     * Time Cost 189ms
     * @see <a href="https://offcampusphodenge.com/leetcode-daily-challenge/longest-path-with-different-adjacent-characters-leetcode-solution/"></a>
     * @param parent
     * @param s
     * @return
     */
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<HashSet<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new HashSet<>());
        }

        /**
         * 1.Why we start from 1 other than 0?
         * 2.Why not add tree.get(i).add(parent[i]);?
         * Create an adjacency list adj of size n to represent the tree.
         * For each node, i starts from 1 to n - 1,
         * add i as a child of the parent node parent[i] in the adjacency list
         */
        for (int i = 1; i < n; i++) {
            tree.get(parent[i]).add(i);
        }
        int[] res = new int[1];
        /**
         * The minimum longest path that can be guaranteed is the node itself,
         * which has a length of 1.
         */
        res[0] = 1;
        /**
         * Traverse from the root(0) with DFS.
         */
        dfs(0, tree, s, res);
        return res[0];
    }

    /**
     * e.g.parent = [-1,0,0,1,1,2], s = "abacbe"
     *          0-a
     *         /  \
     *       2-a  1-b
     *       /    / \
     *     5-e  3-c 4-b
     * dfs(0-a)
     *     dfs(2-a)
     *         dfs(5-e)
     *     dfs(1-b)
     *         dfs(3-c)
     *         dfs(4-b)
     *
     * @param curNode
     * @param tree
     * @param labels
     * @param res
     * @return
     */
    private int dfs(int curNode, List<HashSet<Integer>> tree, String labels, int[] res) {
        int currentMax = 0;
        int secondMax = 0;
        for (int neighbor : tree.get(curNode)) {
            int result = dfs(neighbor, tree, labels, res);
            /**
             * Skip neighbor of the same label with the current node.
             */
            if (labels.charAt(curNode) == labels.charAt(neighbor)) {
                continue;
            }
            if (result > currentMax) {
                secondMax = currentMax;
                currentMax = result;
            } else if (result > secondMax) {
                secondMax = result;
            }
        }
        /**
         * The path we are looking for
         * can be the concatenation of two paths
         * that we found while traversing the tree
         */
        res[0] = Math.max(res[0], 1 + currentMax + secondMax);
        return 1 + currentMax;
    }

}
