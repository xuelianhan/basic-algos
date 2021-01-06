package org.ict.algorithm.leetcode.tree;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = Math.max(0, maxDepth(root.left));
        int right = Math.max(0, maxDepth(root.right));
        return Math.max(left, right) + 1;
    }
}
