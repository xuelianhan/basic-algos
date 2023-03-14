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
        //int[] arr = {0, 1, 0};
        int[] arr = {3, 4, 5, 1};
        PeakIndexInMountainArray instance = new PeakIndexInMountainArray();
        instance.peakIndexInMountainArray(arr);
    }

    /**
     * Golden-section search
     * @see <a href="https://en.wikipedia.org/wiki/Golden-section_search"></a>
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        double goldenRatio = (Math.sqrt(5) + 1) / 2;
        int left = 0;
        int right = arr.length - 1;
        int lmid = cal_lo(left, right, goldenRatio);
        int rmid = cal_hi(left, right, goldenRatio);
        while (lmid < rmid) {
            if (arr[lmid] < arr[rmid]) {
                left = lmid;
                lmid = rmid;
                rmid = cal_hi(left, right, goldenRatio);
            } else {
                right = rmid;
                rmid = lmid;
                lmid = cal_lo(left, right, goldenRatio);
            }
        }
        return lmid;
    }

    private int cal_lo(int l, int r, double goldenRatio) {
        return (int)(r - Math.ceil((r - l) / goldenRatio));
    }

    private int cal_hi(int l, int r, double goldenRatio) {
        return (int)(l + (r - l) / goldenRatio);
    }


}
