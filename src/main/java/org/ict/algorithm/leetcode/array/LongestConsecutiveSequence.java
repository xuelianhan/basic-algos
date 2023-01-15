package org.ict.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * @author sniper
 * @date 13 Jan, 2023
 * LC128, Medium
 */
public class LongestConsecutiveSequence {


    /**
     * Mathematic Solution.
     * @param nums
     * @return
     */
    public int longestConsecutiveV4(int[] nums) {
        //todo
        return 0;
    }

    /**
     * Union Find Solution
     * @param nums
     * @return
     */
    public int longestConsecutiveV3(int[] nums) {
        //todo
        return 0;
    }


    /**
     * HashSet Solution
     * @param nums
     * @return
     */
    public int longestConsecutiveV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        //
        return 0;
    }

    /**
     * Time Cost 35ms
     * HashMap Solution
     * @param nums
     * @return
     */
    public int longestConsecutiveV1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        /**
         * nums = [], so res is zero.
         */
        int res = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            int left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
            int right = map.containsKey(num + 1) ? map.get(num + 1) : 0;
            int sum = left + right + 1;
            map.put(num, sum);
            if (left > 0) {
                map.put(num - left, sum);
            }
            if (right > 0) {
                map.put(num + right, sum);
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * Time Cost 44ms
     * HashMap Solution
     *
     * e.g.nums = [100,100,4,200,1,3,2], expected 4
     * 100, left:0, right:0, sum:1, map.put(100, 1), res = max(0, 1) = 1, map.put(100, 1), map.put(100, 1)
     * 100, map.containsKey(100), continue.
     * 4, left:0, right:0, sum:1, map.put(4, 1), res = max(1, 1) = 1, map.put(4, 1), map.put(4, 1)
     * 200, left:0, right:0, sum:1, map.put(200, 1), res = max(1, 1) = 1, map.put(200, 1), map.put(200, 1)
     * 1, left:0, right:0, sum:1, map.put(1, 1), res = max(1, 1) = 1, map.put(1, 1), map.put(1, 1)
     * 3, left:0, right:1, sum:2, map.put(3, 2), res = max(1, 2) = 2, map.put(3, 2), map.put(4, 2)
     * 2, left:1, right:2, sum:4, map.put(2, 4), res = max(2, 4) = 4, map.put(1, 4), map.put(4, 4).
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        /**
         * nums = [], so res is zero.
         */
        int res = 0;
        for (int num : nums) {
           if (map.containsKey(num)) {
               continue;
           }
           int left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
           int right = map.containsKey(num + 1) ? map.get(num + 1) : 0;
           int sum = left + right + 1;
           map.put(num, sum);
           map.put(num - left, sum);
           map.put(num + right, sum);

           res = Math.max(res, sum);
        }
        return res;
    }
}
