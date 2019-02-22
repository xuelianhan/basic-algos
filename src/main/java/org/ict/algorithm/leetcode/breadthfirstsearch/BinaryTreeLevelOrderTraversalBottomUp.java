package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 * LC107
 * 
 * @see https://stackoverflow.com/questions/12949690/java-arrays-how-to-add-elements-at-the-beginning
 * @see https://stackoverflow.com/questions/12779839/convert-a-queue-to-list
 */
public class BinaryTreeLevelOrderTraversalBottomUp {
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		List<List<Integer>> result = levelOrderBottom(root);
		System.out.println(result);
	}
	

	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result =  new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root == null) {
			return result;
		}
		queue.add(root);
		while (!queue.isEmpty()) {
			/* queue size indicates number of nodes at each level */
			int size = queue.size();
			List<Integer> temp = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (cur == null) {
					continue;
				}
				temp.add(cur.val);
				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}
			result.add(0, temp);
		}
		return result;
	}
	 
	/**
	 * Definition for a binary tree node.
	 */
	 public static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
}
