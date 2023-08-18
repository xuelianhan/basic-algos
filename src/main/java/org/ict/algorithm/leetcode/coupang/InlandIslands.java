package org.ict.algorithm.leetcode.coupang;

/**
 * @author sniper
 * @date 17 Aug 2023
 */
public class InlandIslands {

    /**
     * This code first marks all visited cells as 0.
     * This is done to prevent us from counting the same island multiple times.
     * The code then iterates over all cells and calls the dfs() function for each cell that is not visited and has a value of 1.
     * The dfs() function recursively visits all the neighbors of the current cell.
     *
     * The code returns the number of islands.
     * @param matrix
     * @return
     */
    public int countInlandIslands(int[][] matrix) {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Mark all visited cells as 0.
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited[i][j] = false;
            }
        }

        // Iterate over all cells and count the number of islands.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && matrix[i][j] == 1) {
                    dfs(matrix, visited, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        // Mark the current cell as visited.
        visited[i][j] = true;

        // Iterate over the neighbors of the current cell.
        for (int row = i - 1; row <= i + 1; row++) {
            for (int col = j - 1; col <= j + 1; col++) {
                if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length && !visited[row][col] && matrix[row][col] == 1) {
                    dfs(matrix, visited, row, col);
                }
            }
        }
    }
}
