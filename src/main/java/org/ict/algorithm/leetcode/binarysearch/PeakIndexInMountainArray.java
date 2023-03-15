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
     * c^2 + c = 1
     * c + 1 = 1/c --> (sqrt(5)-1)/2 + 1 = (sqrt(5)+1)/2 = the golden ratio
     * c equals approximately 0.6180
     * The golden-section search is a technique for finding an extreme (minimum or maximum) of a function inside a specified interval.
     * For a strictly uni-modal function with an extreme inside the interval,
     * it will find that extreme, while for an interval containing multiple extrema (possibly including the interval boundaries),
     * it will converge to one of them.
     * If the only extreme on the interval is on a boundary of the interval,
     * it will converge to that boundary point.
     * The method operates by successively narrowing the range of values on the specified interval,
     * which makes it relatively slow, but very robust.
     * The technique derives its name from the fact that the algorithm maintains the function values
     * for four points whose three interval widths are in the ratio φ:1:φ where φ is the golden ratio.
     * These ratios are maintained for each iteration and are maximally efficient.
     * Excepting boundary points, when searching for a minimum,
     * the central point is always less than or equal to the outer points,
     * assuring that a minimum is contained between the outer points.
     * The converse is true when searching for a maximum.
     * The algorithm is the limit of Fibonacci search (also described below) for many function evaluations.
     * Fibonacci search and golden-section search were discovered by Kiefer (1953) (see also Avriel and Wilde (1966)).
     * @see <a href="https://en.wikipedia.org/wiki/Golden-section_search"></a>
     * @see <a href="https://medium.datadriveninvestor.com/golden-section-search-method-peak-index-in-a-mountain-array-leetcode-852-a00f53ed4076"></a>
     *
     * left--------x1--------x2-------right
     * @param arr
     * @return
     */
    public int peakIndexInMountainArrayV2(int[] arr) {
        double goldenRatio = (Math.sqrt(5) + 1) / 2.0;
        int left = 0;
        int right = arr.length - 1;
        int x1 = cal_left(left, right, goldenRatio);
        int x2 = cal_right(left, right, goldenRatio);
        while (x1 < x2) {
            if (arr[x1] < arr[x2]) {
                left = x1;
                x1 = x2;
                x2 = cal_right(left, right, goldenRatio);
            } else {
                right = x2;
                x2 = x1;
                x1 = cal_left(left, right, goldenRatio);
            }
        }
        if (arr[x1] < arr[x1 + 1]) {
            return x1 + 1;
        } else if (arr[x1 - 1] > arr[x1]) {
            return x1 - 1;
        } else {
            return x1;
        }
    }

    private int cal_left(int l, int r, double goldenRatio) {
        return (int)Math.ceil((r - (r - l) / goldenRatio));
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
