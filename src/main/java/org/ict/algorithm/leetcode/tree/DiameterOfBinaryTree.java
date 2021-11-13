package org.ict.algorithm.leetcode.tree;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is
 * the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is
 * represented by the number of edges between them.
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 *
 * Input: root = [1,2]
 * Output: 1
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -100 <= Node.val <= 100
 * LC543
 *
 * [4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]
 * expected:8
 */
public class DiameterOfBinaryTree {

    private static int max = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        TreeNode two = new TreeNode();
        two.val = 2;
        root.val = 1;
        root.left = two;
        int result = diameterOfBinaryTree(root);
        System.out.println(result);
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private static int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}
