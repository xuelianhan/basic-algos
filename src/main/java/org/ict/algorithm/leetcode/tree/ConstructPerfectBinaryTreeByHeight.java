package org.ict.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sniper
 * @date 08 Aug 2023
 */
public class ConstructPerfectBinaryTreeByHeight {

    public static void main(String[] args) {
        ConstructPerfectBinaryTreeByHeight instance = new ConstructPerfectBinaryTreeByHeight();
        TreeNode root = instance.buildV3(2);
        System.out.println(root);
    }

    /**
     * e.g. N = 3:
     *          0
     *        /   \
     *       1     2
     *      / \   / \
     *     3  4  5   6
     *
     * Recursive construct complete binary tree with parent pointer
     * @param N the depth(or height) of the tree, depth(or height) start from 1
     * @return
     */
    public TreeNode buildV3(int N) {
        if (N == 0) {
            return null;
        }
        return helper(null, 0, N);
    }

    private static TreeNode helper(TreeNode parent, int i, int depth) {
        if (i >= Math.pow(2, depth) - 1) {
            return null;
        }
        TreeNode node = new TreeNode(i);
        node.parent = parent;
        node.left = helper(node, 2 * i + 1, depth);
        node.right = helper(node,2 * i + 2, depth);
        return node;
    }

    /**
     * Recursive construct complete binary tree
     * @param N the depth(or height) of the tree, depth(or height) start from 1
     * @return
     */
    public TreeNode buildV2(int N) {
        if (N == 0) {
            return null;
        }
        return helper(0, N);
    }

    private static TreeNode helper(int i, int depth) {
        if (i >= Math.pow(2, depth) - 1) {
            return null;
        }
        TreeNode node = new TreeNode(i);
        node.left = helper(2 * i + 1, depth);
        node.right = helper(2 * i + 2, depth);
        return node;
    }


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
