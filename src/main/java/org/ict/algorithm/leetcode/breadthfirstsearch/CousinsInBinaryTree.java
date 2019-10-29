package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a binary tree, the root node is at depth 0, 
 * and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * We are given the root of a binary tree with unique values,
 * and the values x and y of two different nodes in the tree.
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 * 
 * Example 1:
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 * 
 * Example 2:
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 * 
 * Example 3:
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 * 
 * Note:
 * The number of nodes in the tree will be between 2 and 100.
 * Each node has a unique integer value from 1 to 100.
 * LC993
 *
 * @author Frimish
 */
public class CousinsInBinaryTree {
	
	/**
	 * The level-order traversal is the most time-efficient solution for this problem since we only go as deep as the first potential cousin. 
	 * The memory complexity is O(n/2) to accommodate the longest level, 
	 * vs. O(h) for recursive solutions, where h is the height of the tree (could be n in the worst case).
	 * We use queue q to iterate through the current level nodes and populate their children into q1. 
	 * We also insert nullptr into q1 after inserting each node's children (to separate siblings froum cousins).
	 * If we find a node with value x or y, we have one potential cousin. 
	 * If we find another potential cousin, we return true if they are not siblings (nullptr sets siblings to false). 
	 * If we finished the level with just one potential cousin, we stop and return false.
	 * 
	 * @param root
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isCousins(TreeNode root, int x, int y) {
		if (root == null) {
			 return false;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			/* queue size indicates number of nodes at each level */
		    int size = queue.size();
		    boolean xExist = false;
		    boolean yExist = false;
		    for (int i = 0; i < size; i++) {// for-loop used to control the search breadth.
		    	TreeNode cur = queue.poll();
		   	    if (cur.val == x) xExist = true;
		   	    if (cur.val == y) yExist = true;
		   	    //level-order traversal
		   	    if (cur.left != null && cur.right != null) {
		   	   	    if (cur.left.val == x && cur.right.val == y) {
		   	   	   	   return false;
		   	   	    }
		   	   	    if (cur.left.val == y && cur.right.val == x) {
		   	   	   	   return false;
		   	   	    }
		   	    }
		   	    if (cur.left != null) {
		   	    	queue.offer(cur.left);
		   	    }
		   	    if (cur.right != null) {
		   	    	queue.offer(cur.right);
		   	    }
		    }
		    if(xExist && yExist) {
		    	return true;
		    }
		}
	    return false; 
	 }
	 
	 
	 TreeNode xParent = null;
	 TreeNode yParent = null;
	 int xDepth = -1, yDepth = -1;
	 public boolean isCousinsV2(TreeNode root, int x, int y) {
		 dfs(root, x, y, 0, null);
		 return (xDepth == yDepth && xParent != yParent) ? true : false;
	 }
	 
	 private void dfs(TreeNode root, int x, int y, int depth, TreeNode parent) {
		 if (root == null) {
			 return;
		 }
		 if (root.val == x) {
			 xParent = parent;
			 xDepth = depth;
		 } else if(root.val == y) {
			 yParent = parent;
			 yDepth = depth;
		 }
		 dfs(root.left, x, y, depth + 1, root);
		 dfs(root.right, x, y, depth + 1, root);
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
