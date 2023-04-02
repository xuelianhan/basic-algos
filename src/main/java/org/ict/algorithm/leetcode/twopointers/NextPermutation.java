package org.ict.algorithm.leetcode.twopointers;

import java.util.Arrays;

/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order,
 * then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * @author sniper
 * @date 01 Apr, 2023
 * LC31, Medium
 */
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 4, 3, 1};
        System.out.println(Arrays.toString(nums));
        NextPermutation instance = new NextPermutation();
        instance.nextPermutationV1(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * Understanding the following solution
     *
     * e.g. nums = [1, 2, 7, 4, 3, 1], next permutation:[1, 3, 1, 2, 4, 7]
     * nums:[1, 2, 7, 4, 3, 1]
     * k stopped at element 2 of index 1, because 2 < 7
     * l stopped at element 3 of index 4, because 3 > 2
     * swap 2 and 3, nums:[1, 3, 7, 4, 2, 1]
     * reverse [7, 4, 2, 1], nums:[1, 3, 1, 2, 4, 7]
     *
     * ===========================================================
     * 1.Find the largest index k such that nums[k] < nums[k + 1].
     *   If no such index exists, just reverse nums and done.
     * 2.Find the largest index l > k such that nums[k] < nums[l].
     * 3.Swap nums[k] and nums[l].
     * 4.Reverse the sub-array nums[k + 1:].
     * @see <a href="https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order"></a>
     * @author Narayana Pandita
     * @author jianchao-li
     * @see <a href="https://leetcode.com/problems/next-permutation/solutions/13867/c-from-wikipedia"></a>
     * @param nums
     */
    public void nextPermutationV1(int[] nums) {
        int n = nums.length;
        int k = 0;
        int l = 0;
        /**
         * Scan array from high to low, find the first k that satisfy nums[k] < nums[k + 1]
         */
        for (k = n - 2; k >= 0; k--){
            if (nums[k] < nums[k + 1]) {
                break;
            }
        }
        if (k < 0) {//reverse the whole array.
            reverse(nums, 0, n - 1);
        } else { // scan array from high to k+1, find the first l that satisfy nums[l] > nums[k]
            for (l = n - 1; l > k; l--) {
                if (nums[l] > nums[k]) {
                    break;
                }
            }
            swap(nums, k, l);
            reverse(nums, k + 1, n - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            swap(nums, begin++, end--);
        }
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = nums.length;
        int k = n - 2;
        int l = n - 1;
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }
        if (k >= 0) {
            while (l > k && nums[l] <= nums[k]) {
                l--;
            }
            swap(nums, k, l);
            reverse(nums, k + 1, n - 1);
        } else {
            reverse(nums, k + 1, n - 1);
        }
    }
}
