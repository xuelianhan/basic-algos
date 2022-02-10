package org.ict.algorithm.leetcode.array;

/**
 * LC409
 */
public class LongestPalindrome {
	
	public static void main(String[] args) {
		
	}

	public String longestPalindrome(String s) {
	   char[] chars = s.toCharArray();
	   int len = s.length();
	   while (len >= 0) {
	       for (int i = 0; i + len - 1 < chars.length; i++) {
	           int left = i;
	           int right = i + len - 1;
	           boolean good = true;
	           while (left < right) {
	               if (chars[left] != chars[right]) {
	                   good = false;
	                   break;
	               }
	               left++;
	               right--;
	           }
	           if (good) {
	        	   return s.substring(i, i + len); 
	           }
	       }
	       --len;
	   }
	   return "";
	}
}
