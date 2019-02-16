package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 
 *Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
 *find all unique combinations in candidates where the candidate numbers sums to target.
 *The same repeated number may be chosen from candidates unlimited number of times.
 *Note:
 *All numbers (including target) will be positive integers.
 *The solution set must not contain duplicate combinations.
 *Example 1:
 *Input: candidates = [2,3,6,7], target = 7,
 *A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * 
 * LC39
 * @see https://www.geeksforgeeks.org/must-do-coding-questions-for-companies-like-amazon-microsoft-adobe/
 * @see https://www.geeksforgeeks.org/backtracking-algorithms/
 */
public class CombinationSum {
	
	public static void main(String[] args) {
		int[] candidates = {2, 3, 5};
		int target = 8;
		System.out.println(combinationSumV2(candidates, target));
		System.out.println(combinationSumV1(candidates, target));
	}
	
	public static List<List<Integer>> combinationSumV2(int[] candidates, int target) {
	    Arrays.sort(candidates);
	    int i=0, size = candidates.length, sum=0;
	    // Use Double-Stacks model
	    Stack<Integer> combi = new Stack<>(), indices = new Stack<>();
	    List<List<Integer>> result = new ArrayList<>();
	    while (i < size) {
	    	if (sum + candidates[i]>= target) {
	    		if (sum + candidates[i] == target) {
	    			combi.push(candidates[i]);
	    			result.add(new ArrayList<>(combi));
	    			combi.pop();
	    		}
	    		System.out.println(">="+ target +", i:" + i + ", combi:" + combi + ", indices:" + indices);
	    		// indices stack and combination stack should have the same size all the time
	    		if (!indices.empty()){
	    			sum -= combi.pop();
	    			i = indices.pop();
	    			while (i == size-1 && !indices.empty()) {
	    				i = indices.pop();
	    				sum -= combi.pop();
	    			}
	    		}
	    		i++;
	    	} else {
	    		combi.push(candidates[i]);
	    		sum +=candidates[i];
	    		indices.push(i);
	    		System.out.println("<"+ target +", i:" + i + ", combi:" + combi + ", indices:" + indices);
	    	}
	    }
	    return result;
	}
	
	/**
	 * Backtracking is an algorithmic-technique for solving problems recursively by trying to build a solution incrementally, 
	 * one piece at a time, 
	 * removing those solutions that fail to satisfy the constraints of the problem at any point of time 
	 * (by time, here, is referred to the time elapsed till reaching any level of the search tree).
	 * For example, consider the SudoKo solving Problem, we try filling digits one by one. 
	 * Whenever we find that current digit cannot lead to a solution, we remove it (backtrack) and try next digit. 
	 * This is better than naive approach (generating all possible combinations of digits and then trying every combination one by one) 
	 * as it drops a set of permutations whenever it backtracks.
	 * @see https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
	 * 
	 * @see https://en.wikipedia.org/wiki/Backtracking
	 * The backtracking algorithm enumerates a set of partial candidates that, 
	 * in principle, could be completed in various ways to give all the possible solutions to the given problem. 
	 * The completion is done incrementally, by a sequence of candidate extension steps.
	 * Conceptually, the partial candidates are represented as the nodes of a tree structure, the potential search tree. 
	 * Each partial candidate is the parent of the candidates that differ from it by a single extension step; 
	 * the leaves of the tree are the partial candidates that cannot be extended any further.
	 * The backtracking algorithm traverses this search tree recursively, from the root down, in depth-first order. 
	 * At each node c, the algorithm checks whether c can be completed to a valid solution. 
	 * If it cannot, the whole sub-tree rooted at c is skipped (pruned). 
	 * Otherwise, the algorithm (1) checks whether c itself is a valid solution, and if so reports it to the user; 
	 * and (2) recursively enumerates all sub-trees of c. 
	 * The two tests and the children of each node are defined by user-given procedures.
	 * Therefore, the actual search tree that is traversed by the algorithm is only a part of the potential tree. 
	 * The total cost of the algorithm is the number of nodes of the actual tree times the cost of obtaining and processing each node. 
	 * This fact should be considered when choosing the potential search tree and implementing the pruning test.
	 * @param nums
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> combinationSumV1(int[] nums, int target) {
	    List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);
	    backtrack(list, new ArrayList<>(), nums, target, 0);
	    return list;
	}

	private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
	    if(remain < 0) return;
	    else if(remain == 0) list.add(new ArrayList<>(tempList));
	    else{ 
	        for(int i = start; i < nums.length; i++){
	            tempList.add(nums[i]);
	            backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
	            tempList.remove(tempList.size() - 1);
	        }
	    }
	}
}
