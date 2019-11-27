package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * Example: Input: [1,2,3,null,5,null,4] Output: [1, 3, 4] 
 * LC199Explanation:
 * 
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 * 
 * 
 */
public class BinaryTreeRightSideView {
	
	/**
	 * reverse level traversal
	 * @param root
	 * @return
	 */
	public List<Integer> rightSideViewV2(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (i == 0) {
					result.add(node.val);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				if (node.left != null) {
					queue.offer(node.left);
				}
			}
		}
		return result;
	}

	/**
	 * Recursive invoke root, right and left
	 * @param root
	 * @return
	 */
	public List<Integer> rightSideViewV1(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		rightView(root, result, 0);
		return result;
	}
	
	private void rightView(TreeNode node, List<Integer> result, int depth) {
		if (node == null) {
			return;
		}
		if (result.size() == depth) {
			result.add(node.val);
		}
		rightView(node.right, result, depth + 1);
		rightView(node.left, result, depth + 1);
	}
	 
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
