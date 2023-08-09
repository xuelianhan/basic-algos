package org.ict.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sniper
 * @date 08 Aug 2023
 */
public class ConstructPerfectBinaryTreeByHeight {

    /**
     * Construct Perfect binary tree.
     *          0
     *         /  \
     *       1     2
     *      / \   / \
     *     3   4 5   6
     * @param depth the depth of the perfect-binary-tree
     * @return
     */
    public TreeNode buildV1(int depth) {
        if (depth < 0) {
            return null;
        }
        if (depth == 0) {
            return new TreeNode(0);
        }
        int i = 0;
        TreeNode root = new TreeNode(i);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            i++;
            if (i > depth) {
                break;
            } else {
                for (int j = 0; j < size; j++) {
                    TreeNode node = queue.poll();
                    node.left = new TreeNode(2 * node.val + 1);
                    node.right = new TreeNode(2 * node.val + 2);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }

        }
        return root;
    }

    /**
     * Construct Perfect binary tree.
     *          0
     *         /  \
     *       1     1
     *      / \   / \
     *     2   2 2   2
     * @param depth the depth of the perfect-binary-tree
     * @return
     */
    public TreeNode build(int depth) {
        if (depth < 0) {
            return null;
        }
        if (depth == 0) {
            return new TreeNode(0);
        }
        int i = 0;
        TreeNode root = new TreeNode(i);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            i++;
            if (i > depth) {
                break;
            } else {
                for (int j = 0; j < size; j++) {
                    TreeNode node = queue.poll();
                    node.left = new TreeNode(i);
                    node.right = new TreeNode(i);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }

        }
        return root;
    }
}
