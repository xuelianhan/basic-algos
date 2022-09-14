package org.ict.algorithm.leetcode.tree;

import java.util.Stack;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to
 * the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Input: root = []
 * Output: []
 *
 * Input: root = [0]
 * Output: [0]
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 *
 * @author sniper
 * @date 2021/12/2
 * LC114
 */
public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode two = new TreeNode(2);
        TreeNode six = new TreeNode(6);
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);

        two.left = three;
        two.right = four;
        five.right = six;
        one.left = two;
        one.right = five;
        flatten(one);
    }

    /**
     * We need to flat tree with order of root-left-right,
     * So we should push the node in reverse order like right-left-root
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        flatten(root, null);
    }

    public static TreeNode flatten(TreeNode root, TreeNode prev) {
       if (root == null) {
           return prev;
       }
       System.out.print("before root right:");
       printTreeNode(root, prev);
       prev = flatten(root.right, prev);

       System.out.print("before root left:");
       printTreeNode(root, prev);
       prev = flatten(root.left, prev);

       root.right = prev;
       root.left = null;
       System.out.print("before root:");
       printTreeNode(root, prev);
       prev = root;
       return root;
    }

    private static void printTreeNode(TreeNode root, TreeNode prev) {
        System.out.println("root:" + (root == null ? null : root.val) + ", prev:" + (prev == null ? null : prev.val));
    }
}
