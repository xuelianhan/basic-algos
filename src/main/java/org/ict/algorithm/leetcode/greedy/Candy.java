package org.ict.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * There are n children standing in a line.
 * Each child is assigned a rating value given in the integer array ratings.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 * Example 1:
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 *
 * Example 2:
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 *
 *
 * Constraints:
 * n == ratings.length
 * 1 <= n <= 2 * 10^4
 * 0 <= ratings[i] <= 2 * 10^4
 * @author sniper
 * @date 17 Jun 2023
 * LC135, Hard
 */
public class Candy {

    /**
     * Understanding the following solution
     * Time Cost 3ms
     * -----------------------------------
     * class Solution:
     *     def candy(self, ratings: List[int]) -> int:
     *         n = len(ratings)
     *         l, r = [1] * n, [1] * n
     *         for i in range(0, n - 1, 1):
     *             if ratings[i + 1] > ratings[i]:
     *                 l[i + 1] = l[i] + 1
     *         for i in range(n - 1, 0, -1):
     *             if ratings[i - 1] > ratings[i]:
     *                 r[i - 1] = r[i] + 1
     *         res = 0
     *         for a, b in zip(l, r):
     *             res += max(a, b)
     *         return res
     * ----------------------------------------------
     * class Solution {
     * public:
     *     int candy(vector<int>& ratings) {
     *         int n = ratings.size();
     *         vector<int> l(n, 1);
     *         vector<int> r(n, 1);
     *
     *         for (int i = 0; i < n - 1; i++) {
     *             if (ratings[i + 1] > ratings[i]) {
     *                 l[i + 1] = l[i] + 1;
     *             }
     *         }
     *
     *         for (int i = n - 1; i > 0; i--) {
     *             if (ratings[i - 1] > ratings[i]) {
     *                 r[i - 1] = r[i] + 1;
     *             }
     *         }
     *
     *         int res = 0;
     *         for (int i = 0; i < n; i++) {
     *             res += max(l[i], r[i]);
     *         }
     *         return res;
     *     }
     * };
     * ------------------------------------------
     * object Solution {
     *     def candy(ratings: Array[Int]): Int = {
     *         val n = ratings.length
     *         val l = Array.fill(n)(1)
     *         val r = Array.fill(n)(1)
     *
     *         for (i <- 1 until n) {
     *             if (ratings(i) > ratings(i - 1)) {
     *                 l(i) = l(i - 1) + 1
     *             }
     *         }
     *
     *         for (i <- n - 2 to 0 by -1) {
     *             if (ratings(i) > ratings(i + 1)) {
     *                 r(i) = r(i + 1) + 1
     *             }
     *         }
     *
     *         var res = 0
     *         for (i <- 0 until n) {
     *             res += math.max(l(i), r(i))
     *         }
     *         res
     *     }
     * }
     * -----------------------------------------
     * impl Solution {
     *     pub fn candy(ratings: Vec<i32>) -> i32 {
     *         let n = ratings.len();
     *         let mut l = vec![1; n];
     *         let mut r = vec![1; n];
     *
     *         for i in 1..n {
     *             if ratings[i] > ratings[i - 1] {
     *                 l[i] = l[i - 1] + 1;
     *             }
     *         }
     *
     *         for i in (0..n - 1).rev() {
     *             if ratings[i] > ratings[i + 1] {
     *                 r[i] = r[i + 1] + 1;
     *             }
     *         }
     *
     *         let mut res = 0;
     *         for i in 0..n {
     *             res += l[i].max(r[i]);
     *         }
     *         res
     *     }
     * }
     * @param ratings
     * @return
     */
    public int candyV1(int[] ratings) {
        int n = ratings.length;
        int[] l = new int[n];
        int[] r = new int[n];
        Arrays.fill(l, 1);
        Arrays.fill(r, 1);

        for (int i = 0; i < n - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                l[i + 1] = l[i] + 1;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {
                r[i - 1] = r[i] + 1;
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.max(l[i], r[i]);
        }
        return res;
    }

    /**
     * Understanding the following solution
     * Time Cost 3ms
     * ---------------------------
     * e.g. ratings = [1,0,2]
     * nums = [1, 1, 1]
     * for-loop-1: nums = [1, 1, 2]
     * for-loop-2: nums = [2, 1, 2]
     * ----------------------------
     * e.g. ratings = [1,2,2]
     * nums = [1, 1, 1]
     * for-loop-1: nums = [1, 2, 1]
     * for-loop-2: nums = [1, 2, 1]
     * ----------------------------
     * e.g. ratings = [2, 0, 1]
     * nums = [1, 1, 1]
     * for-loop-1: nums = [1, 1, 2]
     * for-loop-2: nums = [2, 1, 2]
     * ----------------------------
     * e.g. ratings = [1,3,4,5,2], expected 11
     * nums = [1, 1, 1, 1, 1]
     * for-loop-1: nums = [1, 2, 3, 4, 1]
     * for-loop-2: nums = [1, 2, 3, 4, 1]
     * -------------------------------------
     * class Solution:
     *     def candy(self, ratings: List[int]) -> int:
     *         n = len(ratings)
     *         nums = [1] * n
     *         for i in range(0, n - 1, 1):
     *             if ratings[i + 1] > ratings[i]:
     *                 nums[i + 1] = nums[i] + 1
     *         for i in range(n - 1, 0, -1):
     *             if ratings[i - 1] > ratings[i]:
     *                 nums[i - 1] = max(nums[i - 1], nums[i] + 1)
     *         res = 0
     *         for x in nums:
     *             res += x
     *         return res
     * -------------------------------------
     * class Solution {
     * public:
     *     int candy(vector<int>& ratings) {
     *         int n = ratings.size();
     *         vector<int> nums(n, 1);
     *
     *         for (int i = 0; i < n - 1; i++) {
     *             if (ratings[i + 1] > ratings[i]) {
     *                 nums[i + 1] = nums[i] + 1;
     *             }
     *         }
     *
     *         for (int i = n - 1; i > 0; i--) {
     *             if (ratings[i - 1] > ratings[i]) {
     *                 nums[i - 1] = max(nums[i - 1], nums[i] + 1);
     *             }
     *         }
     *
     *         int res = 0;
     *         for (int x : nums) {
     *             res += x;
     *         }
     *         return res;
     *     }
     * };
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int res = 0;
        int n = ratings.length;

        int[] nums = new int[n];
        Arrays.fill(nums, 1);

        for (int i = 0; i < n - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                nums[i + 1] = nums[i] + 1;
            }
        }
        for (int i = n - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {
                /**
                 * Notice max function here
                 * e.g. ratings = [1,3,4,5,2]
                 */
                nums[i - 1] = Math.max(nums[i - 1], nums[i] + 1);
            }
        }
        for (int num : nums) {
            res += num;
        }

        return res;
    }

}
