package org.ict.algorithm.leetcode.tree;

/**
 * LC124
 * For this problem, a path is defined as any node sequence from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 */
public class BinaryTreeMaximumPathSum {

    private int ans;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        recursion(root);
        return ans;
    }

    public int recursion(TreeNode root) {
         if (null == root) {//1
             return 0;
         }
         int left = Math.max(0, recursion(root.left));//2
         int right = Math.max(0, recursion(root.right));//3
         ans = Math.max(ans, left + right + root.val);//4
         return Math.max(left, right) + root.val;//used in 2 and 3
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        BinaryTreeMaximumPathSum instance = new BinaryTreeMaximumPathSum();
        instance.maxPathSum(root);
    }

}
