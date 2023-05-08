package org.ict.algorithm.leetcode.array;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 * 
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 * 
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 * 
 * Note: should run in O(n) time and uses constant extra space.
 *
 * LC41, Hard
 */
public class FirstMissingPositive {
	
	public static void main(String[] args) {
		int[] A = {7, 8, 9, 11, 12};
		System.out.println(firstMissingPositiveV2(A));
		System.out.println(firstMissingPositiveV1(A));
	}
	
	/**
	 * The basic idea is to traversal and try to move the current value to position whose index is exactly the value (swap them). 
	 * Then travelsal again to find the first unusal value, which can not be corresponding to its index
	 * @param nums
	 * @return
	 */
	public int firstMissingPositiveV3(int[] nums) {
		int i = 0, n = nums.length;
		while (i < n) {
	        // If the current value is in the range of (0,length) and it's not at its correct position, 
	        // swap it to its correct position.
	        // Else just continue;
			if (nums[i] >= 0 && nums[i] < n && nums[nums[i]] != nums[i])
				swap(nums, i, nums[i]);
			else
				i++;
		}
		int k = 1;

	    // Check from k=1 to see whether each index and value can be corresponding.
		while (k < n && nums[k] == k)
			k++;

	    // If it breaks because of empty array or reaching the end. K must be the first missing number.
		if (n == 0 || k < n)
			return k;
		else   // If k is hiding at position 0, K+1 is the number. 
			return nums[0] == k ? k + 1 : k;

	}

	
	/**
	 * The key here is to use swapping to keep constant space 
	 * and also make use of the length of the array, 
	 * which means there can be at most n positive integers. 
	 * So each time we encounter an valid integer, 
	 * find its correct position and swap. 
	 * Otherwise we continue.
	 * 
	 * @param A
	 * @return
	 */
	public static int firstMissingPositiveV2(int[] A) {
        int i = 0;
        while(i < A.length) {
            if (A[i] == i+1 || A[i] <= 0 || A[i] > A.length) {
            	i++;
            } else if (A[A[i]-1] != A[i]) {
            	swap(A, i, A[i]-1);
            } else {
            	i++;
            }
        }
        i = 0;
        while(i < A.length && A[i] == i+1) {
        	i++;
        }
        return i+1;
    }

	/**
	 * Put each number in its right place.
	 * For example:
	 * When we find 5, then swap it with A[4].
	 * At last, the first place where its number is not right, return the place + 1.
	 * @param A
	 * @return
	 */
	public static int firstMissingPositiveV1(int A[]) {
		int len = A.length;
        for (int i = 0; i < len; ++i) {
        	// why use while in here? not if statement?
        	while (A[i] > 0 && A[i] <= len && A[A[i] - 1] != A[i]) {
        		swap(A, A[i], A[A[i] - 1]);
        	}
        }
        
        for (int i = 0; i < len; ++ i) {
        	if (A[i] != i + 1) {
        		return i + 1;
        	}
        }
        
        return len + 1;
	}
	
	public static void swap(int[] A, int i, int j){
	    if (i != j) {
	        A[i] ^= A[j];
	        A[j] ^= A[i];
	        A[i] ^= A[j];
	    }
	}
}
