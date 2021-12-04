package org.ict.algorithm.leetcode.tree;

/**
 * Design a class to find the k^th largest element in a stream.
 * Note that it is the k^th largest element in the sorted order, not the k^th distinct element.
 *
 * Implement K^thLargest class:
 *
 * K^thLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and
 * returns the element representing the k^th largest element in the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 *
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 *
 * Constraints:
 *
 * 1 <= k <= 10^4
 * 0 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * -10^4 <= val <= 10^4
 * At most 10^4 calls will be made to add.
 * It is guaranteed that there will be at least k elements in the array when you search for the k^th element.
 * @author sniper
 * @date 2021/12/1
 * LC703
 */
public class KthLargestElementInStream {

    public static void main(String[] args) {
        int k = 3;
        int[] nums = {4,5,8,2};
        KthLargest obj = new KthLargest(k, nums);
        int x1 = obj.add(3);
        int x2 = obj.add(5);
        int x3 = obj.add(10);
        int x4 = obj.add(9);
        int x5 = obj.add(4);
        System.out.println(x1 + ", " + x2 + ", " + x3 + ", " + x4 + ", " + x5);
    }
    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */
    public static class KthLargest {
        private int k;
        private int[] nums;

        public KthLargest(int k, int[] nums) {
           this.k = k;
           this.nums = nums;
        }

        public int add(int val) {

            return 0;
        }

    }
}
