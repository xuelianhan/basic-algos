package org.ict.algorithm.leetcode.binarysearch;


import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Return the kth positive integer that is missing from this array.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * Example 2:
 *
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 *
 *
 * Follow up:
 *
 * Could you solve this problem in less than O(n) complexity?
 * @author sniper
 * @date 06 Mar, 2023
 * LC1539, Easy
 */
public class KthMissingPositiveNumber {

    public static void main(String[] args) {
        KthMissingPositiveNumber instance = new KthMissingPositiveNumber();
        int[] arr = {2,3,4,7,11};
        int k = 5;
        instance.findKthPositive(arr, k);
    }

    /**
     * e.g. arr = [2,3,4,7,11], k = 5, expect 9
     * 1 2 3 4 5
     * 2 3 4 7 11
     * arr[i] - (i + 1):
     * 1 1 1 3 6, this means the count of missing numbers before each index.
     * for instance, there are 3 missing numbers before 7, they are 1,5,6.
     * So we can start iterate the count missing number array, let's call it B.
     * B:1 1 1 3 6
     * What we need to find in the array-B?
     * The answer is the first element greater than k or equals to k.
     * At here, we get 6, 6 is the first element greater than k=5, and the index of 6 is 4.
     * After that, we go back to the original array:[2,3,4,7,11], the value at index 4 is 11.
     * So, we know that our answer is between 7 and 11.
     * -----------------------------------------------------
     * Explanation
     * Let's assume the final result is x, A is the original array.
     * There are m number not missing in the range of [1, x].
     * Binary search the m in range [0, A.size()].
     *
     * If there are m number not missing,
     * that is A[0], A[1] ... A[m-1],
     * the number of missing under A[m] is A[m] - 1 - m, or you can see it as A[m] - (m + 1).
     *
     * If A[m] - 1 - m < k, m is too small, we update left = m + 1.
     * If A[m] - 1 - m >= k, m is big enough, we update right = m.
     *
     * Note that, we exit the while-loop, l = r,
     * which equals to the number of missing number used.
     * So the Kth positive number will be l + k.
     *
     *
     * Complexity
     * Time O(logN)
     * Space O(1)
     *
     * @see <a href="https://leetcode.com/problems/kth-missing-positive-number/solutions/779999/java-c-python-o-logn"></a>
     * @author lee215
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositiveV2(int[] arr, int k) {
        int n = arr.length;
        int left = 0;
        int right = n;
        while (left < right) {
            int m = (left + right) / 2;
            if (arr[m] - m - 1 < k) {
                left = m + 1;
            } else {
                right = m;
            }
        }
        return left + k;
    }

    /**
     *
     * Note that the array is in strictly increasing order and hence there is no repetition.
     *
     * Think of this case,
     * if every element in the array greater than k , the answer would be k.
     * So, for every element <= k ,
     * you need to increment k. (i.e. when you iterate from left to right).
     * Since the array is in increasing order,
     * you can break out of the loop on the first instance this condition fails.
     *
     * e.g. arr = [2,3,4,7,11], k = 5, expect 9
     * e.g. arr = [1, 3], k = 1, expect 2
     * e.g. arr = [3,10], k = 2, expect 2
     * e.g. arr = [5,6,7,8,9], k = 9, expect 14
     *
     * @see <a href="https://leetcode.com/problems/kth-missing-positive-number/solutions/876751/java-1-liner-o-n-simplest-easy-to-understand-beats-100"></a>
     * @author niranjvin
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositiveV1(int[] arr, int k) {
        for (int a : arr) {
            if (a <= k) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }

    /**
     * Using HashSet to store all the elements in the array.
     *
     * e.g. arr = [2,3,4,7,11], k = 5, expect 9
     * e.g. arr = [1,3], k = 1, expect 2
     * e.g. arr = [3,10], k = 2, expect 2
     * e.g. arr = [5,6,7,8,9], k = 9, expect 14
     * @see <a href="https://leetcode.com/problems/kth-missing-positive-number/solutions/1004535/python-two-solutions-o-n-and-o-log-n-explained"></a>
     * @author DBabichev
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        if (arr[n - 1] == n) {
            return (n + k);
        }
        /**
         * Notice the boxed here, and use cnt instead of k, because we need to
         * remain k not modified in for-loop, we decrease cnt instead.
         */
        int cnt = k;
        Set<Integer> set = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        for (int i = 1; i <= (k + n); i++) {
            if (!set.contains(i)) {
                cnt--;
            }
            if (cnt == 0) {
                return i;
            }
        }
        return 0;
    }
}
