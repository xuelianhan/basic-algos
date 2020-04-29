package org.ict.algorithm.leetcode.breadthfirstsearch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LC279
 * A perfect square is a number that can be expressed as the product of two equal integers.
 *
 * Given a positive integer n, find the least number of perfect square numbers(for example,
 * 1, 4, 9, 16, ...) which sum to n.
 * 
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * 
 * 
 * Legendre's Three-square Theorem
 * In mathematics, Legendre's three-square theorem states that a natural number 
 * can be represented as the sum of three squares of integers n=x²+y²+z² if and only if 
 * n is not of the form n=4ᵃ(8b+7) for nonnegative integers a and b. 
 * The first numbers that cannot be expressed as the sum of three squares 
 * (i.e. numbers that can be expressed as n=4ᵃ(8b+7)) 
 * are 7, 15, 23, 28, 31, 39, 47, 55, 60, 63, 71... 
 * (sequence A004215 in the OEIS).
 *
 */
public class PerfectSquares {
	
	public static void main(String[] args) {
		int result = bfsV1(13);
		System.out.println(result);
	}

    public int numSquares(int n) {
        return numSquaresDP(n);        
    }

    /**
     * Start from node 0 in queue, and keep pushing in perfect square number + current value,
     * once we reach number n, we found the solution.
     *
     */
    private static int bfsV2(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            while (size--) {
                int cur = queue.poll();
                for (int i = 1; i*i <= n; i++) {
                    int target = cur + i*i;
                    if (target == n) {
                        return depth;
                    }
                    if (target > n) {
                        break;
                    }
                    if (!visited.contains(target)) {
                        queue.offer(target);
                        visited.add(target);
                    }
                }
            }
        }
        return depth;
    }
    
    /**
     * 40MB memory, 33ms
     * @param n
     * @return
     */
    private static int bfsV1(int n) {
    	if (n <= 0) {
    		return 0;
    	}
    	// perfectSquares contain all perfect square numbers which 
        // are smaller than or equal to n.
    	List<Integer> perfectSquares = new LinkedList<>();
    	// cntPerfectSquares[i - 1] = the least number of perfect 
        // square numbers which sum to i.
    	int[] cntPerfectSquares = new int[n];
    	// Get all the perfect square numbers which are smaller than 
        // or equal to n.
    	for (int i = 1; i * i <= n; i++) {
    		perfectSquares.add(i*i);
    		cntPerfectSquares[i*i - 1] = 1;
    	}
    	// If n is a perfect square number, return 1 immediately.
    	if (perfectSquares.get(perfectSquares.size() - 1) == n) {
    		return 1;
    	}
    	// Consider a graph which consists of number 0, 1,...,n as
        // its nodes. Node j is connected to node i via an edge if  
        // and only if either j = i + (a perfect square number) or 
        // i = j + (a perfect square number). Starting from node 0, 
        // do the breadth-first search. If we reach node n at step 
        // m, then the least number of perfect square numbers which 
        // sum to n is m. Here since we have already obtained the 
        // perfect square numbers, we have actually finished the 
        // search at step 1.
    	Queue<Integer> queue = new LinkedList<>();
    	perfectSquares.forEach(item ->{
    		queue.offer(item);
    	});
    	
    	int currentCntPerfectSquares = 1;
    	while (!queue.isEmpty()) {
    		currentCntPerfectSquares++;
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			Integer cur = queue.poll();
    			for (Integer perfect : perfectSquares) {
    				if (cur + perfect == n) {
    					System.out.println("cur:" + cur + ", perfect:" + perfect);
    					return currentCntPerfectSquares;
    				} else if ((cur + perfect < n) && (cntPerfectSquares[cur + perfect - 1] == 0)) {
    					cntPerfectSquares[cur + perfect - 1] = 1;
    					queue.offer(cur + perfect);
    				} else if(cur + perfect > n){
    					break;
    				}
    			}
    		}
    	}
    	return 0;
    }

    /**
     * 36.4MB memory, 1ms
     * Lagrange's four-square theorem, also known as Bachet's conjecture.
     * Joseph Louis Lagrange proved in 1770 that every positive integer 
     * (natural number) can be represented as the sum of four (or fewer) integer squares.
     * For example, 31 can be written as 31=5^2 + 2^2 + 1^2 + 1^2
     *
     * This theorem was proved by Joseph Louis Lagrange in 1770. 
     * Adrien-Marie Legendre completed the theorem in 1797–8 with his three-square theorem, by 
     * proving that a positive integer can be expressed as the sum of three squares if and 
     * only if it is not of the form (4^k)(8m+7) for integers k and m.
     *
     * This basically tells us that any number can be broken into maximum of 4 squares. 
     * So, by default this is our answer. we need to check if we have a solution with 3 or 2 or 1 squares. 
     * There can be a 3-square solution if and only if we can’t write n in the form (4^k)(8m+7) for integers k and m. 
     * If a number itself is a perfect square number then numbers of square is 1. 
     * Otherwise we can try break the number into 2 squares i and j such that n=i*i+j*j, for any i, 1≤i≤√n. 
     * So, for any natural positive number there are only 4 possible results: 1, 2, 3, 4.
     *
     * Below is a O(√n) time solution using the above math based solution.
     * 
     * @see https://planetmath.org/ProofOfLagrangesFourSquareTheorem
     * @see http://www.zrzahid.com/least-number-of-perfect-squares-that-sums-to-n/
     */
    public int numSquaresLagrange(int n) {
    	if (n < 0) {// negative number has no perfect squares.
    		return 0;
    	}
        // if n is a perfect square, return 1.
        if (square(n)) {
            return 1;
        } 
        
        // Check whether 2 is the result.This code segment can put after 4'case.It's OK.
        int sqrt = (int)(Math.sqrt(n));
        for (int i = 1; i <= sqrt; i++) {
            if (square(n - i*i)) {
                return 2;
            }   
        }

        // The result is 4 if n can be written in the form of 4^k*(8*m + 7).
        // 4^k*(8*m + 7) = (4^k)*(8*m + 7)
        while ((n & 3) == 0) { // n%4 == 0
            n >>=2; //Division by 4=2^2
        }
        // So here only left the tail of (8*m + 7), the 4^k is removed by the while-loop.
        if ((n & 7) == 7) { // n%8 == 7
            return 4;
        }
        return 3;
    }
    
    private static void testMod() {
    	//4^k*(8*m + 7)
    	//4^2*(8*3 + 7)
    	long n = (long)Math.pow(4, 1)*(8*1 + 7);
    	System.out.println(n);
    	while ((n & 3) == 0) { // n%4 == 0, this loop will erase the 4^k and remain (8*m + 7)
    		n >>=2; //Division by 4=2^2
        }
    	System.out.println(n);
    }

    private boolean square(int n) {
        int sqrt = (int)(Math.sqrt(n));
        return (sqrt*sqrt == n);
    }

    /**
     * How many ways we can find n by summing up perfect squares less than n?
     * Note that, maximum perfect square less than n will be sqrt of n.
     * So, we can check for each numbers in j=1 to sqrt of n whether we can
     * break n into two parts such that one part is a perfect square j*j and
     * the remaining part n-j*j can be broken into perfect squares in similar
     * manner. Clearly it has a recurrence relation ps(n) = j*j + ps(n-j*j),
     * for all j, 1≤j≤√n. We need to find such j that minimizes number of
     * perfect squares generated.
     *
     * Note the recursion tree generated for the recurrence relation
     * ps(n) = j*j + ps(n-j*j).
     * Let's take n=12, for all possible j gte 1 and j lte sqrt of n for n=12.
     * We can clearly see that we can reach solution in many paths but the least
     * number of perfect squares that sums to n=12 is ps(12)== 2^2+2^2+2^2 which 
     * has 3 perfect squares. Also, note that the problem has repeating subproblems. 
     * For example, ps(2), ps(7), and ps(3) is appearing twice. So, intuition tells 
     * us that as the problem has optimal substructure ps(n)=j*j+ps(n-j*j), 
     * for all possible 1≤j≤√n and it has repeating subproblems, so we can use DP to 
     * solve the exponential expansion of the recursion tree.
     *
     * O(n^2) DP solution:
     * Let, PSN(i) is minimum number of perfect squares that sum to i
     * PSN(i) = min{1+PSN(i-j*j)}, for all j, 1≤j≤√i
     * 
     * Below is a simple implementation of the above DP solution to the least
     * number of perfect sum problem. This runs in O(n^2).
     *
     * @see http://www.zrzahid.com/least-number-of-perfect-squares-that-sums-to-n/
     *
     */ 
    public int numSquaresDP(int n) {
        if (n <= 0) {
            return 0;
        } 
        int[] dp = new int[n+1];// we start at index-0, so the length is n+1(dp[0]~dp[n])
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        // dp[i] = the least number of perfect square numbers
        // to compute least perfect for n we compute top down for each possible
        // value sum from 2 to n
        for (int i = 2; i <= n; i++) {
            // for a particular value i we can break it as sum of perfect square j*j 
            // and all perfect squares from solution of the remainder (i - j*j)
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j*j]);//1 means j*j this one case.
            }
        }
        return dp[n];
    }
























}
