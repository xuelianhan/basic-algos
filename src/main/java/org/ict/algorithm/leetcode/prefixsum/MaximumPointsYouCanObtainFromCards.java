package org.ict.algorithm.leetcode.prefixsum;

import java.util.Arrays;

/**
 * There are several cards arranged in a row, and each card has an associated number of points.
 * The points are given in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row.
 * You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 * Example 1:
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1.
 * However, choosing the rightmost card first will maximize your total score.
 * The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 *
 * Example 2:
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 *
 * Example 3:
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 *
 * Constraints:
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 * @author sniper
 * @date 30 May 2023
 * LC1423, Medium, frequency=17
 */
public class MaximumPointsYouCanObtainFromCards {

    /**
     * Understanding the following solution
     * Combine maxScoreV1 and maxScoreV4.
     * --------------------------------------------------------------
     * impl Solution {
     *     pub fn max_score(card_points: Vec<i32>, k: i32) -> i32 {
     *         let (k, n) = (k as usize, card_points.len());
     *         let mut sum = card_points.iter().take(n - k).sum::<i32>();
     *         let mut min = sum;
     *         for i in 0..k {
     *             sum += card_points[n - k + i] - card_points[i];
     *             min = min.min(sum);
     *         }
     *         card_points.iter().sum::<i32>() - min
     *     }
     * }
     * --------------------------------------------------------------
     * class Solution:
     *     def maxScore(self, cardPoints: List[int], k: int) -> int:
     *         n = len(cardPoints)
     *         arrSum = sum(cardPoints)
     *         windowSum = sum(cardPoints[: n - k])
     *         t = windowSum
     *         for i in range(0, k):
     *             windowSum += cardPoints[n - k + i] - cardPoints[i]
     *             t = min(t, windowSum)
     *         return arrSum - t
     *---------------------------------------------------------------
     * class Solution {
     * public:
     *     int maxScore(vector<int>& cardPoints, int k) {
     *         const int n = cardPoints.size();
     *         const int sum = accumulate(begin(cardPoints), end(cardPoints), 0);
     *         int windowSum = accumulate(begin(cardPoints), begin(cardPoints) + n - k, 0);
     *         int t = windowSum;
     *         for (int i = 0; i < k; i++) {
     *             windowSum += cardPoints[n - k + i] - cardPoints[i];
     *             t = min(t, windowSum);
     *         }
     *         return sum - t;
     *     }
     * };
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScoreV5(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int sum = Arrays.stream(cardPoints).sum();
        int windowSum = 0;
        for (int i = 0; i < n - k; i++) {
            windowSum += cardPoints[i];
        }
        int min = windowSum;
        for (int i = 0; i < k; i++) {
            windowSum += cardPoints[n - k + i] - cardPoints[i];
            min = Math.min(min, windowSum);
        }
        return sum - min;
    }

    /**
     * Understanding the following solution
     * Similar with maxScoreV3
     * Time Cost 2ms
     *       car
     * [______________]
     * ------------------------
     * ^              ^       ^
     * 1              2       3
     *
     * length of 2_3 is k
     * length of 1_2 is n-k
     * (n - k + i - 1 < n) means the car's index can only go from 2 to 3
     * (prefix[n - k + i] - prefix[i]) means the sum of car body
     * ---------------------------------------------------------
     * class Solution:
     *     def maxScore(self, cardPoints: List[int], k: int) -> int:
     *         n = len(cardPoints)
     *         prefix = [0] * (n + 1)
     *         for i in range(0, n):
     *             prefix[i + 1] = prefix[i] + cardPoints[i]
     *         t = inf
     *         for i in range(0, n):
     *             if (n - k + i - 1 < n):
     *                 t = min(t, prefix[n - k + i] - prefix[i])
     *         return prefix[n] - t
     * ------------------------------------------------------------
     * class Solution {
     * public:
     *     int maxScore(vector<int>& cardPoints, int k) {
     *         const int n = cardPoints.size();
     *         vector<int> prefix(n + 1);
     *         for (int i = 0; i < n; i++) {
     *             prefix[i + 1] = prefix[i] + cardPoints[i];
     *         }
     *         int t = INT_MAX;
     *         for (int i = 0; i < n; i++) {
     *             if (n - k + i - 1 < n) {
     *                 t = min(t, prefix[n - k + i] - prefix[i]);
     *             }
     *         }
     *         return prefix[n] - t;
     *     }
     * };
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScoreV4(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + cardPoints[i];
        }
        int t = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (n - k + i - 1 < n) {
                t = Math.min(t, prefix[n - k + i] - prefix[i]);
            }
        }
        return prefix[n] - t;
    }

    /**
     * Understanding the following solution
     * Time Cost 3ms
     * e.g. Assume the length of cardPoints(call it A for short) is 5, k is 2
     * So the prefix of length is n + 1 = 5 + 1 = 6.
     * prefix[0] = 0
     * prefix[1] = prefix[0] + A[0] = A[0]
     * prefix[2] = prefix[1] + A[1] = A[0] + A[1]
     * prefix[3] = prefix[2] + A[2] = A[0] + A[1] + A[2]
     * prefix[4] = prefix[3] + A[3] = A[0] + A[1] + A[2] + A[3]
     * prefix[5] = prefix[4] + A[4] = A[0] + A[1] + A[2] + A[3] + A[4]
     * ---------------------------------------------
     * n:5, j = n - k - 1 + i,
     * i:0, j = 5 - 2 - 1 + 0 = 2, t = min(t, prefix[3] - prefix[0]) = min(t, A[0] + A[1] + A[2])
     * i:1, j = 5 - 2 - 1 + 1 = 3, t = min(t, prefix[4] - prefix[1]) = min(t, A[1] + A[2] + A[3])
     * i:2, j = 5 - 2 - 1 + 2 = 4, t = min(t, prefix[5] - prefix[2]) = min(t, A[2] + A[3] + A[4])
     * [_____________]
     * -------------------------------
     *
     *            [_____________]
     * -------------------------------
     *  [_____________] is prefix[j + 1] - prefix[i] ,
     *  we need to find the maximum of the rest part
     *  (The remaining part of the long dashed line after removing the closed interval)
     *
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScoreV3(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + cardPoints[i];
        }
        int t = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int j = n - k - 1 + i;
            if (j < n) {
                /**
                 * Notice here is prefix[j + 1] - prefix[i], not prefix[j + 1] - prefix[j]
                 */
                t = Math.min(t, prefix[j + 1] - prefix[i]);
            }
        }
        return prefix[n] - t;
    }

    /**
     * Time Cost 6ms
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScoreV2(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int sum = Arrays.stream(cardPoints).sum();
        /**
         * If k >= n, windowSum:0, res = sum - 0 = sum
         * The second for-loop will do invalid calculations.
         * So we can tune this by adding condition.
         */
        int windowSum = 0;
        for (int i = 0; i < n - k; i++) {
            windowSum += cardPoints[i];
        }

        int res = sum - windowSum;
        /**
         * Notice the condition k < n here only for tuning.
         * If you remove this condition, it won't affect the final result.
         */
        for (int i = 0; i < k && k < n; i++) {
            windowSum -= cardPoints[i];
            windowSum += cardPoints[n - k + i];
            res = Math.max(res, sum - windowSum);
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Time Cost 6ms
     * ---------------------------------------------
     * class Solution:
     *     def maxScore(self, cardPoints: List[int], k: int) -> int:
     *         n = len(cardPoints)
     *
     *         arrSum = sum(cardPoints)
     *         windowSum = sum(cardPoints[: n - k])
     *         res = arrSum - windowSum
     *         for i in range(k):
     *             windowSum -= cardPoints[i]
     *             windowSum += cardPoints[n - k + i]
     *             res = max(res, arrSum - windowSum)
     *         return res
     * ----------------------------------------------
     * class Solution {
     * public:
     *     int maxScore(vector<int>& cardPoints, int k) {
     *         const int n = cardPoints.size();
     *         const int sum = accumulate(begin(cardPoints), end(cardPoints), 0);
     *         int windowSum = accumulate(begin(cardPoints), begin(cardPoints) + n - k, 0);
     *         int res = sum - windowSum;
     *         for (int i = 0; i < k; i++) {
     *             windowSum -= cardPoints[i];
     *             windowSum += cardPoints[n - k + i];
     *             res = max(res, sum - windowSum);
     *         }
     *         return res;
     *     }
     * };
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScoreV1(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int sum = Arrays.stream(cardPoints).sum();
        /**
         * If k >= n, windowSum:0, res = sum - 0 = sum
         * The second for-loop will do invalid calculations.
         * So we can tune this by adding condition.
         */
        int windowSum = 0;
        for (int i = 0; i < n - k; i++) {
            windowSum += cardPoints[i];
        }

        int res = sum - windowSum;
        for (int i = 0; i < k; i++) {
            windowSum -= cardPoints[i];
            windowSum += cardPoints[n - k + i];
            res = Math.max(res, sum - windowSum);
        }
        return res;
    }

    /**
     * Time Cost 6ms
     * e.g. cardPoints = [1,79,80,1,1,1,200,1], k = 3, expected 202
     * a[7] + a[[6] + a[5] = 202, or a[7] + a[6] + a[0] = 202
     *
     * e.g. cardPoints = [100,40,17,9,73,75], k = 3, expected 248
     * a[0] + a[5] + a[4] = 248
     *
     *        [_________]
     * -------------------------
     *
     * [_________]
     * -------------------------
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int sum = Arrays.stream(cardPoints).sum();;
        if (k >= n) {
            return sum;
        }
        /**
         * The window size is n - k + 1
         */
        int windowSum = 0;
        for (int i = 0; i < n - k; i++) {
            windowSum += cardPoints[i];
        }
        /**
         * res being initialized as the last-k sum.
         * e.g. n = 8, k = 3
         * windowSum = a[0] + a[1] + a[2] + a[3] + a[4]
         * sum - windowSum = a[5] + a[6] + a[7]
         */
        int res = sum - windowSum;

        /**
         * Slide the window k times
         * e.g. n = 8, k = 3
         * sum = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7]
         * res = a[5] + a[6] + a[7]
         * windowSum = a[0] + a[1] + a[2] + a[3] + a[4]
         * i:0, windowSum = a[1] + a[2] + a[3] + a[4] + a[5], res = max(res, a[0] + [a6] + a[7])
         * i:1, windowSum = a[2] + a[3] + a[4] + a[5] + a[6], res = max(res, a[0] + [a1] + a[7])
         * i:2, windowSum = a[3] + a[4] + a[5] + a[6] + a[7], res = max(res, a[0] + [a1] + a[2])
         */
        for (int i = 0; i < k; i++) {
            windowSum -= cardPoints[i];
            windowSum += cardPoints[i + n - k];
            res = Math.max(res, sum - windowSum);
        }
        return res;
    }

    public int wrong(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int sum = 0;
        if (k >= n) {
            sum = Arrays.stream(cardPoints).sum();
            return sum;
        }
        int i = 0;
        int j = n - 1;
        int res = 0;
        for (; i <= j; k--) {
            if (k == 0) {
                break;
            }
            if (cardPoints[i] > cardPoints[j]) {
                //In this case, choose first or choose last?
                //You cannot decide which side is the best.
            } else if (cardPoints[i] < cardPoints[j]){
                //In this case, choose first or choose last?
                //You cannot decide which side is the best.
            } else {
                //In this case, choose first or choose last?
                //You cannot decide which side is the best.
            }
        }
        return res;
    }


}
