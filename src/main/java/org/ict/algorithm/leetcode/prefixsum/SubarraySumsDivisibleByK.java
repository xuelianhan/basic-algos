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
public class SubarraySumsDivisibleByK {


    /**
     * Time Complexity: O(N)
     * Space Complexity: O(K)
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/413234/whiteboard-explanation"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217980/java-o-n-with-hashmap-and-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217985/java-c-python-prefix-sum"></a>
     * @see <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/solutions/217962/Java-Clean-O(n)-Number-Theory-+-Prefix-Sums/"></a>
     * @param nums
     * @param k
     * @return
     */
    public int subarraysDivByKV1(int[] nums, int k) {
        int[] map = new int[k];
        /**
         * Deal with "remainder = 0" situation.
         */
        map[0] = 1;
        int count = 0;
        int sum = 0;
        for(int a : nums) {
            sum = (sum + a) % k;
            /**
             * e.g. -1 % 5 = -1, but we need the positive mod 4
             */
            if(sum < 0) {
                sum += k;
            }
            count += map[sum];
            map[sum]++;
        }
        return count;
    }

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(K)
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
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        /**
         * Deal with "remainder = 0" situation.
         */
        map.put(0, 1);
        int count = 0;
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
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
