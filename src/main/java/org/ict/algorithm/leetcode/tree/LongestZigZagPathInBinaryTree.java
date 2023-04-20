package org.ict.algorithm.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given the root of a binary tree.
 * A ZigZag path for a binary tree is defined as follow:
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 * Return the longest ZigZag path contained in that tree.
 *
 * Example 1:
 * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * Output: 3
 * Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 *
 * Example 2:
 * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 * Output: 4
 * Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
 *
 * Example 3:
 * Input: root = [1]
 * Output: 0
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 5 * 10^4].
 * 1 <= Node.val <= 100
 *
 * @author sniper
 * @date 19 Apr, 2023
 * LC1372, Medium
 */
public class LongestZigZagPathInBinaryTree {

    public static void main(String[] args) {
        TreeNode root = generateTestDataOne();
        LongestZigZagPathInBinaryTree instance = new LongestZigZagPathInBinaryTree();
        int res = instance.longestZigZagV1(root);
        System.out.println(res);
    }

    public static TreeNode generateTestDataOne() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        node2.right = node4;
        node4.left = node5;
        node4.right = node6;
        node5.right = node7;
        return root;
    }

    /**
     * Understanding the following solution
     *                     1
     *                   /   \
     *                  2     3
     *                   \
     *                    4
     *                  /   \
     *                 5     6
     *                  \
     *                   7
     * -------------------------------------------------------------
     * e.g.root = [1,2,3,null,4,null,null,5,6,null,7]
     * dfs(1, res, 0, 0) --> res = max(0, max(0, 0)) = 0
     *    dfs(2, res, 1, 0) --> res = max(0, max(1, 0)) = 1
     *       dfs(4, res, 0, 2) --> res = max(1, max(0, 2)) = 2
     *          dfs(5, res, 3, 0) --> res = max(2, max(3, 0)) = 3
     *             dfs(7, res, 0, 4) --> res = max(3, max(0, 4)) = 4
     *          dfs(6, res, 0, 1) --> res = max(4, max(0, 1)) = 4
     *    dfs(3, res, 0, 1) --> res = max(4, max(0, 1)) = 4
     * -------------------------------------------------------------
     * def longestZigZag(self, root: Optional[TreeNode]) -> int:
     *         def dfs(root, l, r):
     *             if root is None:
     *                 return
     *             nonlocal res
     *             res = max(res, l, r)
     *             dfs(root.left, r + 1, 0)
     *             dfs(root.right, 0, l + 1)
     *
     *         res = 0
     *         dfs(root, 0, 0)
     *         return res
     * @param root
     * @return
     */
    public int longestZigZagV1(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res, 0, 0);
        return res[0];
    }

    /**
     * If we move left, it means the zigzag path increment by 1 based on the previous right result, this is(r + 1).
     * If we move right, it means the zigzag path increment by 1 based on the previous left result, this is (l + 1).
     * Move left, we need to pass previous right result plus one to left, and reset the right length r to zero.
     * Move right, we need to pass previous left result plus one to right, and reset the left length l to zero.
     *
     * @param root
     * @param res
     * @param l maximum zigzag length where A is reached by moving left from parent
     * @param r maximum zigzag length where A is reached by moving right from parent
     */
    private void dfs(TreeNode root, int[] res, int l, int r) {
        if (root == null) {
            return;
        }
        res[0] = Math.max(res[0], Math.max(l, r));
        //System.out.println("dfs(" + root.val + ", " + res[0] + ", " + l + ", " + r + ")");
        dfs(root.left, res, r + 1, 0);
        dfs(root.right, res, 0, l + 1);
    }


    /**
     * Understanding the following solution
     * @param root
     * @return
     */
    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Deque<ZigZag> queue = new ArrayDeque<>();
        if (root.left != null) {
            queue.offer(new ZigZag(root.left, "l", 1));
        }
        if (root.right != null) {
            queue.offer(new ZigZag(root.right, "r", 1));
        }
        while (!queue.isEmpty()) {
            ZigZag cur = queue.poll();
            res = Math.max(res, cur.zigZagSize);
            if (cur.node.left != null) {
                if ("l".equals(cur.from)) {
                    /**
                     * Consecutive left-left, reset zigZagSize to 1
                     */
                    queue.offer(new ZigZag(cur.node.left, "l", 1));
                }
                if ("r".equals(cur.from)) {
                    queue.offer(new ZigZag(cur.node.left, "l",  cur.zigZagSize + 1));
                }
            }
            if (cur.node.right != null) {
                if ("l".equals(cur.from)) {
                    queue.offer(new ZigZag(cur.node.right, "r",  cur.zigZagSize + 1));
                }
                if ("r".equals(cur.from)) {
                    /**
                     * Consecutive right-right, reset zigZagSize to 1
                     */
                    queue.offer(new ZigZag(cur.node.right, "r", 1));
                }
            }
        }
        return res;
    }

    static class ZigZag {
        TreeNode node;

        String from;

        int zigZagSize;

        public ZigZag(TreeNode node, String from, int zigZagSize) {
            this.node = node;
            this.from = from;
            this.zigZagSize = zigZagSize;
        }


    }
}
