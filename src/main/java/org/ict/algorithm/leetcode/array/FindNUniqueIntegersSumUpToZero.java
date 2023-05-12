package org.ict.algorithm.leetcode.array;

/**
 * Given an integer n,
 * return any array containing n unique integers such that they add up to 0.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5
 * Output: [-7,-1,1,3,4]
 * Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
 * Example 2:
 *
 * Input: n = 3
 * Output: [-1,0,1]
 * Example 3:
 *
 * Input: n = 1
 * Output: [0]
 *
 *
 * Constraints:
 * 1 <= n <= 1000
 * @author sniper
 * @date 12 May 2023
 * LC1304, Easy, frequency=22
 */
public class FindNUniqueIntegersSumUpToZero {

    /**
     * Understanding the following solution
     * ------------------------
     * class Solution:
     *     def sumZero(self, n: int) -> List[int]:
     *         res = [0] * n
     *         for i in range(1, n):
     *             res[i] = i
     *             res[0] -= i
     *         return res
     * -------------------------
     * class Solution {
     * public:
     *     vector<int> sumZero(int n) {
     *         vector<int> res(n);
     *         for (int i = 1; i < n; i++) {
     *             res[i] = i;
     *             res[0] -= i;
     *         }
     *         return res;
     *     }
     * };
     * @param n
     * @return
     */
    public int[] sumZeroV3(int n) {
        int[] res = new int[n];
        for (int i = 1; i < n; i++) {
            res[i] = i;
            res[0] -= i;
        }
        return res;
    }

    /**
     * Understanding the following solution
     *
     * class Solution:
     *     def sumZero(self, n: int) -> List[int]:
     *         res = [0] * n
     *         for i in range(0, n // 2):
     *             res[i] = -n + i
     *             res[n - i - 1] = n - i
     *         if n % 2 != 0:
     *             res[n // 2] = 0
     *         return res
     * -----------------------------
     * class Solution {
     * public:
     *     vector<int> sumZero(int n) {
     *         vector<int> res(n);
     *         for (int i = 0; i < n / 2; i++) {
     *             res[i] = -n - i;
     *             res[n - 1 - i] = n + i;
     *         }
     *         if (n % 2 != 0) {
     *             res[n / 2] = 0;
     *         }
     *         return res;
     *     }
     * };
     * @param n
     * @return
     */
    public int[] sumZeroV2(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n / 2; i++) {
            res[i] = -n + i;
            res[n - 1 - i] = n - i;
        }
        if (n % 2 != 0) {
            res[n / 2] = 0;
        }
        return res;
    }

    /**
     * Understanding the following solution
     * @param n
     * @return
     */
    public int[] sumZeroV1(int n) {
        if (n == 1) {
            return new int[] {0};
        }
        int[] res = new int[n];
        for (int i = 0; i < n / 2; i++) {
            res[i] = -n + i;
            res[n - 1 - i] = n - i;
        }
        if (n % 2 != 0) {
            res[n / 2] = 0;
        }
        return res;
    }

    public int[] sumZero(int n) {
        if (n == 1) {
            return new int[] {0};
        }
        int[] res = new int[n];
        if (n % 2 == 0) {
            for (int i = 0; i < n / 2; i++) {
                res[i] = -n + i;
                res[n - 1 - i] = n - i;
            }
        } else {
            for (int i = 0; i < n / 2; i++) {
                res[i] = -n + i;
                res[n - 1 - i] = n - i;
            }
            res[n / 2] = 0;
        }
        return res;
    }
}
