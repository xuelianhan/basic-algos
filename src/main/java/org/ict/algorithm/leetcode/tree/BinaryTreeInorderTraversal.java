package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, 
 * return the inorder traversal of its nodes'
 * values.
 * LC94, Easy
 * @author siper
 */
public class BinaryTreeInorderTraversal {

	/**
	 * Understanding the following solution
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversalV3(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			while(cur != null) {
				stack.push(cur);
				cur = cur.left;//left
			}
			cur = stack.pop();//middle
			list.add(cur.val);//middle
			cur = cur.right;//right
		}
		return list;
	}

	/**
	 * Understanding the following solution
	 * Left-->Root-->Right
	 * @param root
	 * @return
	 */
	public static List<Integer> inorderTraversalV2(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			} else {
				TreeNode node = stack.pop();
				list.add(node.val);
				cur = node.right;
			}
		}
		return list;
	}

	/**
	 * Understanding the following solution
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversalV1(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		/**
		 * helperV1(root, list); is OK too.
		 */
		helper(root, list);
		return list;
	}

	public void helperV1(TreeNode node, List<Integer> list) {
		if (null == node) {
			return;
		}
		helper(node.left, list);
		list.add(node.val);
		helper(node.right, list);
	}

	public void helper(TreeNode node, List<Integer> list) {
		if (null == node) {
			return;
		}
		if (node.left != null) {
			helper(node.left, list);
		}
		list.add(node.val);
		if (node.right != null) {
			helper(node.right, list);
		}
	}

}
