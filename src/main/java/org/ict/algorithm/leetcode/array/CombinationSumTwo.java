package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Given a collection of candidate numbers (candidates) and a target number (target), 
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 *
 * 
 */
public class CombinationSumTwo {

	public List<List<Integer>> combinationSum2V1(int[] cand, int target) {
	    Arrays.sort(cand);
	    List<List<Integer>> res = new ArrayList<List<Integer>>();
	    List<Integer> path = new ArrayList<Integer>();
	    dfs_com(cand, 0, target, path, res);
	    return res;
	}
	void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
	    if (target == 0) {
	        res.add(new ArrayList<>(path));
	        return ;
	    }
	    if (target < 0) return;
	    for (int i = cur; i < cand.length; i++){
	        if (i > cur && cand[i] == cand[i-1]) continue;
	        path.add(path.size(), cand[i]);
	        dfs_com(cand, i+1, target - cand[i], path, res);
	        path.remove(path.size()-1);
	    }
	}
	
	public List<List<Integer>> combinationSum2V2(int[] candidates, int target) {
		   List<List<Integer>> list = new LinkedList<List<Integer>>();
		   Arrays.sort(candidates);
		   backtrack(list, new ArrayList<Integer>(), candidates, target, 0);
		   return list;
		}

		private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] cand, int remain, int start) {
		   
		   if(remain < 0) return; /** no solution */
		   else if(remain == 0) list.add(new ArrayList<>(tempList));
		   else{
		      for (int i = start; i < cand.length; i++) {
		         if(i > start && cand[i] == cand[i-1]) continue; /** skip duplicates */
		         tempList.add(cand[i]);
		         backtrack(list, tempList, cand, remain - cand[i], i+1);
		         tempList.remove(tempList.size() - 1);
		      }
		   }
		}
}
