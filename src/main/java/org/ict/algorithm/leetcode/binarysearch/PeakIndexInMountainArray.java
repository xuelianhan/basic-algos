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
     *
     * The three-equivalence method is applicable to strictly monotonic single-peaked functions or single-valley functions.
     * If the function is not strictly monotonic, i.e.,
     * there is an equal segment in the image of the function,
     * then the three-equivalence method can not be used.
     *
     * Our target is to find the peak. In the closed interval[left, right],
     * we firstly locate two golden split points: x1, x2, which would split
     * function-F into three parts: left---x1---x2---right.
     * 1.if F(x1) < F(x2), both x1 and x2 are on the left hand of peak, or on the
     * two sides of peak:
     * left---x1---x2---peak---right
     * left---x1----peak---x2---right
     * Whatever, the peak would be on the right side of x1, so we can let left = x1
     * 2.if F(x1) > F(x2), both x1 and x2 are on the right hand of peak, or on the two sides of peak:
     * left---peak----x1---x2---right
     * left---x1----peak---x2---right
     * The peak would be on the left side of x2, so we can let right = x2
     * 
     * Search space would shrink to nearly 1/3 of original interval every time.
     * Let's see the golden split point formula, we mark golden split point as G.
     * Assume that there is a line segment with length of 1, the length on the left side of G is a, the length on the right side of G is b.
     *
     *          G
     *          |
     *          V
     * -----------------
     * |<---a-->|<--b-->
     *  a + b = 1
     * (a + b) / a = a / b = G
     * ==> 1 + b/a = G ==> 1 + 1/G = G
     * ==> G^2 - G - 1 = 0
     * ==> G^2 - G + (1/2)^2 - (1/2)^2 - 1 = 0
     * ==> (G - 1/2)^2 = 1 + (1/2)^2 = 5/4
     * ==> (G - 1/2)^2 = 5/4
     * Because G is a positive number, then G - 1/2 = sqrt(5)/2
     * ==> G = 1/2 + sqrt(5)/2 = (sqrt(5) + 1) / 2 = the golden ratio
     * G equals approximately 1.6180
     * l-----x1--------x2------r
     * x2-l = r-x1
     * (r-l) / (x2-l) = (x2-l) / (x1-l) = G
     * ==> x2 = l + (r - l)/G
     * r - x1 = x2 - l
     * ==> x1 = r + l - x2 = r + l - (l + (r - l)/G)
     * ==> x1 = r + l - l - (r -l)/G = r - (r-l)/G
     *
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

    /**
     * Math.ceil in here is necessary.
     * @param l
     * @param r
     * @param goldenRatio
     * @return
     */
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
