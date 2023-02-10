package org.ict.algorithm.leetcode.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * Example 2:
 *
 * Input: nums = [5], k = 9
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * 2 <= k <= 10^4
 *
 * @author sniper
 * @date 20 Jan, 2023
 * LC974, Medium
 */
public class SubArraySumsDivisibleByK {

    /**
     * Understanding the following solution
     * @author lee215
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByKV4(int[] nums, int k) {
        int[] freq = new int[k];
        freq[0] = 1;
        int prefix = 0;
        int res = 0;
        for (int a : nums) {
            prefix = (prefix + a % k + k) % k;
            res += freq[prefix]++;
        }
        return res;
    }


    /**
     * Understanding the following solution
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByKV3(int[] nums, int k) {
        int[] freq = new int[k];
        /**
         * Deal with "remainder = 0" situation.
         */
        freq[0] = 1;
        int res = 0;
        int sum = 0;
        for(int a : nums) {
            sum += a;
            int remainder = sum % k;
            /**
             * e.g. -1 % 5 = -1, but we need the positive mod 4
             */
            if(remainder < 0) {
                remainder += k;
            }
            res += freq[remainder];
            freq[remainder]++;
        }
        return res;
    }

    /**
     * Understanding the following solution
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByKV2(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        /**
         * Deal with "remainder = 0" situation.
         */
        freq.put(0, 1);
        int res = 0;
        int sum = 0;
        for(int a : nums) {
            sum = (sum + a) % k;
            /**
             * e.g. -1 % 5 = -1, but we need the positive mod 4
             * This make mod always positive.
             */
            if(sum < 0) {
                sum += k;
            }
            res += freq.getOrDefault(sum, 0);
            freq.put(sum, freq.getOrDefault(sum, 0) + 1);
        }
        return res;
    }


    /**
     * Understanding the following solution
     * Frequency Array Solution
     * Time Complexity: O(N)
     * Space Complexity: O(K)
     *
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/413234/whiteboard-explanation"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217980/java-o-n-with-hashmap-and-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217985/java-c-python-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217962/Java-Clean-O(n)-Number-Theory-+-Prefix-Sums/"></a>
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByKV1(int[] nums, int k) {
        int[] freq = new int[k];
        /**
         * Deal with "remainder = 0" situation.
         */
        freq[0] = 1;
        int res = 0;
        int sum = 0;
        for(int a : nums) {
            sum += a;
            int remainder = sum % k;
            /**
             * e.g. -1 % 5 = -1, but we need the positive mod 4
             */
            if(remainder < 0) {
                remainder += k;
            }
            res += freq[remainder];
            freq[remainder]++;
        }
        return res;
    }

    /**
     * Understanding the following solution
     * HashMap Solution
     * Time Complexity: O(N)
     * Space Complexity: O(K)
     *
     * Suppose i < j, and
     * Si = a0 + a1 + a2 + ... + ai;
     * Sj = a0 + a1 + a2 + ... + aj;
     * Ri = Si mod k;
     * Rj = Sj mod k;
     * If Ri == Rj, then sum of sub-array from i to j must have a reminder of zero.
     * Formula: (a + b) % k = (a % k + b % k) % k;
     * e.g.(1 + 2) % 5 = (1 % 5 + 2 % 5) % 5 = 3
     * Proof:
     * If Si mod k == Sj mod k, then
     * (Sj - Si) mod k = (Sj % k - Si % k) % k = 0 % k = 0.
     *
     *
     * For example, nums = [1, 2, 3, 4, 5], k = 5
     * S0 = 1, S2 = 1 + 2 + 3 = 6
     * S0 mod 5 == S2 mod 5 == 1, so [2, 3] sum = 2 + 3 = 5, it can be divided by 5 with a reminder of 0.
     *
     * e.g.nums = [4,5,0,-2,-3,1], k = 5
     *
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/413234/whiteboard-explanation"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217980/java-o-n-with-hashmap-and-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217985/java-c-python-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217962/Java-Clean-O(n)-Number-Theory-+-Prefix-Sums/"></a>
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        /**
         * Deal with "remainder = 0" situation.
         */
        freq.put(0, 1);
        int res = 0;
        int sum = 0;
        for(int a : nums) {
            sum = sum + a;
            int remainder = sum % k;
            /**
             * e.g. -1 % 5 = -1, but we need the positive mod 4
             * This make mod always positive.
             */
            if(remainder < 0) {
                remainder += k;
            }
            res += freq.getOrDefault(remainder, 0);
            freq.put(remainder, freq.getOrDefault(remainder, 0) + 1);
        }
        return res;
    }

}
