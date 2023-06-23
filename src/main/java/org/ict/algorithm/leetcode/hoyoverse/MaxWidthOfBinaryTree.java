package org.ict.algorithm.leetcode.hoyoverse;

/**
 * There is only one node in the first layer of this tree,
 * so the width of the first layer is 1,
 * the distance from the left-most to the right-most of the second layer is 2,
 * so the width is 2,
 * and the distance from the left-most 4 to the right-most 5 of the third layer is 4,
 * so the width is 4.
 * The maximum width of this tree is 4.
 *
 * Example 1:
 * Input: {1,2,3,4,#,4,5}
 * Expected: 4
 *
 * Example 2:
 * Input: {1}
 * Expected 1
 *
 * Example 3:
 * Input: {1,2,3,4,#,4,5,6,#,1}
 * Expected: 5
 *
 * @author sniper
 * @date 21 Jun 2023
 * NC204, Medium,
 * LC662, Medium
 */
public class MaxWidthOfBinaryTree {

    public int widthOfBinaryTree (TreeNode root) {
        return 0;
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
