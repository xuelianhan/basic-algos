package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.*;

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
 * LC199, Medium, frequency=112
 */
public class BinaryTreeRightSideView {
	
	/**
	 * reverse level traversal
	 * -------------------------------------------------------
	 * from collections import deque
	 *
	 * class TreeNode:
	 *     def __init__(self, val=0, left=None, right=None):
	 *         self.val = val
	 *         self.left = left
	 *         self.right = right
	 *
	 *  def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
	 *         result = []
	 *         if not root:
	 *             return result
	 *
	 *         queue = deque()
	 *         queue.append(root)
	 *
	 *         while queue:
	 *             size = len(queue)
	 *             for i in range(size):
	 *                 node = queue.popleft()
	 *                 if i == 0:
	 *                     result.append(node.val)
	 *
	 *                 if node.right:
	 *                     queue.append(node.right)
	 *                 if node.left:
	 *                     queue.append(node.left)
	 *
	 *         return result
	 * ---------------------------------------------------------
	 * @param root
	 * @return
	 */
	public List<Integer> rightSideViewV3(TreeNode root) {
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
				/**
				 * If we put right firstly, put left secondly,
				 * then the first element is the target seeing from right-view.
				 */
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

	public List<Integer> rightSideViewV2(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				/**
				 * If we put left firstly, put right secondly,
				 * then the last element is the target seeing from the right-view.
				 */
				if (i == size - 1) {
					res.add(cur.val);
				}
				if (cur.left != null) {
					queue.offer(cur.left);
				}
				if (cur.right != null) {
					queue.offer(cur.right);
				}
			}
		}
		return res;
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
