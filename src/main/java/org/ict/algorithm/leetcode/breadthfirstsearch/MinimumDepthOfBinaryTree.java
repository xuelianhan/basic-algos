package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 * Example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its minimum depth = 2.
 * LC111
 * @see https://www.geeksforgeeks.org/find-minimum-depth-of-a-binary-tree/
 */
public class MinimumDepthOfBinaryTree {
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		//root.right.left = new TreeNode(4);
		//root.right.right = new TreeNode(3);
		int minDepth = minDepth(root);
		System.out.println(minDepth);
	}
	
	public static int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int minDepth = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				/* If this is the first leaf seen so far, return it as answer */
				if (cur.left == null && cur.right == null) {
					return minDepth;
				}
				/* If left subtree is not null, add it to queue */
				if (cur.left != null) {
					queue.add(cur.left);
				}
				/* If right subtree is not null, add it to queue */
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}
			minDepth++;
		}
		return 0;
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
