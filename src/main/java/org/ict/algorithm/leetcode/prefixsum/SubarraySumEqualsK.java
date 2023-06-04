package org.ict.algorithm.leetcode.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array of integers nums and an integer k,
 * return the total number of sub-arrays whose sum equals to k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 * @author sniper
 * @date 12 Apr, 2023
 * LC560, Medium, frequency=185
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        SubarraySumEqualsK instance = new SubarraySumEqualsK();
        int res = instance.subarraySumV1(nums, k);
        System.out.println(res);
    }


    /**
     * Understanding the following solution.
     *
     * We use hash map to store the cumulative sum, sum[i] up to index-i,
     * if sum[i]−sum[j]=k, then it means the sum between indices i and j is k
     * Therefore, we store the cumulative sum, search for sum−k
     * in the hash map and add the occurrences if it is found to the answer.
     *
     * e.g. nums = [1,2,3], k = 3, expected 2
     * map:{(0, 1)}
     * i:0, sum:1, sum-k=-2, map not contains -2, res:0, map:{(1, 1)},
     * i:1, sum:3, sum-k= 0, map contains 0, res:1, map:{(1, 1), (3, 1)}, it means sum of range:[0, i] is k
     * i:2, sum:6, sum-k= 3, map contains 3, res:2, map:{(1, 1), (3, 1), (6, 1)}
     *
     * e.g. nums = [1], k = 0, expected 0, if you use res + 1 instead of putting (0, 1), you will get the wrong answer 1.
     *
     * def subarraySum(self, nums: List[int], k: int) -> int:
     *         sum = 0
     *         res = 0
     *         dict = {0:1}
     *         for x in nums:
     *             sum += x
     *             if sum - k in dict:
     *                 res += dict[sum - k]
     *             if sum not in dict:
     *                 dict[sum] = 1
     *             else:
     *                 dict[sum] += 1
     *         return res
     * ---------------------------------------------
     * e.g. nums = [1,1,1], k = 2, expected 2
     * map:{(0, 1)}
     * i:0, sum:1, sum-k=-1, map not contains -1, map:{(0, 1), (1, 1)}
     * i:1, sum:2, sum-k= 0, map contains 0, res = res + 1 = 1, map:{(0, 1), (1, 1), (2, 1)}
     * i:2, sum:3, sum-k= 1, map contains 1, res = res + 1 = 2, map:{(0, 1), (1, 1), (2, 1), (3, 1)}
     * ----------|-----|
     *           ^     ^
     *           k    sum
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySumV1(int[] nums, int k) {
        int sum = 0;
        int res = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        /**
         * Assume that prefix sum equals k at index-i, the range from index-0 to this index-i is an answer.
         * So while sum == k, the sum-k is 0, the value should be 1.
         * e.g. nums = [1, 2, 3], k = 3
         * while 1 + 2 = 3, i:1, so range from index-0 to index-1 should be an answer.
         */
        prefixSum.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (prefixSum.containsKey(sum - k)) {
                res += prefixSum.get(sum - k);
            }
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }


    /**
     * Time Cost 1991ms, very slow.
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (prefix[i] == k) {
                res++;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (prefix[i] - prefix[j] == k) {
                    res++;
                }
            }
        }
        return res;
    }
}
