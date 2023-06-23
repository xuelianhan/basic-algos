package org.ict.algorithm.leetcode.hoyoverse;


import java.util.LinkedList;
import java.util.Queue;

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
 * LC662, Medium,
 * @see org.ict.algorithm.leetcode.breadthfirstsearch.MaximumWidthOfBinaryTree
 */
public class MaxWidthOfBinaryTree {

    public int widthOfBinaryTree (TreeNode root) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 1));
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            /**
             * Reset seq to 1 while current layer has only one node.
             * Suppose given a binary tree, every layer has one node, like the following:
             *        1     1
             *      2         2
             *    3             3
             *  ...              ...
             * 32                  32
             *  2^32 overflow for int type in Java.
             */
            if (size == 1) {
                queue.peek().seq = 1;
            }
            int left = queue.peek().seq;
            int right = left;
            for (int i = 0; i < size; i++){
                Pair cur = queue.poll();
                right = cur.seq;
                if (cur.node.left != null) {
                    queue.offer(new Pair(cur.node.left, right * 2));
                }
                if (cur.node.right != null) {
                    queue.offer(new Pair(cur.node.right, right * 2 + 1));
                }
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    static class Pair {
        TreeNode node;
        int seq;

        public Pair(TreeNode node, int seq) {
            this.node = node;
            this.seq = seq;
        }
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
