package org.ict.algorithm.leetcode.tree;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 * LC110
 */
public class BalancedBinaryTree {


    public boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }
        return helper(root) != -1;
    }

    public int helper(TreeNode node) {
        if (null == node) {
            return 0;
        }
        int leftHeight = helper(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = helper(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        int abs = Math.abs(leftHeight - rightHeight);
        if (abs > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    public boolean isBalancedV2(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.abs(left - right) <= 1 && isBalancedV2(root.left) && isBalancedV2(root.right);
    }
    /**
     * Wrong answer:
     *     public boolean isBalanced(TreeNode root) {
     *         if (null == root) {
     *             return true;
     *         }
     *         return helper(root, 0) <= 1;
     *     }
     *
     *     public int helper(TreeNode node, int height) {
     *         if (null == node) {
     *             return height;
     *         }
     *         int leftHeight = helper(node.left, height + 1);
     *         int rightHeight = helper(node.right, height + 1);
     *         return Math.abs(leftHeight - rightHeight);
     *     }
     */
}
