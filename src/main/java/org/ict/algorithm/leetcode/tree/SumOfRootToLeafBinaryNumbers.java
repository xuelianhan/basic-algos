package org.ict.algorithm.leetcode.tree;

import java.util.*;

/**
 * You are given the root of a binary tree where each node has a value 0 or 1.
 * Each root-to-leaf path represents a binary number starting with the most significant bit.
 * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1,
 * then this could represent 01101 in binary, which is 13.
 *
 * For all leaves in the tree,
 * consider the numbers represented by the path from the root to that leaf.
 *
 * Return the sum of these numbers. The answer is guaranteed to fit in a 32-bits integer.
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * Node.val is 0 or 1.
 *
 * @author sniper
 * @date 2021/12/1
 * LC1022
 */
public class SumOfRootToLeafBinaryNumbers {

    public static void main(String[] args) {
        TreeNode leaf1 = new TreeNode(0);
        TreeNode leaf2 = new TreeNode(1);
        TreeNode leaf3 = new TreeNode(0);
        TreeNode leaf4 = new TreeNode(1);
        TreeNode middle1 = new TreeNode(0);
        TreeNode middle2 = new TreeNode(1);
        TreeNode root = new TreeNode(1);
        middle1.left = leaf1;
        middle1.right = leaf2;
        middle2.left = leaf3;
        middle2.right = leaf4;
        root.left = middle1;
        root.right = middle2;
        int result = sumRootToLeaf(root);
        System.out.println(result);
    }

    public static int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * Intuition
     * Easily decompose this problem into 2 sub-problem:
     *
     * Find all path from root to leaves. DFS recursion should help do that.
     * Transform a path from base to base 10.
     * You can do this separately, and it will be a O(N^2) solution.
     * In my solution, I combine them together.
     *
     * Explanation:
     * We recursively pass the current value of path to the children.
     * If root == null, no value, return 0.
     * If root != null,
     * we double the value from its parent and add the node's value,
     * like the process of transforming base 2 to base 10.
     * 1  ->  2^0=1
     * 11  ->  1*2+1=2^1+1=2^2-1
     * 111  ->  (1*2+1)*2+1=2^2+2^1+1=2^3-1
     * 1111  ->  ((1*2+1)*2+1)*2+1=2^3+2^2+2^1+1=2^4-1
     *
     * In the end of recursion,
     * if root.left == root.right == null,
     * return the current val.
     *
     *
     * Complexity:
     * Time O(N)
     * Space O(H) for recursion.
     * @author lee215
     *
     * @param root
     * @param val
     * @return
     */
    public static int dfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        //Double the value from its parent and add the node's value
        val = val * 2 + root.val;
        return root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val);
    }


}
