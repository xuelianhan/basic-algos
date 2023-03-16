package org.ict.algorithm.leetcode.tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * Given the root of a binary tree, determine if it is a complete binary tree.
 *
 * In a complete binary tree, every level,
 * except possibly the last, is completely filled,
 * and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full
 * (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 100].
 * 1 <= Node.val <= 1000
 * @author sniper
 * @date 15 Mar, 2023
 * LC958, Medium
 */
public class CheckCompletenessBinaryTree {

    /**
     * Intuition
     * You can imagine tree flat into a linear container like queue:
     * [1,2,3,4,5,null,7]
     * [1,2,3,4,5,6]
     * if we encountered null-element, afterwards we encounter no-null element, we could
     * return false immediately. we could return true if all the nodes has been visited.
     *
     * @param root
     * @return
     */
    public boolean isCompleteTreeV1(TreeNode root) {
        /**
         * Don't use ArrayDeque here, because ArrayDeque.offer(node)
         * not allow null-node.
         * If you insist on using ArrayDeque, the TreeNode should be wrapper as Optional<TreeNode>
         */
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean found = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                found = true;
            } else {
                if (found) {
                    return false;
                }
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return found;
    }

    public boolean isCompleteTree(TreeNode root) {
        Queue<Optional<TreeNode>> queue = new ArrayDeque<>();
        queue.add(Optional.ofNullable(root));
        boolean found = false;
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll().orElse(null);
            if (cur == null) {
                found = true;
            } else {
                if (found) {
                    return false;
                }
                queue.add(Optional.ofNullable(cur.left));
                queue.add(Optional.ofNullable(cur.right));
            }
        }
        return true;
    }
}
