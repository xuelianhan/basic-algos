package org.ict.algorithm.leetcode.breadthfirstsearch;

import org.ict.algorithm.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the maximum width of the given tree.
 * The maximum width of a tree is the maximum width among all levels.
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
 * where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level
 * are also counted into the length calculation.
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 *
 * Example 1:
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
 *
 * Example 2:
 * Input: root = [1,3,2,5,null,null,9,6,null,7]
 * Output: 7
 * Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
 *
 * Example 3:
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width exists in the second level with length 2 (3,2).
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 3000].
 * -100 <= Node.val <= 100
 * @author sniper
 * @date 21 Apr, 2023
 * LC662, Medium
 */
public class MaximumWidthOfBinaryTree {

    /**
     * For a perfect binary tree, if the root node is depth 1,
     * then the number of nodes at each level is 2n-1,
     * and the position of each node is one of [1, 2n-1].
     * Suppose the position of a node is i,
     * then the positions of its left and right child nodes can be directly calculated as 2i and 2i+1.
     * The queue is a pair of nodes and their current position.
     * When entering the loop of a new layer,
     * first determine whether there is only one node in the layer,
     * reset the coordinate position of the node if it is,
     * then save the position of the first node as the leftmost position,
     * and then update the position of the right node for the nodes traversed,
     * and then calculate the width update result after traversing a layer of nodes res.
     * -----------------------------------------------------------------------------------
     *                    1
     *                  3  2
     *                5
     * e.g. root = [1,3,2,5]
     * queue:(1, 1)
     * size:1, left:1, right:1, cur:(1, 1), right:1, queue:(3, 2), (2, 3), res = max(0, 0) = 0
     * size:2, left:2, right:2, cur:(3, 2), right:2, queue:(2, 3), (5, 4),
     * size:2, left:2, right:2, cur:(2, 3), right:3, queue:(5, 4), res = max(0, 2) = 2
     * size:1, left:1, right:1, cur:(5, 1), right:1, queue: null, res = max(2, 1) = 2
     * return res:2
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTreeV1(TreeNode root) {
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

    public int widthOfBinaryTree(TreeNode root) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 1));
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //Please compare with widthOfBinaryTreeV1 at the same place, you will find surprise.
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
}
