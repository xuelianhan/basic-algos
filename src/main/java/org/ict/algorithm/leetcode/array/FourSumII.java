package org.ict.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
 *
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * Output: 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * Example 2:
 *
 * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 * @author sniper
 * @date 04 Jan, 2023
 */
public class FourSumII {

    /**
     * Time Cost 203ms
     * Using two hash maps to store the sums a + b, and -(c + d),
     * finally multiply the values at the end.
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCountV2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                int sum1 = (a + b);
                map1.put(sum1, map1.getOrDefault(sum1, 0) + 1);
            }
        }
        for (int c : nums3) {
            for (int d : nums4) {
                int sum2 = - (c + d);
                map2.put(sum2, map2.getOrDefault(sum2, 0) + 1);
            }
        }
        /**
         * a + b = - (c + d)
         */
        int res = 0;
        for (int key : map1.keySet()) {
            int cnt2 = map2.getOrDefault(key, 0);
            if (cnt2 == 0) {
                continue;
            }
            int cnt1 = map1.get(key);
            res += cnt1 * cnt2;
        }
        return res;
    }

    /**
     * Time Cost 155ms
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCountV1(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                map.put((a + b), map.getOrDefault(a + b, 0) + 1);
            }
        }

        for (int c : nums3) {
            for (int d : nums4) {
                int sum = -(c + d);
                if (map.containsKey(sum)) {
                    res += map.get(sum);
                }
            }
        }
        return res;
    }

    /**
     * Time Cost 314ms
     * Time Complexity O(N^2)
     *
     * a + b + c + d = 0 is same as a + b = - (c + d)
     * First, we iterate nums1 and nums2 to count the sum of elements a + b,
     * then we iterate nums3 and nums4 to find if the complementary sum - (c + d).
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                map.put((a + b), map.getOrDefault(a + b, 0) + 1);
            }
        }

        for (int c : nums3) {
            for (int d : nums4) {
                if (map.getOrDefault(-(c + d), 0) > 0) {
                    res += map.get(-(c + d));
                }
            }
        }
        return res;
    }
}
