package org.ict.algorithm.leetcode.binarysearchtree;

/**
 * @author sniper
 * @date 16 Aug 2023
 */
public class ConvertBinarySearchTreeToLinkedListV1 {

    public static void main(String[] args) {
        ConvertBinarySearchTreeToLinkedListV1 instance = new ConvertBinarySearchTreeToLinkedListV1();
        /**
         *         0
         *       /  \
         *      1    2
         *     / \  / \
         *    3  4 5  6
         */
        TreeNode root = instance.constructBinaryTree(3);
        /**
         * 3-->1-->4-->0-->5-->2-->6-->
         */
        instance.printTreeInOrder(root);
        TreeNode head = instance.convert(root);

        instance.printNodeList(head);
    }

    public TreeNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode prev = null;
        TreeNode head = null;
        inOrderTraversal(root, prev, head);
        // todo
        // NPE bug here
        head.left = null;
        prev.right = null;

        return head;
    }

    private void inOrderTraversal(TreeNode node, TreeNode prev, TreeNode head) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, prev, head);

        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        inOrderTraversal(node.right, prev, head);
    }

    public TreeNode constructBinaryTree(int height) {
        if (height <= 0) {
            return null;
        }
        return helper(0, height);
    }

    private TreeNode helper(int i, int height) {
        if (i >= Math.pow(2, height) - 1) {
            return null;
        }
        TreeNode node = new TreeNode(i);
        node.left = helper( 2 * i + 1, height);
        node.right = helper(2 * i + 2, height);
        return node;
    }

    public void printTreeInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printTreeInOrder(root.left);
        System.out.print("" + root.getVal() + "-->");
        printTreeInOrder(root.right);
    }

    public void printNodeList(TreeNode head) {
        while (head != null) {
            System.out.print("" + head.getVal() + "-->");
            head = head.getRight();
        }
        System.out.println("null");
    }


}
