package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * Given an integer array nums, find a
 * subarray
 *  that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * @author sniper
 * @date 28 Mar, 2023
 * LC152, Medium, frequency=32
 */
public class MaximumProductSubarray {

    /**
     * e.g.nums = [2,3,-2,4]
     * res:2, curMax:2, curMin:2
     * i:1, nums[1]:3, curMax=max(3, 2*3)=6, curMin=min(3, 2*3)=3, res=max(2,6)=6
     * i:2, nums[2]:-2, curMax:3, curMin:6, curMax=max(-2, 3*(-2))=-2, curMin=min(6, 6*(-2))=-12, res=max(6,-2)=6
     * i:3, nums[3]:4, curMax:-2, curMin:-12, curMax=max(4, -2*4)=4, curMin=(-12, (-12)*4)=-48, res=max(6, 4)=6
     * for-loop-end, return res:6
     *
     * @see <a href="https://leetcode.com/problems/maximum-product-subarray/solutions/48230/possibly-simplest-solution-with-o-n-time-complexity/"></a>
     * @param nums
     * @return
     */
    public int maxProductV1(int[] nums) {
        int res = nums[0];
        /**
         * curMax/curMin stores the max/min product of subarray that ends with the current nums[i].
         */
        int curMax = nums[0];
        int curMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            /**
             * Multiplied by a negative will change the big/small relation of curMax and curMin.
             */
            if (nums[i] < 0) {
                int temp = curMax;
                curMax = curMin;
                curMin = temp;
            }

            curMax = Math.max(nums[i], curMax * nums[i]);
            curMin = Math.min(nums[i], curMin * nums[i]);
            res = Math.max(res, curMax);
        }
        return res;
    }


    /**
     * Calculate prefix product in A.
     * Calculate suffix product in A.
     * Return the max.
     * Time Complexity O(N)
     * Space Complexity O(1)
     * Explanation by izackwu:
     *
     * First, if there's no zero in the array,
     * then the subarray with maximum product must start with the first element or end with the last element.
     * And therefore, the maximum product must be some prefix product or suffix product.
     * So in this solution, we compute the prefix product A and suffix product B,
     * and simply return the maximum of A and B.
     *
     * Why? Here's the proof:
     * Say, we have a subarray A[i : j](i != 0, j != n) and the product of elements inside is P.
     * Take P > 0 for example: if A[i] > 0 or A[j] > 0,
     * then obviously, we should extend this subarray to include A[i] or A[j];
     * if both A[i] and A[j] are negative,
     * then extending this subarray to include both A[i] and A[j] to get a larger product.
     * Repeating this procedure, so eventually we will reach the beginning or the end of A.
     * What if there are zeroes in the array? Well, we can split the array into several smaller ones.
     * That's to say, when the prefix product is 0,
     * we start over and compute prefix product from the current element instead.
     * And this is exactly what A[i] *= (A[i - 1]) or 1 does.
     *
     * suppose there's no 0 in the array:
     * 1.if number of negative number is odd, and left most is i and right most is j,
     * then we can only either keep A[:j] or A[i+1:] to make it max
     * 2.if it's even, then we just multiply all the numbers.
     * @see <a href="https://leetcode.com/problems/maximum-product-subarray/solutions/183483/java-c-python-it-can-be-more-simple/"></a>
     * e.g.nums = [2,3,-2,4]
     * l:0, r:0, res:2
     * i:0, l=1*2=2, r=1*4=4, res=max(0, max(2, 4))=4
     * i:1, l=2*3=6, r=4*(-2)=-8, res=max(4, max(6,-8))=6
     * i:2, l=6*(-2)=-12, r=(-8)*3=-24, res=(6, max(-12,-24))=6
     * i:3, l=(-12)*4=-48, r=(-24)*2=-48, res=(6,max(-48,-48))=6
     * for-loop-end, return res:6
     *
     * @author lee215
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int res = nums[0];
        int l = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            l = (l == 0 ? 1 : l) * nums[i];
            r = (r == 0 ? 1 : r) * nums[n - 1 - i];
            res = Math.max(res, Math.max(l, r));
        }
        return res;
    }
}
