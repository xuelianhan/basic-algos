package org.ict.algorithm.leetcode.prefixsum;

/**
 * Given an integer array nums, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right)
 * Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 *
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 * numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * 0 <= left <= right < nums.length
 * At most 10^4 calls will be made to sumRange.
 * @author sniper
 * @date 27 Aug 2023
 * LC303, Easy
 */
public class RangeSumQueryImmutable {

    /**
     * Time Cost 8ms
     */
    static class NumArray {
        private int[] prefix;

        /**
         * e.g.
         * nums = [1, 2, 3]
         * prefix = [0, 1, 3, 6]
         *           0  1  2  3
         * @param nums
         */
        public NumArray(int[] nums) {
            int n = nums.length;
            prefix = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
        }

        /**
         * e.g. left:1, right:2
         * nums[1] + nums[2] = 2 + 3
         * 5 = prefix[3] - prefix[1] = 6 - 1
         * So this is the formula:
         * 5 = prefix[right + 1] - prefix[left]
         *
         * @param left
         * @param right
         * @return
         */
        public int sumRange(int left, int right) {
            return prefix[right + 1] - prefix[left];
        }
    }
    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
}
