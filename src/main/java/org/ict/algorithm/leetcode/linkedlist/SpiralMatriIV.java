package org.ict.algorithm.leetcode.linkedlist;

import java.util.Arrays;

/**
 * You are given two integers m and n, which represent the dimensions of a matrix.
 *
 * You are also given the head of a linked list of integers.
 *
 * Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise),
 * starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.
 *
 * Return the generated matrix.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
 * Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
 * Explanation: The diagram above shows how the values are printed in the matrix.
 * Note that the remaining spaces in the matrix are filled with -1.
 * Example 2:
 *
 *
 * Input: m = 1, n = 4, head = [0,1,2]
 * Output: [[0,1,2,-1]]
 * Explanation: The diagram above shows how the values are printed from left to right in the matrix.
 * The last space in the matrix is set to -1.
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * The number of nodes in the list is in the range [1, m * n].
 * 0 <= Node.val <= 1000
 *
 * @author sniper
 * @date 18 Sep, 2022
 * LC2326
 */
public class SpiralMatriIV {

    /**
     * Recommend this solution.
     * we comment two if-condition compared with spiralMatrix.
     * @param m
     * @param n
     * @param head
     * @return
     */
    public int[][] spiralMatrixV4(int m, int n, ListNode head) {
        int[][] res = new int[m][n];

        for (int[] arr: res) {
            Arrays.fill(arr, -1);
        }

        int rowBegin = 0, rowEnd = m - 1;
        int colBegin = 0, colEnd = n - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd && head != null) {
            /**
             * Traverse Right
             */
            for (int i = colBegin; i <= colEnd; i++, head = head.next) {
                if (head != null) {
                    res[rowBegin][i] = head.val;
                } else {
                    break;
                }
            }
            rowBegin++;
            /**
             * Traverse Down
             */
            for (int i = rowBegin; i <= rowEnd; i++, head = head.next) {
                if (head != null) {
                    res[i][colEnd] = head.val;
                } else {
                    break;
                }
            }
            colEnd--;
            /**
             * Traverse left, comment if  (rowBegin <= rowEnd) is ok for this problem.
             */
            //if (rowBegin <= rowEnd) {
            for (int i = colEnd; i>= colBegin; i--, head = head.next) {
                if (head != null) {
                    res[rowEnd][i] = head.val;
                } else {
                    break;
                }
            }
            //}
            rowEnd--;
            /**
             * Traverse Up, comment if (colBegin <= colEnd) is ok for this problem.
             */
            //if (colBegin <= colEnd) {
            for (int i = rowEnd; i >= rowBegin; i--, head = head.next) {
                if (head != null) {
                    res[i][colBegin] = head.val;
                } else {
                    break;
                }
            }
            //}
            colBegin++;
        }//end-while-loop

        return res;
    }


    /**
     * Solution provided by votrubac
     *
     * We use directions (d) to right->down->left->up and then repeat. When we bump into an edge - we change the direction.
     * Note that an edge is also a cell that we've already filled (not equal to -1).
     * This makes it a bit easier, otherwise we could use an offset, incrementing it after each full turn.
     *
     * @param m
     * @param n
     * @param head
     * @return
     */
    public int[][] spiralMatrixV3(int m, int n, ListNode head) {
        int[][] res = new int[m][n];

        for (int[] arr: res) {
            Arrays.fill(arr, -1);
        }

        int i = 0, j = 0, cur_d = 0;
        int[] d = {0, 1, 0, -1, 0};
        for (; head != null; head = head.next) {
            res[i][j] = head.val;

            int ni = i + d[cur_d];
            int nj = j + d[cur_d + 1];

            if (Math.min(ni, nj) < 0 || ni >= m || nj >= n || res[ni][nj] != -1) {
                cur_d = (cur_d + 1) % 4;
            }
            i += d[cur_d];
            j += d[cur_d + 1];
        }
        return res;
    }

    /**
     *
     * @param m
     * @param n
     * @param head
     * @return
     */
    public int[][] spiralMatrixV2(int m, int n, ListNode head) {
        int[][] res = new int[m][n];

        for (int[] arr: res) {
            Arrays.fill(arr, -1);
        }

        int row = 0, col = 0, top = 0, left = 0, bottom = m-1, right = n-1;
        while(head != null) {
            res[row][col] = head.val;
            head = head.next;

            if (row == top && col < right) {
                /**
                 * fill top most unfilled row
                 */
                col++;
                if(col == right) top++;
            } else if (col == right && row < bottom) {
                /**
                 * fill right most unfilled column
                 */
                row++;
                if (row == bottom) right--;
            } else if(row == bottom && col > left) {
                /**
                 * fill bottom most unfilled row
                 */
                col--;
                if (col == left) bottom--;
            } else if(col == left && row > top) {
                /**
                 * fill left most unfilled column
                 */
                row--;
                if (row == top) left++;
            }
        }
        return res;
    }

    /**
     * Recommend this solution because it's easy to understand.
     * @param m
     * @param n
     * @param head
     * @return
     */
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];

        for (int[] arr: res) {
            Arrays.fill(arr, -1);
        }

        int rowBegin = 0, rowEnd = m - 1;
        int colBegin = 0, colEnd = n - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd && head != null) {
            /**
             * Traverse Right
             */
            for (int i = colBegin; i <= colEnd; i++, head = head.next) {
                if (head != null) {
                    res[rowBegin][i] = head.val;
                } else {
                    break;
                }
            }
            rowBegin++;
            /**
             * Traverse Down
             */
            for (int i = rowBegin; i <= rowEnd; i++, head = head.next) {
                if (head != null) {
                    res[i][colEnd] = head.val;
                } else {
                    break;
                }
            }
            colEnd--;
            /**
             * Traverse left
             */
            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i>= colBegin; i--, head = head.next) {
                    if (head != null) {
                        res[rowEnd][i] = head.val;
                    } else {
                        break;
                    }
                }
            }
            rowEnd--;
            /**
             * Traverse Up
             */
            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--, head = head.next) {
                    if (head != null) {
                        res[i][colBegin] = head.val;
                    } else {
                        break;
                    }
                }
            }
            colBegin++;
        }//end-while-loop

        return res;
    }


    private static class ListNode {
        int val;

        ListNode next;

        ListNode(){}

        ListNode(int x) {
            this.val = x;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }
}
