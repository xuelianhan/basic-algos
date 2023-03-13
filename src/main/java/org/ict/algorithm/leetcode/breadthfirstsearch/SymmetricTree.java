package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 *Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *    1
 *   / \
 *  2   2
 * / \ / \
 *3  4 4  3
 *But the following [1,2,2,null,3,null,3] is not:
 *    1
 *   / \
 *  2   2
 *   \   \
 *   3    3
 *Note:
 *Bonus points if you could solve it both recursively and iteratively.
 * LC101
 * @see <a href="https://www.geeksforgeeks.org/check-symmetric-binary-tree-iterative-approach/"></a>
 * @see <a href="https://www.geeksforgeeks.org/symmetric-tree-tree-which-is-mirror-image-of-itself/"></a>
 * @see <a href="https://stackoverflow.com/questions/8436623/check-if-a-binary-tree-is-a-mirror-image-or-symmetric"></a>
 *
 */
public class SymmetricTree {
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		
		boolean result = isSymmetric(root);
		System.out.println(result);
	}

	public boolean isSymmetricV2(TreeNode root) {
		return root == null || isSymmetricRecursive(root.left, root.right);
	}

	public boolean isSymmetricRecursive(TreeNode left, TreeNode right) {
		if (left == null || right == null) {
			return left == right;
		}
		if (left.val != right.val) {
			return false;
		}
		return (isSymmetricRecursive(left.left, right.right)
				&& isSymmetricRecursive(left.right, right.left));
	}


	/**
	 * In this post, iterative approach is discussed.We use queue here.
	 * Note that for a symmetric tree whose elements at every level are palindromic.
	 * In example 2, at the leaf-level, the elements are not palindromic.
	 * In other words, 
	 * 1. The left child of left subtree = right child of right subtree.
	 * 2. The right child of left subtree = left child of right subtree.
	 * If we insert the left child of left subtree first followed by the right child of right subtree
	 * in the queue, we only need to ensure these 2 node's value are equal.
	 * Similarly, if we insert the right child of left subtree followed by the left child of right subtree
	 * in the queue, we also need to ensure there values are equal too.
	 * 
	 * @see <a href="https://www.geeksforgeeks.org/check-symmetric-binary-tree-iterative-approach/"></a>
	 * @see <a href="https://stackoverflow.com/questions/8436623/check-if-a-binary-tree-is-a-mirror-image-or-symmetric"></a>
	 * @param root
	 * @return
	 */
	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root.left);
		queue.add(root.right);
		while(!queue.isEmpty()) {
			/* Remove the first 2 Nodes to check for equality */
			TreeNode curLeft = queue.poll();
			TreeNode curRight = queue.poll();
			
			/* If both are null, continue for check further nodes */
			if (curLeft == null && curRight == null) {
				continue;
			}
			/* if Only one is null, means inequality, return false*/
			if ((curLeft == null && curRight != null) || 
			    (curLeft != null && curRight == null)) {
				return false;
			}
			/* if Both left and right nodes are not null, but have
			   different values,  means inequality, return false*/
			if (curLeft.val != curRight.val) {
				return false;
			}
			/* Note the order of insert nodes into the queue */
			queue.add(curLeft.left);
			queue.add(curRight.right);
			queue.add(curLeft.right);
			queue.add(curRight.left);
		}
		/* if the flow reaches here, return true*/
        return true;
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
