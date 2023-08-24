package org.ict.algorithm.leetcode.depthfirstsearch;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 *
 * You are also given three integers sr, sc, and color.
 * You should perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel,
 * plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
 * plus any pixels connected 4-directionally to those pixels (also with the same color), and so on.
 * Replace the color of all of the aforementioned pixels with color.
 *
 * Return the modified image after performing the flood fill.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel),
 * all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 * Example 2:
 *
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel is already colored 0, so no changes are made to the image.
 *
 *
 * Constraints:
 *
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], color < 2^16
 * 0 <= sr < m
 * 0 <= sc < n
 * @author sniper
 * @date 24 Aug 2023
 * LC733, Easy,
 *
 */
public class FloodFill {

    /**
     * Understanding the following solution
     * DFS Solution
     * Time Cost 0ms
     *
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     */
    public int[][] floodFillV1(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int srcColor, int newColor) {
        int m = image.length;
        int n = image[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] != srcColor) {
            return;
        }
        image[i][j] = newColor;
        dfs(image, i + 1, j, srcColor, newColor);
        dfs(image, i - 1, j, srcColor, newColor);
        dfs(image, i, j + 1, srcColor, newColor);
        dfs(image, i, j - 1, srcColor, newColor);
    }


    /**
     * Time Limit Exceed
     * BFS Solution
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }
        int m = image.length;
        int n = image[0].length;
        int srcColor = image[sr][sc];
        int[][] res = Arrays.stream(image)
                .map((int[] row) -> row.clone())
                .toArray((int length) -> new int[length][]);

        int[] dirs = {-1, 0, 1, 0, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                res[x][y] = srcColor;
                for (int j = 0; j < 4; j++) {
                    int dx = x + dirs[j];
                    int dy = y = dirs[j + 1];
                    if (dx < 0 || dx >= m || dy < 0 || dy >= n || res[dx][dy] != srcColor) {
                        continue;
                    }
                    queue.offer(new int[] {dx, dy});
                }
            }
        }
        return res;
    }
}
