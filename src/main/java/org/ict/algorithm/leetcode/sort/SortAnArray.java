package org.ict.algorithm.leetcode.sort;

/**
 * Given an array of integers nums,
 * sort the array in ascending order and return it.
 *
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity,
 * and with the smallest space complexity possible.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Explanation:
 * After sorting the array,
 * the positions of some numbers are not changed (for example, 2 and 3),
 * while the positions of other numbers are changed (for example, 1 and 5).
 *
 * Example 2:
 *
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * Explanation: Note that the values of nums are not necessairly unique.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 * @author sniper
 * @date 10 Mar, 2023
 * LC912, Medium
 */
public class SortAnArray {

    private int[] nums;

    /**
     * Time Cost 38ms
     * Time Complexity O(N*logN)
     * Space Complexity O(N)
     * @param nums
     * @return
     */
    public int[] sortArrayV1(int[] nums) {
        this.nums = nums;
        mergeSort(0, nums.length - 1);
        return nums;
    }

    /**
     * Time Cost 1605ms
     * Time Complexity O(N*logN)
     * Space Complexity O(logN)
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        this.nums = nums;
        quickSort(0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        int i = l, j = mid + 1, k = 0;
        int[] aux = new int[r - l + 1];
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                aux[k++] = nums[i++];
            } else {
                aux[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            aux[k++] = nums[i++];
        }
        while (j <= r) {
            aux[k++] = nums[j++];
        }
        for (i = l; i <= r; ++i) {
            nums[i] = aux[i - l];
        }
    }

    private void quickSort(int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l - 1, j = r + 1, k = l;
        int x = nums[(l + r) >> 1];
        while (k < j) {
            if (nums[k] < x) {
                swap(++i, k++);
            } else if (nums[k] > x) {
                swap(--j, k);
            } else {
                ++k;
            }
        }
        quickSort(l, i);
        quickSort(j, r);
    }

    private void swap(int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
