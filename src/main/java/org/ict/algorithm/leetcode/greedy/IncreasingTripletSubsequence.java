package org.ict.algorithm.leetcode.greedy;

/**
 * Given an integer array nums,
 * return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k].
 * If no such indices exists, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * Example 2:
 *
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 *
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 * @author sniper
 * @date 17 Jan, 2023
 * LC334, Medium
 */
public class IncreasingTripletSubsequence {

    public boolean increasingTripletV2(int[] nums) {
        return false;
    }

    public boolean increasingTripletV1(int[] nums) {
        return false;
    }

    /**
     * Understanding the following solution.
     * Time Complexity O(N)
     * Space Complexity O(1)
     * @author Simranjot Singh Sandhu
     * @see <a href="https://leetcode.com/problems/increasing-triplet-subsequence/solutions/79004/concise-java-solution-with-comments"></a>
     *
     * test case and comments provided by gcarrillo
     * e.g. nums = [1, 0, 2, 0, -1, 3]
     * i:0, m1 > 1 --> m1:1
     * i:1, m1 > 0 --> m1:0
     * i:2, m1 < 2 and m2 > 2 --> m2:2
     * i:3, m1 ==0, --> m1:0
     * i:4, m1 > -1, --> m1:-1
     * i:5, m1:-1, m2:2, 3 > m1 and 3 > m2, return true.
     * Although (2, -1, 3) not matches the nums[i] < nums[j] < nums[k] and i < j < k, but it also works.
     * Why? m2:2 means there existed a value that previously smaller than 2.
     * Now if you find any value greater than 2 that we know their exist in an increasing triplet sub-sequence.
     *
     * Although notice if we had a test case like this [1,0,2,0,-1,0,1],
     * we now could see the importance of the updated lower bound for first = -1,
     * so we can have a correct lower bound for second = 0, and note this answer ask for existence, not to construct the triplet,
     * as this solution wouldn't be able to in its current form.
     * e.g. nums = [1, 0, 2, 0, -1, 0, 1]
     * i:0, m1 > 1 --> m1:1
     * i:1, m1 > 0 --> m1:0
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        /**
         * start with two largest values,
         * as soon as we find a number bigger than both,
         * while both have been updated, return true.
         */
        int m1 = Integer.MAX_VALUE;
        int m2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (m1 >= num) {
                /**
                 * update m1 if num is smaller than both
                 */
                m1 = num;
            } else if (m2 >= num) {
                /**
                 * update big only if greater than m1 but smaller than m2
                 */
                m2 = num;
            } else {
                /**
                 * return if you find a number bigger than both m1 and m2.
                 */
                return true;
            }
        }
        return false;
    }
}
