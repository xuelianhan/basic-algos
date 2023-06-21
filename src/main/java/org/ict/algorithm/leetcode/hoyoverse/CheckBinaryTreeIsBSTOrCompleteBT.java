package org.ict.algorithm.leetcode.hoyoverse;

/**
 * Description
 * Given a binary tree in which the nodes are known to have no duplicate values,
 * determine whether the binary tree is a search binary tree or a complete binary tree.
 * Output description: Whether the tree is a search binary tree or a complete binary tree.
 * Data range: The number of nodes in the binary tree satisfies 0≤n≤500000 , and the values in the binary tree satisfy 0≤val≤100000
 * Requirements: space complexity O(n), time complexity O(n)
 *
 * Note: The empty sub-tree is considered to be both a search binary tree and a complete binary tree.
 * Example 1
 * Input: {2,1,3}
 * Return value: [true,true]
 *
 * Example 2
 * Input: {1,#,2}
 * Return value: [true,false]
 * Description:
 * Since the right son of a node is larger than the root node and there is no left subtree,
 * it is a search binary tree but not a complete binary tree
 *
 * Example 3
 * Input: {}
 * Return value: [true,true]
 * @author sniper
 * @date 21 Jun 2023
 * NC60, Medium
 */
public class CheckBinaryTreeIsBSTOrCompleteBT {

    public boolean[] judgeIt (TreeNode root) {
        return null;
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
