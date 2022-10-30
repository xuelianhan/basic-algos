package org.ict.algorithm.leetcode.prefixsum;

/**
 * Given an integer array nums,
 * return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 *
 * Follow up:
 * Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 *
 * @author sniper
 * @date 30 Oct, 2022
 * LC238
 */
public class ProductOfArrayExceptSelf {

    /**
     * @see <a href="https://www.enjoyalgorithms.com/blog/product-of-array-except-self"></a>
     * @param nums
     * @return
     */
    public int[] productExceptSelfV3(int[] nums) {

        return null;
    }


    /**
     * An efficient in-place solution.
     * Can we solve the problem in O(1) extra space complexity?
     *
     * @see <a href="https://www.enjoyalgorithms.com/blog/product-of-array-except-self"></a>
     * @param nums
     * @return
     */
    public int[] productExceptSelfV2(int[] nums) {
        int n = nums.length;

        return null;
    }


    /**
     * Using prefix and suffix product array
     * If we observe the output pattern, product[i] = nums[0]...nums[i-1]* nums[i+1]...nums[n-1]
     * So if we multiply the prefix product with the suffix product, we will get the product of elements
     * excluding the current index.
     * To implement this idea, we need to create two extra arrays, prefixProduct[n] and suffixProduct[n], to
     * store prefix and suffix products for each index i.
     *
     * If we know the value of product[i-1], we can easily calculate prefixProduct[i]
     * prefixProduct calculation:
     * prefixProduct[i] = product of elements from index 0 to i-1
     * prefixProduct[i-1] = product of elements from index 0 to i-2
     * prefixProduct[i]= nums[i-1] * product[i-1]
     * For i = 0, there is no element on the left of the array.So we initialize prefixProduct[0] with 1 and loop
     * will run from i=1 to n-1.
     *
     * Similarly, we can store the suffix product array by calculating the running product using a single loop
     * starting from the right end. The idea is simple:
     * if we know the value of suffixProduct[i+1], we can easily calculate the suffixProduct[i].
     * suffixProduct calculation:
     * suffixProduct[i] = product of elements from index i+1 to n-1.
     * suffixProduct[i+1] = product of elements from index i+2 to n-1.
     * suffixProduct[i] = nums[i+1] * suffixProduct[i+1].
     * For i = n - 1, there is no element on the right of the array.
     * So we initialize suffixProduct[n-1] with 1 and loop will run from i = n - 2 to 0.
     *
     * Finally, we run another single loop to store the product excluding each element in the output array.
     * product[i] = prefixProduct[i] * suffixProduct[i]
     *
     * nums = [2, 1, 3, 4]
     * product[2] = 2 * 1 * 4
     * prefixProduct[2] = nums[2-1]*prefixProduct[2-1] = 1 * prefixProduct[1] = 1 * nums[1-1]*prefixProduct[1-1]=1*2*1
     * suffixProduct[2] = nums[2+1]* suffixProduct[2+1] = 4 * suffixProduct[3] = 4 * 1
     *
     * prefixProduct:[1, 2, 2, 6]
     * suffixProduct:[12, 12, 4, 1]
     *
     *
     * Time Complexity O(N)
     * Space Complexity O(N)
     *
     * @see <a href="https://www.enjoyalgorithms.com/blog/product-of-array-except-self"></a>
     * @param nums
     * @return
     */
    public int[] productExceptSelfV1(int[] nums) {
        /**
         * prefix product
         *
         */
        int n = nums.length;
        int[] prefixProduct = new int[n];
        prefixProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            prefixProduct[i] = nums[i-1] * prefixProduct[i-1];
        }

        /**
         * suffix product
         */
        int[] suffixProduct = new int[n];
        suffixProduct[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffixProduct[i] = nums[i+1] * suffixProduct[i+1];
        }

        int[] product = new int[n];
        for (int i = 0; i < n; i++) {
            product[i] = prefixProduct[i] * suffixProduct[i];
        }

        return product;
    }

    /**
     * Brute Force Solution.
     * Exceed the time limit.
     * Time Complexity O(N^2)
     *
     * @see <a href="https://www.enjoyalgorithms.com/blog/product-of-array-except-self"></a>
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] product = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int curProduct = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i == j) {
                    continue;
                }
                curProduct *= nums[j];
            }
            product[i] = curProduct;
        }
        return product;
    }
}
