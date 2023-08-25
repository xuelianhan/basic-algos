package org.ict.algorithm.leetcode.breadthfirstsearch;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Given the root of a binary tree,
 * the level of its root is 1,
 * the level of its children is 2, and so on.
 *
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 *
 * Example 1:
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 * Example 2:
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^5 <= Node.val <= 10^5
 *
 * @author sniper
 * @date 25 Aug 2023
 * LC1161, Medium
 */
public class MaximumLevelSumOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode twoLeft = new TreeNode(7);
        TreeNode twoRight = new TreeNode(0);
        TreeNode threeLeft = new TreeNode(7);
        TreeNode threeRight = new TreeNode(-8);
        twoLeft.left = threeLeft;
        twoLeft.right = threeRight;
        root.left = twoLeft;
        root.right = twoRight;
        MaximumLevelSumOfBinaryTree instance = new MaximumLevelSumOfBinaryTree();
        int res = instance.maxLevelSum(root);
        System.out.println(res);
    }

    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        /**
         * e.g. root = [-100,-200,-300,-20,-5,-10,null]
         */
        int maxSum = Integer.MIN_VALUE;
        int level = 0;
        int maxLevel = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelSum += cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            level++;
            if (levelSum > maxSum) {
                maxSum = levelSum;
                maxLevel = level;
            }
        }

        return maxLevel;
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
