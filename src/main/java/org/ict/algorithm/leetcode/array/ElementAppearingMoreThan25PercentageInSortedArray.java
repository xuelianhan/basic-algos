package org.ict.algorithm.leetcode.array;

/**
 * Given an integer array sorted in non-decreasing order,
 * there is exactly one integer in the array that occurs more than 25% of the time,
 * return that integer.
 *
 * Example 1:
 * Input: arr = [1,2,2,6,6,6,6,7,10]
 * Output: 6
 *
 * Example 2:
 * Input: arr = [1,1]
 * Output: 1
 *
 *
 * Constraints:
 * 1 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^5
 *
 * @author sniper
 * @date 27 Jul 2023
 * LC1287, Easy
 */
public class ElementAppearingMoreThan25PercentageInSortedArray {

    /**
     * class Solution:
     *     def findSpecialInteger(self, arr: List[int]) -> int:
     *         n = len(arr)
     *         gap = n // 4
     *         for i in range(n - gap):
     *             if arr[i] == arr[i + gap]:
     *                 return arr[i]
     *         return -1
     * ----------------------------------------
     * object Solution {
     *     def findSpecialInteger(arr: Array[Int]): Int = {
     *         var n = arr.length
     *         var gap = n / 4
     *         for (i <- 0 until n - gap) {
     *             if (arr(i) == arr(i + gap)) {
     *                 return arr(i)
     *             }
     *         }
     *         return -1
     *     }
     * }
     * ---------------------------------------
     * class Solution {
     * public:
     *     int findSpecialInteger(vector<int>& arr) {
     *         int n = arr.size();
     *         int gap = n / 4;
     *         for (int i = 0; i < n - gap; i++) {
     *             if (arr[i] == arr[i + gap]) {
     *                 return arr[i];
     *             }
     *         }
     *         return -1;
     *     }
     * };
     * -------------------------------------------
     * impl Solution {
     *     pub fn find_special_integer(arr: Vec<i32>) -> i32 {
     *         let n = arr.len();
     *         let gap = n / 4;
     *         for i in 0..n - gap {
     *             if arr[i] == arr[i + gap] {
     *                 return arr[i];
     *             }
     *         }
     *         -1
     *     }
     * }
     * @param arr
     * @return
     */
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        int gap = n / 4;
        for (int i = 0; i < n - gap; i++) {
            if (arr[i] == arr[i + gap]) {
                return arr[i];
            }
        }
        return -1;
    }
}
