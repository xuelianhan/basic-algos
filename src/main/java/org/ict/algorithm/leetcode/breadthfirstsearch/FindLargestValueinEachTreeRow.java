package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
 * LC515
 *
 */
public class FindLargestValueinEachTreeRow {

	public List<Integer> largestValues(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		List<Integer> result = new ArrayList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int max = Integer.MIN_VALUE, n = queue.size();
			for (int i = 0; i < n; i++) {
				root = queue.poll();
				max = Math.max(max, root.val);
				if (root.left != null) {
					queue.add(root.left);
				}
				if (root.right != null) {
					queue.add(root.right);
				}
			}
			result.add(max);
			
		}
		return result;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
