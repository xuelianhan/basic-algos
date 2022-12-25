package org.ict.algorithm.leetcode.prefixsum;

import java.util.Arrays;

/**
 * You are given an integer array nums of length n, and an integer array queries of length m.
 *
 * Return an array answer of length m where answer[i] is
 * the maximum size of a subsequence
 * that you can take from nums such that
 * the sum of its elements is less than or equal to queries[i].
 *
 * A subsequence is an array that can be derived from another array
 * by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,2,1], queries = [3,10,21]
 * Output: [2,3,4]
 * Explanation: We answer the queries as follows:
 * - The subsequence [2,1] has a sum less than or equal to 3.
 * It can be proven that 2 is the maximum size of such a subsequence, so answer[0] = 2.
 * - The subsequence [4,5,1] has a sum less than or equal to 10.
 * It can be proven that 3 is the maximum size of such a subsequence, so answer[1] = 3.
 * - The subsequence [4,5,2,1] has a sum less than or equal to 21.
 * It can be proven that 4 is the maximum size of such a subsequence, so answer[2] = 4.
 * Example 2:
 *
 * Input: nums = [2,3,4,5], queries = [1]
 * Output: [0]
 * Explanation: The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 1000
 * 1 <= nums[i], queries[i] <= 10^6
 * @author sniper
 * @date 25 Dec, 2022
 * LC2389
 */
public class LongestSubsequenceWithLimitedSum {

    public static void main(String[] args) {
        int[] nums = {4,5,2,1};
        int[] queries = {3,10,21};

        LongestSubsequenceWithLimitedSum instance = new LongestSubsequenceWithLimitedSum();
        int[] res = instance.answerQueries(nums, queries);
        System.out.println(Arrays.toString(res));
    }

    /**
     * Time Cost 4ms
     * @author lee215
     * @param nums
     * @param queries
     * @return
     */
    public int[] answerQueriesV3(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        for (int i = 0; i < queries.length; i++) {
            int pos = Arrays.binarySearch(nums, queries[i]);
            queries[i] = Math.abs(pos + 1);
        }
        return queries;
    }

    /**
     * Sort input nums, since we care the sum and doesn't care the order.
     * Calukate the prefix sum of nums, since we want to know the accumulate sum.
     * Binary seach each query q in query, and the index is the result.
     * return result res.
     * Time O(mlogn + n)
     * Space O(n)
     * n = nums.size(), m = queries.size()
     * @see <a href="https://leetcode.com/problems/longest-subsequence-with-limited-sum/solutions/2493409/java-c-python-binary-search"></a>
     * @author lee215
     * @param nums
     * @param queries
     * @return
     */
    public int[] answerQueriesV2(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        int m = queries.length;
        int[] res = new int[m];

        for (int i = 1; i < n; ++i) {
            nums[i] += nums[i - 1];
        }

        for (int i = 0; i < m; ++i) {
            /**
             * binarySearch return the index of the search key,
             * if it is contained in the array;
             *
             * otherwise, <tt>(-(<i>insertion point</i>) - 1)</tt>.
             * The <i>insertion point</i> is defined as the point at which the
             * key would be inserted into the array:
             * the index of the first element greater than the key,
             * or <tt>a.length</tt> if all elements in the array are less than the specified key.
             * Note that this guarantees that the return value will be &gt;= 0 if and only if the key is found.
             */
            int j = Arrays.binarySearch(nums, queries[i]);
            res[i] = Math.abs(j + 1);
        }
        return res;
    }

    /**
     * Time Cost 8ms
     * The minimal sum of any k elements of the array is the sum of k smallest elements.
     *
     * So, we sort the array first.
     * Then, we apply prefix sum so that the array i-th element of the array contains the minimal sum of i elements.
     *
     * Finally, we use a binary search to find the maximum number of elements for each query.
     *
     * @see <a href="https://leetcode.com/problems/longest-subsequence-with-limited-sum/solutions/2492737/prefix-sum"></a>
     * @author votrubac
     * @param nums
     * @param queries
     * @return
     */
    public int[] answerQueriesV1(int[] nums, int[] queries) {
        Arrays.sort(nums);
        Arrays.parallelPrefix(nums, Integer::sum);
        for (int i = 0; i < queries.length; i++) {
            int pos = Arrays.binarySearch(nums, queries[i] + 1);
            /**
             * Java's binary search function is a weird hybrid of lower and upper bound.
             * If the element n exists in the array, it returns it's index.
             * Otherwise, it returns -i - 1, where i is the 'insertion point' or,
             * in other words, an index of the first element larger than n.
             * I guess it negates the result so we can distinguish between two cases.
             * It also subtract 1 to handle the zero index.
             * Pretty complicated, IMHO.
             * The ~i operator is the same as doing -i - 1 while i is negative.
             * It's called bitwise compliment,
             * and used in this case (handling binary search results) by convention.
             */
            queries[i] = pos < 0 ? ~pos : pos;
        }
        return queries;
    }


    /**
     * Time Cost 6ms
     * @param nums
     * @param queries
     * @return
     */
    public int[] answerQueries(int[] nums, int[] queries) {
        int m = queries.length;
        int n = nums.length;
        int[] res = new int[m];
        /**
         * Sort nums first.
         */
        Arrays.sort(nums);

        /**
         * Calculate prefix sum array.
         */
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        for (int i = 0; i < m; i++) {
            /**
             * Find the index of first element greater or equal to queries[i].
             */
            int k = 0;
            while (k < n && prefix[k] < queries[i]) {
                k++;
            }
            if (k == n) {
                res[i] = n;
                continue;
            }
            /**
             * It requires that the sum of its elements is less than or equal to queries[i],
             * so we need to check whether prefix sum greater than queries[i] or not,
             * if greater than queries[i] we need to backward pointer k one step.
             */
            if (prefix[k] > queries[i]) {
                k--;
            }
            /**
             * k is an index, the final distance need to plus one more.
             */
            res[i] = k + 1;
        }
        return res;
    }
}
