package org.ict.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, 
 * return the inorder traversal of its nodes'
 * values.
 * 
 *
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


}
