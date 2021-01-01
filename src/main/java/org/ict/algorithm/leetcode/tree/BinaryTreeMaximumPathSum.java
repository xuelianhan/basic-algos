package org.ict.algorithm.leetcode.tree;

public class BinaryTreeMaximumPathSum {

    private int ans;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        recursion(root);
        return ans;
    }

    public int recursion(TreeNode root) {
         if (null == root) {
             return 0;
         }
         int left = Math.max(0, recursion(root.left));
         int right = Math.max(0, recursion(root.right));
         ans = Math.max(ans, left + right + root.val);
         return Math.max(left, right) + root.val;
    }

    public static void main(String[] args) {

    }

}
