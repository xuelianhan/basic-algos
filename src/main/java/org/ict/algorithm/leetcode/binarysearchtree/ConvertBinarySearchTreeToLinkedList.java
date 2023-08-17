package org.ict.algorithm.leetcode.binarysearchtree;

/**
 * @author sniper
 * @date 16 Aug 2023
 */
public class ConvertBinarySearchTreeToLinkedList {

    private TreeNode prev = null;
    private TreeNode head = null;

    public TreeNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        inOrderTraversal(root);
        head.left = prev;
        prev.right = head;

        return head;
    }

    private void inOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);

        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        inOrderTraversal(node.right);
    }
}
