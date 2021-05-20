package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given the root of a binary tree, 
 * return the inorder traversal of its nodes'
 * values.
 * 
 * @author hxl
 */
public class BinaryTreeInorderTraversal {
	
	 public List<Integer> inorderTraversalV1(TreeNode root) {
		 List<Integer> list = new ArrayList<>();
		 helper(root, list);
		 return list;
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

	/**
	 * Left-->Root-->Right
	 * @param root
	 * @return
	 */
	 public List<Integer> inorderTraversalV2(TreeNode root) {
	 	List<Integer> list = new ArrayList<>();
	 	Stack<TreeNode> stack = new Stack<>();
	 	TreeNode cur = root;
	 	while (cur != null || !stack.isEmpty()) {
	 		if (cur != null) {
	 			stack.push(cur);
				//left
	 			cur = cur.left;
			} else {
				//middle
				cur = stack.pop();
				//middle
				list.add(cur.val);
				//right
				cur = cur.right;
			}
		}
	 	return list;
	 }


}
