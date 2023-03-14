package org.ict.algorithm.leetcode.binarysearch;

/**
 * An array arr a mountain if the following properties hold:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array arr,
 * return the index i such that arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 *
 * You must solve it in O(log(arr.length)) time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [0,1,0]
 * Output: 1
 * Example 2:
 *
 * Input: arr = [0,2,1,0]
 * Output: 1
 * Example 3:
 *
 * Input: arr = [0,10,5,2]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 3 <= arr.length <= 10^5
 * 0 <= arr[i] <= 10^6
 * arr is guaranteed to be a mountain array.
 * @author sniper
 * @date 14 Mar, 2023
 * LC852, Medium
 */
public class PeakIndexInMountainArray {

    public static void main(String[] args) {
        //int[] arr = {0, 1, 0}; //expected 1
        int[] arr = {3, 4, 5, 1};//expected 2
        PeakIndexInMountainArray instance = new PeakIndexInMountainArray();
        instance.peakIndexInMountainArray(arr);
    }

    /**
     * Golden-Section Search
     * @see <a href="https://en.wikipedia.org/wiki/Golden-section_search"></a>
     * @see <a href="https://medium.datadriveninvestor.com/golden-section-search-method-peak-index-in-a-mountain-array-leetcode-852-a00f53ed4076"></a>
     * @param arr
     * @return
     */
    public int peakIndexInMountainArrayV2(int[] arr) {
        double goldenRatio = (Math.sqrt(5) + 1) / 2.0;
        int left = 0;
        int right = arr.length - 1;
        int midLeft = cal_left(left, right, goldenRatio);
        int midRight = cal_right(left, right, goldenRatio);
        while (midLeft < midRight) {
            if (arr[midLeft] < arr[midRight]) {
                left = midLeft;
                midLeft = midRight;
                midRight = cal_right(left, right, goldenRatio);
            } else {
                right = midRight;
                midRight = midLeft;
                midLeft = cal_left(left, right, goldenRatio);
            }
        }
        return midLeft;
    }

    private int cal_left(int l, int r, double goldenRatio) {
        return (int)(r - Math.ceil((r - l) / goldenRatio));
    }

    private int cal_right(int l, int r, double goldenRatio) {
        return (int)(l + (r - l) / goldenRatio);
    }

    public int peakIndexInMountainArrayV1(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Time Cost 1ms
     * Sequence Search
     * Time Complexity O(N)
     * Space Complexity O(1)
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int max = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                res = i;
            }
        }
        return res;
    }


}
