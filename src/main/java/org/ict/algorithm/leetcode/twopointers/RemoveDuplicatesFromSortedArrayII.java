package org.ict.algorithm.leetcode.twopointers;

/**
 * Given an integer array nums sorted in non-decreasing order,
 * remove some duplicates in-place such that each unique element appears at most twice.
 * The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 * Do not allocate extra space for another array.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Custom Judge:
 * The judge will test your solution with the following code:
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 *
 * int k = removeDuplicates(nums); // Calls your implementation
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5,
 * with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Example 2:
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7,
 * with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in non-decreasing order.
 * @author sniper
 * @date 16 Jun 2023
 * LC80, Medium
 */
public class RemoveDuplicatesFromSortedArrayII {


    /**
     * Understanding the following solution
     * e.g. nums = [1,1,1,2,2,3]
     * i:0, num:1, i < 2, nums[0] = 1, nums:[1,1,1,2,2,3], i++
     * i:1, num:1, i < 2, nums[1] = 1, nums:[1,1,1,2,2,3], i++
     * i:2, num:1, num == nums[2], don't change anything, nums:[1,1,1,2,2,3], i don't increment
     * i:2, num:2, num > nums[0]:1, nums[2] = 2, nums:[1,1,2,2,2,3], i++
     * i:3, num:2, num > nums[1]:1, nums[3] = 2, nums:[1,1,2,2,2,3], i++
     * i:4, num:3, num > nums[2]:2, nums[4] = 3, nums:[1,1,2,2,3,3], i++
     * i:5, return i:5
     * ---------------------------
     * class Solution:
     *     def removeDuplicates(self, nums: List[int]) -> int:
     *         i = 0
     *         for num in nums:
     *             if i < 2 or num > nums[i - 2]:
     *                 nums[i] = num
     *                 i += 1
     *         return i
     * ----------------------------
     * impl Solution {
     *     pub fn remove_duplicates(nums: &mut Vec<i32>) -> i32 {
     *         let mut k = 0;
     *         for i in 0..nums.len() {
     *             if k < 2 || nums[i] > nums[k - 2] {
     *                 nums[k] = nums[i];
     *                 k += 1
     *             }
     *         }
     *         k as i32
     *     }
     * }
     * -----------------------
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i - 2]) {
                /**
                 * Notice i++ in this if-condition
                 */
                nums[i++] = num;
            }
        }
        return i;
    }
}
