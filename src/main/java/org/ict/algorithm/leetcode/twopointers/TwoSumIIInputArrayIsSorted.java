package org.ict.algorithm.leetcode.twopointers;

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 < numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2,
 * added by one as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution.
 * You may not use the same element twice.
 * Your solution must use only constant extra space.
 *
 * Example 1:
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 *
 * Example 2:
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore, index1 = 1, index2 = 3. We return [1, 3].
 *
 * Example 3:
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1.
 * Therefore, index1 = 1, index2 = 2. We return [1, 2].
 *
 *
 * Constraints:
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 * @author sniper
 * @date 17 Jun 2023
 * LC167, Medium,
 */
public class TwoSumIIInputArrayIsSorted {

    /**
     * Two-Pointers Solution
     * Time Cost 1ms
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumV4(int[] numbers, int target) {
        for (int l = 0, r = numbers.length - 1;;) {
            int x = numbers[l] + numbers[r];
            if (x == target) {
                return new int[] {l + 1, r + 1};
            }
            if (x < target) {
                l++;
            } else {
                r--;
            }
        }
    }


    /**
     * Understanding the following solution
     *
     * Two-Pointers Solution
     * Time Cost 1ms
     * ----------------------------------
     *
     * ----------------------------------
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumV3(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (numbers[l] + numbers[r] != target) {
            if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        /**
         * The tests are generated such that there is exactly one solution
         */
        return new int[] {l + 1, r + 1};
    }

    /**
     * Understanding the following solution
     *
     * Two-Pointers Solution
     * Time Cost 1ms
     * -----------------------
     * class Solution:
     *     def twoSum(self, numbers: List[int], target: int) -> List[int]:
     *         l, r = 0, len(numbers) - 1
     *         while l < r:
     *             x = numbers[l] + numbers[r]
     *             if x == target:
     *                 return [l + 1, r + 1]
     *             elif x < target:
     *                 l += 1
     *             else:
     *                 r -=1
     * -------------------------
     * class Solution:
     *     def twoSum(self, numbers: List[int], target: int) -> List[int]:
     *         l, r = 0, len(numbers) - 1
     *         while l < r:
     *             x = numbers[l] + numbers[r]
     *             if x == target:
     *                 return [l + 1, r + 1]
     *             if x < target:
     *                 l += 1
     *             else:
     *                 r -=1
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumV2(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }

    /**
     * Binary Search Solution
     * Time Cost 6ms
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumV1(int[] numbers, int target) {
        for (int i = 0, n = numbers.length;; i++) {
            int x = target - numbers[i];
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (numbers[mid] < x) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            if (numbers[l] == x) {
                return new int[] {i + 1, l + 1};
            }
        }
    }

    /**
     * Binary Search Solution
     * Time Cost 6ms
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int x = target - numbers[i];
            int l = i + 1;
            int r = numbers.length;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (numbers[mid] == x) {
                    /**
                     * Notice here {i + 1, mid + 1}, because index-returned start from 1.
                     */
                    return new int[] {i + 1, mid + 1};
                } else if (numbers[mid] < x) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
        }
        return null;
    }

}
