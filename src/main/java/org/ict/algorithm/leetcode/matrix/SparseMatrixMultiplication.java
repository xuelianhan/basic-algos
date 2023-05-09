package org.ict.algorithm.leetcode.matrix;


import java.util.*;

/**
 * Description
 * Given two sparse matrices
 * mat1 of size m x k and mat2 of size k x n,
 * return the result of mat1 x mat2.
 * You may assume that multiplication is always possible.
 *
 *
 * Example 1:
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 *
 * Example 2:
 * Input: mat1 = [[0]], mat2 = [[0]]
 * Output: [[0]]
 *
 *
 * Constraints:
 * m == mat1.length
 * k == mat1[i].length == mat2.length
 * n == mat2[i].length
 * 1 <= m, n, k <= 100
 * -100 <= mat1[i][j], mat2[i][j] <= 100
 * @author sniper
 * @date 09 May 2023
 * LC311, Medium, frequency=7
 */
public class SparseMatrixMultiplication {

    public static void main(String[] args) {
        int[][] mat1 = {{1, 0, 0}, {-1, 0, 3}};
        int[][] mat2 = {{7 ,0, 0}, {0, 0, 0}, {0, 0, 1}};
        SparseMatrixMultiplication instance = new SparseMatrixMultiplication();
        int[][] res = instance.multiplyV2(mat1, mat2);
        System.out.println(Arrays.deepToString(res));
    }


    /**
     * Understanding the following solution
     *
     * -----------------------------------
     * class Solution {
     * public:
     *     vector<vector<int>> multiply(vector<vector<int>>& mat1, vector<vector<int>>& mat2) {
     *         int r1 = mat1.size(), c1 = mat1[0].size(), c2 = mat2[0].size();
     *         vector<vector<int>> res(r1, vector<int>(c2));
     *         unordered_map<int, vector<int>> mp;
     *         for (int i = 0; i < r1; ++i) {
     *             for (int j = 0; j < c1; ++j) {
     *                 if (mat1[i][j] != 0) mp[i].push_back(j);
     *             }
     *         }
     *         for (int i = 0; i < r1; ++i) {
     *             for (int j = 0; j < c2; ++j) {
     *                 for (int k : mp[i]) res[i][j] += mat1[i][k] * mat2[k][j];
     *             }
     *         }
     *         return res;
     *     }
     * };
     * ----------------------------------
     * class Solution:
     *     def multiply(self, mat1: List[List[int]], mat2: List[List[int]]) -> List[List[int]]:
     *         r1, c1, c2 = len(mat1), len(mat1[0]), len(mat2[0])
     *         res = [[0] * c2 for _ in range(r1)]
     *         mp = defaultdict(list)
     *         for i in range(r1):
     *             for j in range(c1):
     *                 if mat1[i][j] != 0:
     *                     mp[i].append(j)
     *         for i in range(r1):
     *             for j in range(c2):
     *                 for k in mp[i]:
     *                     res[i][j] += mat1[i][k] * mat2[k][j]
     *         return res
     * @param mat1
     * @param mat2
     * @return
     */
    public int[][] multiplyV2(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;
        int[][] res = new int[m][n];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                if (mat1[i][j] != 0) {
                    map.computeIfAbsent(i, a -> new ArrayList<>()).add(j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map.containsKey(i)) {
                    for (int c : map.get(i)) {
                        res[i][j] += mat1[i][c] * mat2[c][j];
                    }
                }
            }
        }
        return res;
    }

    public int[][] multiplyV1(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int l = 0; l < k; l++) {
                if (mat1[i][l] != 0) {
                    for (int j = 0; j < n; j++) {
                        if (mat2[l][j] != 0) {
                            res[i][j] += mat1[i][l] * mat2[l][j];
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * Brute-Force Solution
     *
     * class Solution:
     *     def multiply(self, mat1: List[List[int]], mat2: List[List[int]]) -> List[List[int]]:
     *         r1, c1, c2 = len(mat1), len(mat1[0]), len(mat2[0])
     *         res = [[0] * c2 for _ in range(r1)]
     *         for i in range(r1):
     *             for j in range(c2):
     *                 for k in range(c1):
     *                     res[i][j] += mat1[i][k] * mat2[k][j]
     *         return res
     * @param mat1
     * @param mat2
     * @return
     */
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int l = 0; l < k; l++) {
                for (int j = 0; j < n; j++) {
                    res[i][j] += mat1[i][l] * mat2[l][j];
                }
            }
        }
        return res;
    }
}
