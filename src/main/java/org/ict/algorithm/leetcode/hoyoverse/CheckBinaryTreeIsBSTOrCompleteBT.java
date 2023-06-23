package org.ict.algorithm.leetcode.hoyoverse;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Description
 * Given a binary tree in which the nodes are known to have no duplicate values,
 * determine whether the binary tree is a search binary tree or a complete binary tree.
 * Output description: Whether the tree is a search binary tree or a complete binary tree.
 * Data range: The number of nodes in the binary tree satisfies 0≤n≤500000 , and the values in the binary tree satisfy 0≤val≤100000
 * Requirements: space complexity O(n), time complexity O(n)
 *
 * Note: The empty subtree is considered to be both a search binary tree and a complete binary tree.
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
 *
 * @author sniper
 * @date 21 Jun 2023
 * NC60, Medium
 * LC98 + LC958
 */
public class CheckBinaryTreeIsBSTOrCompleteBT {

    public boolean[] judgeIt(TreeNode root) {
        boolean f1 = isBinarySearchTree(root);
        boolean f2 = isCompleteBinaryTree(root);
        return new boolean[] {f1, f2};
    }

    /**
     * In-Order Traversal
     * @param head
     * @return
     */
    private boolean isBinarySearchTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        TreeNode pre = null;
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (pre != null && pre.val > node.val) {
                    return false;
                }
                pre = node;
                cur = node.right;
            }
        }
        return true;
    }

    /**
     * Level Order Traversal
     * e.g. head = [1,2,3,4,5,null,7]
     *              1
     *            /  \
     *           2    3
     *         /  \    \
     *        4    5    7
     *
     * @param head
     * @return
     */
    private boolean isCompleteBinaryTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        boolean foundEmpty = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                foundEmpty = true;
            } else {
                if (foundEmpty) {
                    return false;
                }
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return foundEmpty;
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
