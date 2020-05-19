package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2:
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.
 * 
 *
 */
/**
 * Definition for a binary tree node. 
 * public class TreeNode { 
 * 	  int val; 
 * 	  TreeNode left;
 *    TreeNode right;
 *    TreeNode() {} 
 *    TreeNode(int val) { 
 *       this.val = val;
 *    }
 *    
 *    TreeNode(int val, TreeNode left, TreeNode right) { 
 *       this.val = val;
 *       this.left = left;
 *       this.right = right; 
 *    }
 * }
 * 
 * LC513
 */
public class FindBottomLeftTreeValue {

	public int findBottomLeftValue(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			root = queue.poll();
			if (root.right != null) {
				queue.add(root.right);
			}
			if (root.left != null) {
				queue.add(root.left);
			}
		}
		return root.val;
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
