package org.ict.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * 
 * Implement next permutation, 
 * which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, 
 * it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. 
 * Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 * @see https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
 * @see https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
 * 
 * LC31
 */
public class NextPermutation {
	
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 5, 3, 3, 0};
		//It's OK
		nextPermutationV3(array);
		
		//Not OK
		//nextPermutationV2(array);
		
		//It's OK
		//nextPermutationV1(array);
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * 1.Initial Sequence
	 *   0, 1, 2, 5, 3, 3, 0
	 * 
	 * 2.Find longest non-increasing suffix
	 *   5, 3, 3, 0
	 *   
	 * 3.Identify pivot
	 *   2, 5, 3, 3, 0
	 *   
	 * 4.Find the rightmost successor to pivot in the suffix
	 *   the rightmost successor is 3 next to 0
	 *   
	 * 5.Swap this successor with pivot
	 *   0, 1, 3, 5, 3, 2, 0
	 *   
	 * 6.Reverse the suffix
	 *   0, 1, 3, 0, 2 ,3, 5
	 *   
	 * 7.Done
	 *   0, 1, 3, 0, 2, 3, 5
	 * 
	 * @see https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
	 * @see https://www.nayuki.io/res/next-lexicographical-permutation-algorithm/next-permutation-algorithm.svg
	 * @param array
	 * @return
	 */
	public static boolean nextPermutationV3(int[] array) {
	    // Find longest non-increasing suffix
	    int i = array.length - 1;
	    while (i > 0 && array[i - 1] >= array[i])
	        i--;
	    // Now i is the head index of the suffix
	    
	    // Are we at the last permutation already?
	    if (i <= 0)
	        return false;
	    
	    // Let array[i - 1] be the pivot
	    // Find rightmost element that exceeds the pivot
	    int j = array.length - 1;
	    while (array[j] <= array[i - 1])
	        j--;
	    // Now the value array[j] will become the new pivot
	    // Assertion: j >= i
	    
	    // Swap the pivot with j
	    int temp = array[i - 1];
	    array[i - 1] = array[j];
	    array[j] = temp;
	    
	    // Reverse the suffix
	    j = array.length - 1;
	    while (i < j) {
	        temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	        i++;
	        j--;
	    }
	    
	    // Successfully computed the next permutation
	    return true;
	}
	
	/**
	 * Well, in fact the problem of next permutation has been studied long ago. 
	 * From the Wikipedia page, in the 14th century, 
	 * a man named Narayana Pandita gives the following classic and yet quite simple algorithm 
	 * (with minor modifications in notations to fit the problem statement):
	 * Find the largest index k such that nums[k] < nums[k + 1]. 
	 * If no such index exists, the permutation is sorted in descending order, just reverse it to ascending order and we are done. 
	 * For example, the next permutation of [3, 2, 1] is [1, 2, 3].
	 * Find the largest index l greater than k such that nums[k] < nums[l].
	 * Swap nums[k] and nums[l].
	 * Reverse the sub-array from nums[k + 1] to nums[nums.size() - 1].
	 * The above algorithm is actually very powerful and can even handle duplicates. 
	 * If you have tried the problems Permutations and Permutations II, then the above nextPermutation can be used to solve both of them.
	 * The code is as follows.
	 * @param nums
	 */
	public static void nextPermutationV2(int[] nums) {
	    	int n = nums.length, k, l;
	    	for (k = n - 2; k >= 0; k--) {
	            if (nums[k] < nums[k + 1]) {
	                break;
	            }
	        }
	    	if (k < 0) {
	    	    reverse(nums, 0, n);
	    	} else {
	    	    for (l = n - 1; l > k; l--) {
	                if (nums[l] > nums[k]) {
	                    break;
	                }
	            } 
	    	    swap(nums, nums[k], nums[l]);
	    	    reverse(nums, 0 + k + 1, n);
	        }
	}
	
	private static void reverse(int[] nums, int start, int end) {
		if(start>end)
	        return;
	    for(int i=start;i<=(end+start)/2;i++)
	        swap(nums,i,start+end-i);
	}
	

	/**
	 * Start from its last element, 
	 * traverse backward to find the first one with index i that satisfy num[i-1] < num[i]. 
	 * So, elements from num[i] to num[n-1] is reversely sorted.
	 * To find the next permutation, we have to swap some numbers at different positions, 
	 * to minimize the increased amount, we have to make the highest changed position as high as possible. 
	 * Notice that index larger than or equal to i is not possible as num[i,n-1] is reversely sorted. 
	 * So, we want to increase the number at index i-1, clearly, 
	 * swap it with the smallest number between num[i,n-1] that is larger than num[i-1]. 
	 * For example, original number is 121543321, 
	 * we want to swap the '1' at position 2 with '2' at position 7.
	 * The last step is to make the remaining higher position part as small as possible, 
	 * we just have to reversely sort the num[i,n-1]
	 */
	public static void nextPermutationV1(int[] num) {
	    int n=num.length;
	    if(n<2) {
	    	return;
	    }
	    int index = n-1;        
	    while(index > 0) {
	        if(num[index-1]<num[index]) {
	        	break;
	        }
	        index--;
	    }
	    if(index==0) {
	        reverseSort(num,0,n-1);
	        return;
	    } else {
	        int val=num[index-1];
	        int j=n-1;
	        while(j>=index) {
	            if(num[j]>val) {
	            	break;
	            }
	            j--;
	        }
	        swap(num,j,index-1);
	        reverseSort(num,index,n-1);
	        return;
	    }
	}

	public static void swap(int[] num, int i, int j){
	    int temp=0;
	    temp=num[i];
	    num[i]=num[j];
	    num[j]=temp;
	}

	public static void reverseSort(int[] num, int start, int end){   
	    if(start>end)
	        return;
	    for(int i=start;i<=(end+start)/2;i++)
	        swap(num,i,start+end-i);
	}
	
}
