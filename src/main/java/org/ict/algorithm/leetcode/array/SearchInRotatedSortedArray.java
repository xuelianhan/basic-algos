package org.ict.algorithm.leetcode.array;

/**
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. 
 * If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * 
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * 
 * LC33
 *
 */
public class SearchInRotatedSortedArray {
	
	public static void main(String[] args) {
		int[] nums = {12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		int target = 14;
		int rs1 = searchV1(nums, target);
		int rs2 = searchV1(nums, target);
		int rs3 = searchV1(nums, target);
		System.out.println(rs1);
		System.out.println(rs2);
		System.out.println(rs3);
	}
	
	/**
	 * The idea is that when rotating the array, there must be one half of the array that is still in sorted order.
	 * For example, 6 7 1 2 3 4 5, the order is disrupted from the point between 7 and 1. 
	 * So when doing binary search, 
	 * we can make a judgement that which part is ordered and whether the target is in that range, 
	 * if yes, continue the search in that half, 
	 * if not continue in the other half.
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int searchV3(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
        
            if (nums[start] <= nums[mid]){
                 if (target < nums[mid] && target >= nums[start]) 
                    end = mid - 1;
                 else
                    start = mid + 1;
            } 
        
            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                 else
                    end = mid - 1;
            }
        }
        return -1;
    }
	
	/**
	 * Revised Binary Search
	 * @param A
	 * @param target
	 * @return
	 */
	public static int searchV2(int[] A, int target) {
	    int lo = 0;
	    int hi = A.length - 1;
	    while (lo < hi) {
	        int mid = (lo + hi) / 2;
	        if (A[mid] == target) return mid;
	        
	        if (A[lo] <= A[mid]) {
	            if (target >= A[lo] && target < A[mid]) {
	                hi = mid - 1;
	            } else {
	                lo = mid + 1;
	            }
	        } else {
	            if (target > A[mid] && target <= A[hi]) {
	                lo = mid + 1;
	            } else {
	                hi = mid - 1;
	            }
	        }
	    }
	    return A[lo] == target ? lo : -1;
	}

	/**
	 * This very nice idea is from rantos22's solution who sadly only commented "You are not expected to understand that :)", 
	 * which I guess is the reason it's now it's hidden among the most downvoted solutions. 
	 * I present an explanation and a more usual implementation.Explanation
	 * Let's say nums looks like this: [12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
	 * Because it's not fully sorted, we can't do normal binary search. But here comes the trick:
	 * If target is let's say 14, then we adjust nums to this, where "inf" means infinity:
	 * [12, 13, 14, 15, 16, 17, 18, 19, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf, inf]
	 * If target is let's say 7, then we adjust nums to this:
	 * [-inf, -inf, -inf, -inf, -inf, -inf, -inf, -inf, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
	 * And then we can simply do ordinary binary search.
	 * Of course we don't actually adjust the whole array but instead adjust only on the fly only the elements we look at. 
	 * And the adjustment is done by comparing both the target and the actual element against nums[0].
	 * If nums[mid] and target are "on the same side" of nums[0], we just take nums[mid]. 
	 * Otherwise we use -infinity or +infinity as needed.
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int searchV1(int[] nums, int target) {
	    int lo = 0, hi = nums.length;
	    while (lo < hi) {
	        int mid = (lo + hi) / 2;
	        
	        double num = (nums[mid] < nums[0]) == (target < nums[0])
	                   ? nums[mid]
	                   : target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	                   
	        if (num < target)
	            lo = mid + 1;
	        else if (num > target)
	            hi = mid;
	        else
	            return mid;
	    }
	    return -1;
	}
}
