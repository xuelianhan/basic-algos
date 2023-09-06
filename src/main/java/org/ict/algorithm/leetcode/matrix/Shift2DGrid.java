package org.ict.algorithm.leetcode.matrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a 2D grid of size m x n and an integer k.
 * You need to shift the grid k times.
 *
 * In one shift operation:
 *
 * Element at grid[i][j] moves to grid[i][j + 1].
 * Element at grid[i][n - 1] moves to grid[i + 1][0].
 * Element at grid[m - 1][n - 1] moves to grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * Output: [[9,1,2],[3,4,5],[6,7,8]]
 * Example 2:
 *
 *
 * Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * Example 3:
 *
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * Output: [[1,2,3],[4,5,6],[7,8,9]]
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 50
 * 1 <= n <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 * @author sniper
 * @date 05 Sep 2023
 * LC1260, Easy
 */
public class Shift2DGrid {

    /**
     * Time Cost 8ms
     * ----------------------------------------------
     *  def shiftGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
     *         m = len(grid)
     *         n = len(grid[0])
     *         res = [[0] * n for _ in range(m)]
     *         k %= m * n
     *
     *         for i in range(m):
     *             for j in range(n):
     *                 index = (i * n + j + k) % (m * n)
     *                 x = index // n
     *                 y = index % n
     *                 res[x][y] = grid[i][j]
     *
     *         return res
     * ----------------------------------------------
     *
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        List<List<Integer>> res = new ArrayList<>();
        int[][] arr = new int[m][n];
        k %= m * n;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = (i * n + j + k) % (m * n);
                int x = index / n;
                int y = index % n;
                arr[x][y] = grid[i][j];
            }
        }


        for (int[] row : arr) {
            res.add(Arrays.stream(row).boxed().collect(Collectors.toList()));
        }
        return res;
    }
}
