package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * 
 * Example 1:
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * 
 * Example 2:
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * 
 * Example 3:
 * Input: ")("
 * Output: [""]
 *
 */
public class RemoveInvalidParentheses {
	
	public static void main(String[] args) {
		
		testOperatorSequence();
	}
	
	public List<String> removeInvalidParentheses(String s) {
		return bfsV1(s);
	}
	
	/**
	 * Key Points:
	 * Generate unique answer once and only once, do not rely on Set.
	 * Do not need preprocess.
	 * Runtime 3 ms.
	 * 
	 * Explanation:
	 * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
	 * 
	 * The counter will increase when it is ‘(‘ and decrease when it is ‘)’. 
	 * Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
	 * To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix. 
	 * However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove s[1] or s[2] but the result is the same (). 
	 * Thus, we restrict ourself to remove the first ) in a series of concecutive )s.
	 * After the removal, the prefix is then valid. 
	 * We then call the function recursively to solve the rest of the string. 
	 * However, we need to keep another information: the last removal position. 
	 * If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
	 * For this, we keep tracking the last removal position and only remove ‘)’ after that.
	 * Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
	 * The answer is: do the same from right to left.
	 * However a cleverer idea is: reverse the string and reuse the code!
	 * Here is the final implement in Java.
	 * 
	 * @author dietpepsi
	 * @see https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
	 * @param s
	 * @return
	 */
	public List<String> bfsV2(String s) {
		List<String> result = new ArrayList<>();
		recursiveDFS(s, result, 0, 0, '(', ')');
		return result;
	}
	
	private void recursiveDFS(String s, List<String> result, int last_i, int last_j, char openParen, char closedParen) {
		for (int stack = 0, i = last_i; i < s.length(); i++) {
			if (s.charAt(i) == openParen) stack++;
			if (s.charAt(i) == closedParen) stack--;
			if (stack >= 0) continue;// No need to cut off, because there is no extra closed paren.
			for (int j = last_j; j <= i; j++) {
				// s.charAt(j) == closeParen and s.charAt(j - 1) != closedParen with the limit 
				// we restrict ourself to remove the first ) in a series of concecutive )s
				if (s.charAt(j) == closedParen && (j == last_j || s.charAt(j - 1) != closedParen)) {
					recursiveDFS(s.substring(0, j) + s.substring(j + 1, s.length()), result, i, j, openParen, closedParen);
				}
			}
			return;// Stop here. The recursive calls handle the rest of the string.
		}
		// No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
		String reversed = new StringBuilder(s).reverse().toString();
		if (openParen == '(') {
			recursiveDFS(reversed, result, 0, 0, ')', '(');
		} else {
			result.add(reversed);
		}
	}
	
	
	/**
	 * 52 ms	40.1 MB
	 * The idea is straightforward, with the input string s, 
	 * we generate all possible states by removing one ( or ), 
	 * check if they are valid, if found valid ones on the current level, 
	 * put them to the final result list and we are done, 
	 * otherwise, add them to a queue and carry on to the next level.
	 * The good thing of using BFS is that we can guarantee 
	 * the number of parentheses that need to be removed is minimal, 
	 * also no recursion call is needed in BFS.
	 * we don't need stack to check valid parentheses.
	 * Time complexity:
	 * In BFS we handle the states level by level, 
	 * in the worst case, we need to handle all the levels, 
	 * we can analyze the time complexity level by level and add them up to get the final complexity.
	 * On the first level, there's only one string which is the input string s, 
	 * let's say the length of it is n, to check whether it's valid, we need O(n) time. 
	 * On the second level, we remove one ( or ) from the first level, 
	 * so there are C(n, n-1) new strings, each of them has n-1 characters, 
	 * and for each string, we need to check whether it's valid or not, 
	 * thus the total time complexity on this level is (n-1) x C(n, n-1). 
	 * Come to the third level, total time complexity is (n-2) x C(n, n-2), 
	 * so on and so forth...
	 * Finally we have this formula:
	 * T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
	 * @param s
	 * @return
	 */
	public List<String> bfsV1(String s) {
		List<String> result = new ArrayList<>();
		if (s == null || s == "") {
			return result;
		}
		
		// Initialize
		Queue<String> queue = new ArrayDeque<>();
		Set<String> visited = new HashSet<>();
		queue.add(s);
		visited.add(s);
		boolean found = false;
		
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			if (isValid(cur)) {
				result.add(cur);
				found = true;
			}
			// if found, no need to cut off anymore
			if (found) {
				continue;
			}
			
			// cut-off
			for (int i = 0; i < cur.length(); i++) {
				char c = cur.charAt(i);
				// we only try to remove left or right paren
				if (c != '(' && c != ')') {
					continue;
				}
				// cur.substring(i + 1) will not out of bound, see substring method implementation
				String sub = cur.substring(0, i) + cur.substring(i + 1);
				if (!visited.contains(sub)) {
					visited.add(sub);
					queue.offer(sub);
				}
			}
		}
        return result;
    }
	
	
	
	private boolean isValid(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				count++;
			}
			if (c == ')' && (count-- == 0)) {// do count == 0 then count--
				return false;
			}
			/*
			 * the semantic meaning of code splices above is equals the following:
			 * if (c == ')') {
	         *   if (count == 0) return false;
	         *   count--;
	         * }
	         * 
	         * the following code is equivalent too:
	         * if(c==')')
             *   if(count > 0) 
             *     count--;
             *   else 
             *     return false;
	         */
		}
		return count == 0;
	}
	
	/**
	 * count before decrement:0
	 * flag after count decrement:true
	 * count after decrement:-1
	 * s1:ab
	 * s2:c
	 * original:(()(()
	 * reversed:)(()((
	 */
	private static void testOperatorSequence() {
		int count = 0;
		System.out.println("count before decrement:" + count);
		boolean flag = (count-- == 0);
		System.out.println("flag after count decrement:" + flag);
		System.out.println("count after decrement:" + count);
		String s = "abc";
		int i = s.length() - 1;
		String s1 = s.substring(0, i);
		String s2 = s.substring(i);
		System.out.println("s1:" + s1);
		System.out.println("s2:" + s2);
		String paren = "(()(()";
		System.out.println("original:" + paren);
		String sb = new StringBuilder(paren).reverse().toString();
		System.out.println("reversed:" + sb);
	}
}
