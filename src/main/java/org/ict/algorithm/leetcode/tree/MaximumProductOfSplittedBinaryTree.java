package org.ict.algorithm.leetcode.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * Given the root of a binary tree, split the binary tree into two subtrees
 * by removing one edge such that the product of the sums of the subtrees is maximized.
 *
 * Return the maximum product of the sums of the two subtrees. Since the answer may be too large,
 * return it modulo 10^9 + 7.
 *
 * Note that you need to maximize the answer before taking the mod and not after taking it.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 * Example 2:
 *
 *
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 5 * 10^4].
 * 1 <= Node.val <= 10^4
 * @author sniper
 * @date 10 Dec, 2022
 * Google,Amazon,Meta, Phone
 * LC1339
 */
public class MaximumProductOfSplittedBinaryTree {

    public static void main(String[] args) {
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);

        TreeNode two = new TreeNode(2);
        two.left = four;
        two.right = five;
        TreeNode three = new TreeNode(3);
        three.left = six;

        TreeNode one = new TreeNode(1);
        one.left = two;
        one.right = three;

        MaximumProductOfSplittedBinaryTree instance = new MaximumProductOfSplittedBinaryTree();
        int result = instance.maxProduct(one);
        System.out.println(result);
    }


    /**
     * Time Cost 59ms
     *            1
     *          /   \
     *         2     3
     *       /\     /
     *      4  5   6
     *  ----------------------
     *            21
     *          /   \
     *        11     9
     *       /\     /
     *      4  5   6
     * ------------------------
     * sumSet: 4, 5, 21, 6, 9, 11
     * 4 * (21 - 4) = 4 * 17 = 68, max = Math.max(0, 68) = 68
     * 5 * (21 - 5) = 5 * 16 = 80, max = Math.max(68, 80) = 80
     * 21 * (21 - 21) = 0, max = Math.max(0, 80) = 80
     * 6 * (21 - 6) = 6 * 15 = 90, max = Math.max(80, 90) = 90
     * 9 * (21 - 9) = 9 * 12 = 108, max = Math.max(90, 108) = 108
     * 11 * (21 - 11) = 11 * 10 = 110, max = Math.max(108, 110) = 110
     * ------------------
     * return 110 % (1e9 + 7)
     *
     * Hints:
     * If we know the sum of a subtree,
     * the answer is max( (total_sum – subtree_sum) * subtree_sum) in each node.
     * Converted the given tree into a sum tree.
     * In the sum tree, every node contains the sum of its left subtree and right subtree.
     * When you have the sum tree,
     * then what are you waiting for just split from every possible edge and get the maximum result.
     *
     * Time Complexity O(N)
     * where N is the number of nodes in the Binary Tree.
     *
     * Space Complexity O(H)
     * where H is the height of the Binary Tree, it’s the stack depth of the recursion.
     * In the best case, the binary tree is balanced, the height is O(logN)
     * In the worst case, the binary tree is a skewed binary tree, the height is O(N)
     * @param root
     * @return
     */
    public int maxProduct(TreeNode root) {
        Set<Long> sumSet = new HashSet<>();
        int total = dfs(root, sumSet);
        int mod = (int)1e9 + 7;
        long max = 0;
        for (long sum : sumSet) {
            max = Math.max(max, sum * (total -sum));
        }
        return (int)(max % mod);
    }

    public int dfs(TreeNode node, Set<Long> sumSet) {
        if (null == node) {
            return 0;
        }
        node.val += dfs(node.left, sumSet);
        node.val += dfs(node.right, sumSet);
        sumSet.add((long)node.val);
        return node.val;
    }

}
