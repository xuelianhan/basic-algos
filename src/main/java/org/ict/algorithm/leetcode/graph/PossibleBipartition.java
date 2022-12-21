package org.ict.algorithm.leetcode.graph;

/**
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi]
 * indicates that the person labeled ai does not like the person labeled bi,
 * return true if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4] and group2 [2,3].
 * Example 2:
 *
 * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 *
 * Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 104
 * dislikes[i].length == 2
 * 1 <= dislikes[i][j] <= n
 * ai < bi
 * All the pairs of dislikes are unique.
 * @author sniper
 * @date 21 Dec, 2022
 * LC886
 */
public class PossibleBipartition {

    public boolean possibleBipartition(int n, int[][] dislikes) {

        return false;
    }

    class UnionFind {
        int[] parent;
        private int count;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find (int p) {
            while (p != parent[p]) {
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j) {
                return;
            }
            parent[i] = j;
            count--;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }
}
