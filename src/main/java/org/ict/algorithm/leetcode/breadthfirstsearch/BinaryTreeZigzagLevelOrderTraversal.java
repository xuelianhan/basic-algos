package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


/**
 * 
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. 
 * (ie, from left to right, then right to left for the next level and alternate between).
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 * input: [1,2,3,4,null,null,5]
 *     1
 *    / \
 *   2   3
 *  / \ / \ 
 * 4  null 5
 * expected: [[1],[3,2],[4,5]]
 * LC103
 * @see https://medium.com/@harycane/binary-tree-zigzag-level-order-traversal-5b96a3e1b767
 * @see https://www.geeksforgeeks.org/zig-zag-level-order-traversal-of-a-tree-using-single-queue/
 * @see https://www.geeksforgeeks.org/zigzag-tree-traversal/
 */
public class BinaryTreeZigzagLevelOrderTraversal {
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		//root.left.right = new TreeNode(6);
		//root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(5);
		List<List<Integer>> result = zigzagLevelOrderV2(root);
		System.out.println(result);
	}
	
	public static List<List<Integer>> zigzagLevelOrderV2(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		/* The level of binary tree */
		int level = 1;
		while (!queue.isEmpty()) {
			/* queue size indicates number of nodes at each level */
			int size = queue.size();
			List<Integer> temp = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				if (level % 2 == 0) {
					temp.add(0, cur.val);
				} else {
					temp.add(cur.val);
				}
				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}
			level++;
			result.add(temp);
		}
		return result;
	}
	
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Stack<TreeNode> rightToLeft = new Stack<>();
		Stack<TreeNode> leftToRight = new Stack<>();
		/* Push first level to first stack rightToLeft. */
		rightToLeft.add(root);
		while (!rightToLeft .isEmpty() || !leftToRight.isEmpty()) {
			List<Integer> listOne = new ArrayList<>();
			while (!rightToLeft.isEmpty()) {
				TreeNode tempNode = rightToLeft.peek();
				rightToLeft.pop();
				listOne.add(tempNode.val);
				if (tempNode.left != null) {
					leftToRight.add(tempNode.left);
				}
				if (tempNode.right != null) {
					leftToRight.add(tempNode.right);
				}
			}
			if (listOne.size() > 0) {
				result.add(listOne);
			}
			
			List<Integer> listTwo = new ArrayList<>();
			while (!leftToRight.isEmpty()) {
				TreeNode tempNode = leftToRight.peek();
				leftToRight.pop();
				listTwo.add(tempNode.val);
				if (tempNode.right != null) {
					rightToLeft.add(tempNode.right);
				}
				if (tempNode.left != null) {
					rightToLeft.add(tempNode.left);
				}
			}
			if (listTwo.size() > 0) {
				result.add(listTwo);
			}
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
	    
	    public String toString() {
	    	return String.valueOf(val);
	    }
 	}
}
