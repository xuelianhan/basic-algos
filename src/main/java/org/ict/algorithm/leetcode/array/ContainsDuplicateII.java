package org.ict.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an integer array nums and an integer k,
 * return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 0 <= k <= 10^5
 * @author sniper
 * @date 15 Oct, 2022
 * LC219
 */
public class ContainsDuplicateII {

    /**
     * Understand the following solution
     * Sliding Window Solution with HashSet
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicateV2(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            /**
             * remove element if its distance to nums[i] is not lesser than k
             */
            if(i > k) {
                set.remove(nums[i-k-1]);
            }
            /**
             * because all still existed elements is closer than k distance to the num[i],
             * therefore if the add() return false,
             * it means there's a same value element already existed within the distance k,
             * therefore return true.
             */
            if(!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Understand the following solution.
     *
     * Sliding Window Solution with HashMap
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicateV1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
           if (map.containsKey(nums[i])) {
                int previous = map.get(nums[i]);
                if ((i - previous) <= k) {
                    return true;
                }
           }
           map.put(nums[i], i);
        }
        return false;
    }


    /**
     * Brute Force Solution
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
           for (int j = i + 1;  j < nums.length && j <= (i + k); j++) {
               if (nums[i] == nums[j]) {
                   return true;
               }
           }
        }
        return false;
    }
}
