package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * 
 * The Fibonacci numbers are the numbers in the following integer sequence.
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ……..
 * In mathematical terms, 
 * the sequence Fn of Fibonacci numbers is defined by the recurrence relation
 * Fn = Fn-1 + Fn-2
 * with seed values
 * F0 = 0 and F1 = 1.
 * @see https://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
 * 
 */
public class Fabonacci {
	
	/**
	 * Space Optimized Method base fibV2
	 * We can optimize the space used in method 2 
	 * by storing the previous two numbers only 
	 * because that is all we need to get the next Fibonacci number in series.
	 * 
	 * @param n
	 * @return
	 */
	public static int fibV3(int n) {
		int a = 0, b = 1, c;
		if (n == 0) {
			return 0;
		}
		for (int i = 2; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return b;
	}
 	
	/**
	 * Use Dynamic Programming 
	 * We can avoid the repeated work done is the method 1 by storing the Fibonacci numbers calculated so far.
	 * 
	 * @param n
	 * @return
	 */
	public static int fibV2(int n) {
		//Declare an array to store Fibonacci numbers
		int[] f = new int[n + 2];
		f[0] = 0;
		f[1] = 1;
		
		for (int i = 2; i <=n; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		return f[n];
	}

	/**
	 * Time Complexity: T(n) = T(n-1) + T(n-2) which is exponential.
	 * We can observe that this implementation does a lot of repeated work (see the following recursion tree). 
	 * So this is a bad implementation for nth Fibonacci number.
	 * Extra Space: O(n) if we consider the function call stack size, otherwise O(1).
	 * 
	 * @param n
	 * @return
	 */
	public static int fibV1(int n) {
		if (n <= 1) {
			return n;
		}
		return fibV1(n-1) + fibV1(n-2);
	}
}
