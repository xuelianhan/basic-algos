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
     *
     * (a % k + k) takes care of the cases where a < 0
     *
     * @author lee215
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByKV5(int[] nums, int k) {
        /**
         * If k = 2, then the remainder is 0,1
         * If k = 5, then the remainder is 0,1,2,3,4
         * So a frequency array with length-k is just what we need.
         */
        int[] freq = new int[k];
        /**
         * Deal with first time "remainder == 0" situation.
         */
        freq[0] = 1;
        int prefix = 0;
        int res = 0;
        for (int a : nums) {
            prefix = (prefix + a % k + k) % k;
            /**
             * Store the frequency of each remainder.
             */
            res += freq[prefix]++;
        }
        return res;
    }




    /**
     * Understanding the following solution
     * @author CanDong
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByKV3(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        /**
         * Deal with first time "remainder == 0" situation.
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
            /**
             * Store the frequency of each remainder.
             */
            freq.put(sum, freq.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    /**
     * Understanding the following solution
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByKV4(int[] nums, int k) {
        int[] freq = new int[k];
        /**
         * Deal with first time "remainder == 0" situation.
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
            /**
             * Store the frequency of each remainder.
             */
            freq[remainder]++;
        }
        return res;
    }


    /**
     * Understanding the following solution
     * Frequency Array Solution
     * Time Complexity: O(N)
     * Space Complexity: O(K)
     *
     * @author GeorgeChryso
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/413234/whiteboard-explanation"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217980/java-o-n-with-hashmap-and-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217985/java-c-python-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217962/Java-Clean-O(n)-Number-Theory-+-Prefix-Sums/"></a>
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByKV2(int[] nums, int k) {
        int[] freq = new int[k];
        /**
         * Deal with first time "remainder == 0" situation.
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
            /**
             * Store the frequency of each remainder.
             */
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
     * i:0, sum = sum + a = 0 + 4, remainder:4 % 5 = 4, res=res+
     *
     *
     * @author GeorgeChryso
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/413234/whiteboard-explanation"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217980/java-o-n-with-hashmap-and-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217985/java-c-python-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217962/Java-Clean-O(n)-Number-Theory-+-Prefix-Sums/"></a>
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByKV1(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        /**
         * Deal with first time "remainder == 0" situation.
         * "remainder == 0" means the prefix sum of current array is divisible by k.
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
             * e.g. nums=[-9, -1, 5, 5], k:2
             */
            if(remainder < 0) {
                remainder += k;
            }
            /**
             * Store the frequency of each remainder.
             */
            res += freq.getOrDefault(remainder, 0);
            freq.put(remainder, freq.getOrDefault(remainder, 0) + 1);
        }
        return res;
    }

    /**
     * Logic:
     * I am already going to assume that you know about prefix sums before you read this.
     * We can all agree that for an array int[] A, where N = len(A), that there are N prefix sums.
     * prefix[0] = A[0], prefix[1] = A[0] + A[1], ... prefix[i] = A[0] + ... + A[i].
     *
     * Then to calculate how many sub-arrays are divisible by K is logically equivalent to saying,
     * how many ways can we pair up all prefix sum pairs (i,j) where i < j
     * such that (prefix[j] - prefix[i]) % K == 0.
     *
     * Just from that information alone we easily get a O(n^2) solution.
     * Compute all prefix sums, then check all pair to see if k divides the difference between them.
     *
     * However, if we just exploit some information w.r.t(With Regard To)
     * to the remainder of each prefix sum we can manipulate this into a linear algorithm.
     * Here's how.
     *
     * Number Theory Part
     * I noted above that we need to find all prefix sum pairs (i,j) such tha (p[j] - p[i]) % K == 0.
     * But this is only true, if and only if p[j] % K == p[i] % K
     * Why is this?
     *
     * According the division algorithm we can express p[j] and p[i] in the following way.
     * p[j] = bK + r0 where 0 <= r0 < K
     * p[i] = aK + r1 where 0<= r1 < K
     *
     * Then p[j] - p[i] = (b*K + r0) - (a*K + r1)
     * = b*K - a*K + r0 - r1 = K*(b-a) + r0 - r1
     * Again: p[j] - p[i] = K*(b-a) + (r0-r1), in other words
     * K only divides p[j] - p[i] iff r0-r1 = 0 <-> r0 = r1
     * QED(written or said after an argument to show that you have proved something that you wanted to prove)
     *
     * However, we should not forget about elements in the array that do not need a pairing,
     * namely those that are divisible by K.
     * That's why I add mod[0] at the end.
     * So counting pairs => N choose 2 = > n*(n-1) / 2.
     *
     * @author EddieCarrillo
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217962/Java-Clean-O(n)-Number-Theory-+-Prefix-Sums/"></a>
     * @param nums
     * @param k
     * @return
     */
    public int subArraysDivByK(int[] nums, int k) {
        int n = nums.length;
        /**
         * There are k mod groups:0,...,k-1
         */
        int[] modGroups = new int[k];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int group = sum % k;
            /**
             * Correct negative modulus
             */
            if (group < 0) {
                group += k;
            }
            modGroups[group]++;
        }

        int res = 0;
        for (int x : modGroups) {
            if (x > 1) {
                res += x * (x - 1) / 2;
            }
        }
        /**
         * Don't forget all numbers that divide K
         */
        res += modGroups[0];
        return res;
    }

}
